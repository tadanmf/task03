package com.task03.comment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.task03.comment.service.CommentService;
import com.task03.comment.vo.CommentVO;
import com.task03.member.vo.MemberVO;

@Controller
public class CommentController {
	Logger log = LoggerFactory.getLogger(CommentController.class);
	String type, result;
	
	@Autowired
	CommentService service;
	
	@RequestMapping(value = "/writeComment", method = RequestMethod.POST)
	@ResponseBody
	public List<CommentVO> writeComment(@ModelAttribute CommentVO vo, HttpServletRequest request) {
		MemberVO member = (MemberVO) request.getSession().getAttribute("member");
		vo.setM_idx(member.getIdx());
		
//		service.writeComment(vo);
		
//		log.info("***" + vo.toString());
		
		return service.writeComment(vo);
	}

}
