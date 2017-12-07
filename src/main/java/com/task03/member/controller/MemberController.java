package com.task03.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.task03.member.service.MemberService;
import com.task03.member.vo.MemberVO;

@Controller
public class MemberController {
	Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService service;
	
	@RequestMapping("/test")
	@ResponseBody
	public Map<String,Object> findById(HttpServletRequest request) {
		String id = request.getParameter("id");
		return service.findById(id);
	}
	
	String result, type;
	
	/* 로그인 */
	@RequestMapping("/login")
	public String doLogin(@ModelAttribute MemberVO vo, HttpServletRequest request) {
		type = "로그인";
		result = "실패";
		
		MemberVO member = service.doLogin(vo);
		
		if(member != null) {
			result = "성공";
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
		}
		
		log.debug("result: " + result);
		log.debug("type: " + type);
		
		request.setAttribute("result", result);
		request.setAttribute("type", type);
		
		return "process";
	}
	
	/* 로그아웃 */
	@RequestMapping("/logout")
	public String doLogout(HttpServletRequest request) {
		type = "로그아웃";
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		request.setAttribute("type", type);
		
		return "process";
	}
	
	/* 회원 가입 */
	@RequestMapping("/signup")
	public String doSignup(@ModelAttribute MemberVO vo, HttpServletRequest request) {
		log.info(vo.toString());
		
		type = "회원 가입";
		result = service.doSignup(vo);
		
		request.setAttribute("type", type);
		request.setAttribute("result", result);
		
		return "process";
	}

}
