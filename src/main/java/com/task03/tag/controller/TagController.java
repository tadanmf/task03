package com.task03.tag.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.task03.tag.service.TagService;

@Controller
public class TagController {
	Logger log = LoggerFactory.getLogger(TagController.class);
	
	@Autowired
	TagService service;
	
	@RequestMapping(value = "/addTag")
	@ResponseBody
	public String addTag(@RequestParam(value="param") String param) {
//		String tag = request.getParameter("param");
		
		log.info("addTag, tag: " + param);

		return "성공?";
		
//		return service.addTag(vo);
	}

}
