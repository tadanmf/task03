package com.task03.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.task03.member.service.MemberService;
import com.task03.member.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	/* 로그인 */
	@RequestMapping("/login")
	public String doLogin(@ModelAttribute MemberVO member) {
		System.out.println(member);
		
		service.doLogin();
		
		
		return "test";
	}

}
