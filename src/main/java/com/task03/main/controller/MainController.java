package com.task03.main.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.task03.content.service.ContentService;
import com.task03.content.vo.ContentVO;
import com.task03.main.service.MainService;
import com.task03.page.vo.PageVO;
import com.task03.statistic.vo.StatisticVO;
import com.task03.tag.service.TagService;
import com.task03.tag.vo.TagVO;

import twitter4j.JSONArray;

@Controller
public class MainController {
	Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	MainService service;
	@Autowired
	ContentService contentService;
	@Autowired
	TagService tagService;
	
	@RequestMapping(path = {"/main", "/"}, method = RequestMethod.GET)
	public String goMain(HttpServletRequest request) {
		/* 글 목록, 개수 */
		Map<String, Object> contentMap = service.goMain(request.getParameter("t_idx"), request.getParameter("listCount"), request.getParameter("page"));
		
		List<ContentVO> contentList = (List<ContentVO>) contentMap.get("contentList");
		PageVO page = (PageVO) contentMap.get("page");
		int count = (int) contentMap.get("count");
		
		request.setAttribute("page", page);
		request.setAttribute("contentList", contentList);
		request.setAttribute("count", count);
		/* 글 목록, 개수 */
		
		/* 선택한 태그 */
		String selected = "";
		if(request.getParameter("tag") != null) {
			selected = request.getParameter("tag");
		}
		log.info(selected);
		
		request.setAttribute("selected", selected);
		/* 선택한 태그 */
		
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
		Map<String, Object> map = service.getStat();
		List<StatisticVO> commentStatList = (List<StatisticVO>) map.get("commentStatList");
		List<StatisticVO> contentStatList = (List<StatisticVO>) map.get("contentStatList");
		
//		log.info("commentStatList: " + commentStatList);
		request.setAttribute("commentStatList", commentStatList);
		request.setAttribute("contentStatList", contentStatList);
		/* 통계 */
		
		return "main";
	}
	
}
