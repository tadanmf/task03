package com.task03.content.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.task03.comment.vo.CommentVO;
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
	ContentService service;
	@Autowired
	TagService tagService;
	@Autowired
	ContentTagService conTagService;
	
	/* 글 작성 */
	@RequestMapping("/write")
	public String doWrite(@ModelAttribute ContentVO contentVo, @ModelAttribute TagVO tagVo, HttpServletRequest request) {
//		log.info(vo.toString());
		
//		log.info("/write");
		log.info(contentVo.toString());
		
		MemberVO member = (MemberVO) request.getSession().getAttribute("member");
		contentVo.setM_idx(member.getIdx());

		type = "글 쓰기";
		
		int c_idx = service.doWrite(contentVo);
		int t_idx = tagService.doWrite(tagVo);
//		
		result = conTagService.doWrite(c_idx, t_idx);
		
		request.setAttribute("type", type);
		request.setAttribute("result", result);
		
		return "process";
	}
	
	/* 디테일 페이지 */
	@RequestMapping("/detail")
	public String goDetail(@RequestParam("idx") int idx, HttpServletRequest request) {
		log.info("idx: " + idx);
		
		Map<String, Object> map = service.goDetail(idx);
		ContentVO content = (ContentVO) map.get("content");
		List<CommentVO> commentList = (List<CommentVO>) map.get("commentList");
		TagVO tag = (TagVO) map.get("tag");
		
		request.setAttribute("content", content);
		request.setAttribute("commentList", commentList);
		request.setAttribute("tag", tag);
		
		return "detail";
	}

}
