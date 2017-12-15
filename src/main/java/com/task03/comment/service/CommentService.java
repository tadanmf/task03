package com.task03.comment.service;

import java.util.Map;

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

	public int writeComment(CommentVO vo) {
		return dao.writeComment(vo);
	}

	public String writeComment(Map<String, Object> map) {
		CommentVO vo = new CommentVO();
		
		vo.setC_idx(Integer.parseInt(map.get("c_idx").toString()));
		vo.setComment(map.get("comment").toString());
		vo.setM_idx(Integer.parseInt(map.get("m_idx").toString()));
		vo.setNick(map.get("nick").toString());
		
		return writeComment(vo) == 1 ? "성공" : "실패";
	}

}
