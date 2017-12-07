package com.task03.content.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task03.content.dao.ContentDAO;
import com.task03.content.vo.ContentVO;
import com.task03.member.controller.MemberController;

@Service
public class ContentService {
	Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	ContentDAO dao;
	
	public String doWrite(ContentVO vo) {
		return dao.doWrite(vo) == 1 ? "성공" : "실패";
	}

}
