package com.task03.contentTag.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task03.contentTag.dao.ContentTagDAO;

@Service
public class ContentTagService {
	Logger log = LoggerFactory.getLogger(ContentTagService.class);
	
	@Autowired
	ContentTagDAO dao;

	public String doWrite(int c_idx, int t_idx) {
		return dao.doWrite(c_idx, t_idx) == 1? "성공" : "실패";
	}
	
}
