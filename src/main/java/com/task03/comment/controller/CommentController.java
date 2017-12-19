package com.task03.comment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.task03.comment.service.CommentService;
import com.task03.comment.vo.CommentVO;

@Controller
public class CommentController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	String type, result;
	
	@Autowired
	CommentService service;
	
	@RequestMapping(value = "/writeComment", method = RequestMethod.POST)
	@ResponseBody
	public String writeComment(@RequestBody Map<String, Object> map, HttpServletRequest request) {
//		log.info("asfd: " + map);
//		log.info(map.getClass().getName());
//		log.info(map.get("c_idx").toString());
		
		return service.writeComment(map);
//		log.info("***" + vo.toString());
//		
//		log.info("writeComment result: " + service.writeComment(vo));
	}
	
	@RequestMapping("/getComment")
	@ResponseBody
	public List<CommentVO> getComment(@RequestParam HashMap<String, String> map) {
		log.info("params: " + map.get("idx"));
		
		return service.getComment(Integer.parseInt(map.get("idx")));
		
	}

}
