package com.task03.comment.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task03.comment.controller.CommentController;
import com.task03.comment.dao.CommentDAO;
import com.task03.comment.vo.CommentVO;

@Service
public class CommentService {
	Logger log = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	CommentDAO dao;

	public List<CommentVO> writeComment(CommentVO vo) {
		int num = dao.writeComment(vo);
		
		List<CommentVO> commentList = null;
		CommentVO comment = null;
		
		if(num == 1) {
			commentList = dao.viewComment();
		} 
		
		log.info(commentList.toString());
		
		return commentList;
	}

}
