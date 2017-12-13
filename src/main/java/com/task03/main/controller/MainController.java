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
import com.task03.tag.service.TagService;
import com.task03.tag.vo.TagVO;

import twitter4j.JSONArray;

@Controller
public class MainController {
	Logger log = LoggerFactory.getLogger(MainController.class);
	String type, result;
	
	@Autowired
	ContentService contentService;
	@Autowired
	TagService tagService;
	
	@RequestMapping(path = {"/main", "/"}, method = RequestMethod.GET)
	public String goMain(HttpServletRequest request) {
		
		/* 글 목록 */
		List<ContentVO> contentList = contentService.getContentList();
		
//		log.info(contentList.toString());
		
		request.setAttribute("contentList", contentList);
		/* 글 목록 */
		
		/* 글 목록 개수  */
		int count = contentService.getContentCount();
		
		request.setAttribute("count", count);
		/* 글 목록 개수  */
		
		/* 태그 목록 */
		List<TagVO> tagList = null;
		if(tagService.getTagList() != null) {
			 tagList = tagService.getTagList();
		}
		
		log.info(tagList.toString());
		
		
		JSONArray json = new JSONArray(tagList);
		
		request.setAttribute("tagList_json", json);
		request.setAttribute("tagList", tagList);
		
		/* 태그 목록 */
		
		/* 통계 */
		/* 통계 */
		
		return "main";
	}
	
}
