package com.chen.jpa.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chen.jpa.Page;
import com.chen.jpa.Pageable;
import com.chen.jpa.entity.MemberEntity;
import com.chen.jpa.service.MemberService;

/**
 * 测试
 * 
 * @author chen
 * @date 2019-01-03 03:09:45
 */
@RestController("testJpaController")
public class TestController {
	private static final Logger log = LoggerFactory.getLogger(TestController.class);
	@Resource(name = "memberJpaServiceImpl")
	private MemberService memberService;

	@RequestMapping(method = RequestMethod.GET)
	public Page<MemberEntity> ces(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageNum", required = true, defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", required = true, defaultValue = "1") Integer pageSize) {
		log.info("返回json");
		String userName = "numeeee";
		MemberEntity member = new MemberEntity(new Date(), userName, "asda", "asdjas", "asdjaod", null, null);
		MemberEntity member1 = memberService.findByUsername(userName);
		if (null == member1) {
			// 根据用户名查询
			memberService.save(member);
			log.info("持久化数据>>>" + member.toString());
		}else {
			member.setId(member1.getId());
			member.setMap("测试map");
			memberService.update(member);
			log.info("更新化数据>>>" + member.toString());
		}
		// 分页查询
		return memberService.findPage(null);
	}

}