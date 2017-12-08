package com.task03.content.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.task03.content.service.ContentService;
import com.task03.content.vo.ContentVO;
import com.task03.member.controller.MemberController;
import com.task03.member.vo.MemberVO;

@Controller
public class ContentController {
	Logger log = LoggerFactory.getLogger(MemberController.class);
	String type, result;
	
	@Autowired
	ContentService service;
	
	@RequestMapping("/write")
	public String doWrite(@ModelAttribute ContentVO vo, HttpServletRequest request) {
//		log.info(vo.toString());
		
		MemberVO member = (MemberVO) request.getSession().getAttribute("member");
		vo.setM_idx(member.getIdx());

		type = "글 쓰기";
		result = service.doWrite(vo);
		
		request.setAttribute("type", type);
		request.setAttribute("result", result);
		
		return "process";
	}

}
