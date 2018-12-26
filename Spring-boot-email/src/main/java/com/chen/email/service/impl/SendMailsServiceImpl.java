package com.chen.email.service.impl;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.chen.email.config.SpringMailConfigBean;
import com.chen.email.service.SendMailsService;

@Service
public class SendMailsServiceImpl implements SendMailsService {
	private static final Logger log = LoggerFactory.getLogger(SendMailsServiceImpl.class);
	@Autowired
	private JavaMailSender mailSender;

	private MimeMessage message = null;

	@Autowired
	private SpringMailConfigBean springMailConfigBean;

	/**
	 * 发送普通格式邮件
	 * 
	 * @param to
	 *            对方邮件地址
	 * @param subject
	 *            邮件标题
	 * @param Text
	 *            邮件内容
	 */
	public boolean sendSimpleMail(String to, String subject, String Text) {
		log.info("发送文字邮件>>>");
		boolean b = false;
		try {
			synchronized (mailSender) {
				SimpleMailMessage message = new SimpleMailMessage();
				// message.setFrom(Sender);
				message.setFrom(springMailConfigBean.getUsername());
				message.setTo(to);
				message.setSubject(subject);
				message.setText(Text);
				mailSender.send(message);
			}
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public void sendHtmlMail(String to, String subject, String Text) {
		log.info("发送html邮件>>");
		try {
			synchronized (mailSender) {
				message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				// helper.setFrom(Sender);
				helper.setFrom(springMailConfigBean.getUsername());
				helper.setTo(to);
				helper.setSubject(subject);
				helper.setText(Text, true);// HTML格式
				mailSender.send(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
