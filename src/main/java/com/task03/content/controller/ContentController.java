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
import com.task03.contentTag.service.ContentTagService;
import com.task03.member.vo.MemberVO;
import com.task03.tag.service.TagService;
import com.task03.tag.vo.TagVO;

@Controller
public class ContentController {
	Logger log = LoggerFactory.getLogger(ContentController.class);
	String type, result;
	
	@Autowired
	ContentService contentService;
	@Autowired
	TagService tagService;
	@Autowired
	ContentTagService conTagService;
	
	@RequestMapping("/write")
	public String doWrite(@ModelAttribute ContentVO contentVo, @ModelAttribute TagVO tagVo, HttpServletRequest request) {
//		log.info(vo.toString());
		
		log.info("/write");
		log.info(contentVo.toString());
		
		MemberVO member = (MemberVO) request.getSession().getAttribute("member");
		contentVo.setM_idx(member.getIdx());

		type = "글 쓰기";
		
//		int c_idx = contentService.doWrite(contentVo);
//		int t_idx = tagService.doWrite(tagVo);
//		
//		result = conTagService.doWrite(c_idx, t_idx);
		
		request.setAttribute("type", type);
		request.setAttribute("result", result);
		
		return "process";
	}
	
	@RequestMapping("/detail")
	public String goDetail() {
		return "detail";
	}

}
