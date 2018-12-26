package com.chen.email.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chen.email.service.SendMailsService;

@RestController("testController")
public class TestController {
	private static final Logger log = LoggerFactory.getLogger(TestController.class);
	@Resource
	private SendMailsService sendMailsService;

	@Value("${spring.mail.username}")
	private String username;

	@RequestMapping(method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response) {
		log.info("发送测试邮件>>>" + username);
		sendMailsService.sendSimpleMail("990372004@qq.com", "测试邮件标题", "测试邮件内容>>>");
		return "send success";
	}
}
