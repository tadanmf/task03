package com.task03.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@RequestMapping(path = {"/main", "/"}, method = RequestMethod.GET)
	public String goMain() {
		
		/* 글 목록 */
		/* 글 목록 */
		
		/* 태그 목록 */
		/* 태그 목록 */
		
		/* 통계 */
		/* 통계 */
		
		return "main";
	}

}
