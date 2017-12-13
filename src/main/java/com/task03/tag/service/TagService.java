package com.task03.tag.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task03.tag.dao.TagDAO;
import com.task03.tag.vo.TagVO;

@Service
public class TagService {
	Logger log = LoggerFactory.getLogger(TagService.class);
	
	@Autowired
	TagDAO dao;

	public List<TagVO> getTagList() {
		log.info("getTagList() service"); 
		
		return dao.getTagList();
	}

	public int addTag(TagVO vo) {
//		int result = dao.addTag(vo);
//		log.info("result: " + result);
		return dao.addTag(vo);
	}
	
	public int checkTag(TagVO vo) {
		return dao.checkTag(vo);
	}

	public int doWrite(TagVO vo) {
		int result = checkTag(vo); 
		if(result == -1) {
			result = addTag(vo);
		}
		
		return result;
	}

}
