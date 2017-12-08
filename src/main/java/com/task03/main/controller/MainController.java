package com.task03.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.task03.content.service.ContentService;
import com.task03.content.vo.ContentVO;

@Controller
public class MainController {
	Logger log = LoggerFactory.getLogger(MainController.class);
	String type, result;
	
	@Autowired
	ContentService service;
	
	@RequestMapping(path = {"/main", "/"}, method = RequestMethod.GET)
	public String goMain(HttpServletRequest request) {
		
		/* 글 목록 */
		List<ContentVO> contentList = service.getContentList();
		
//		log.info(contentList.toString());
		
		request.setAttribute("contentList", contentList);
		/* 글 목록 */
		
		/* 댓글 목록 */
		/* 댓글 목록 */
		
		/* 태그 목록 */
		/* 태그 목록 */
		
		/* 통계 */
		/* 통계 */
		
		return "main";
	}
	
}
