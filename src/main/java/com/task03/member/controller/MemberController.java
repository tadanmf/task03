package com.task03.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.task03.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	/* 로그인 */
	@RequestMapping("/login")
	public String doLogin(Model model) {
		System.out.println("asf");
//		service.selectLoginList();
		return "test";
	}

}
