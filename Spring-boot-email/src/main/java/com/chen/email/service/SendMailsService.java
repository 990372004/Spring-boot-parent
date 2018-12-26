package com.chen.email.service;

/**
 * 发送email
 * 
 * @author chen
 * @date 2018-12-24 05:14:21
 */
public interface SendMailsService {
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
	public boolean sendSimpleMail(String to, String subject, String Text);
	/**
	 * 发送html格式邮件
	 * 
	 * @param to
	 *            对方邮件地址
	 * @param subject
	 *            邮件标题
	 * @param Text
	 *            邮件内容  html格式-富文本格式
	 */
	void sendHtmlMail(String to, String subject, String Text);
}