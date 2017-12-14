package com.task03.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task03.comment.dao.CommentDAO;
import com.task03.content.dao.ContentDAO;
import com.task03.statistic.vo.StatisticVO;

@Service
public class MainService {
	Logger log = LoggerFactory.getLogger(MainService.class);
	
	@Autowired
	ContentDAO contentDao;
	@Autowired
	CommentDAO commentDao;
	
	public Map<String, Object> getStat() {
		List<StatisticVO> commentStatList = commentDao.getStat();
		List<StatisticVO> contentStatList = contentDao.getStat();
		
		Map<String, Object> map = new HashMap<>();
		map.put("commentStatList", commentStatList);
		map.put("contentStatList", contentStatList);
		
//		log.info("map: " + map);
		
		return map;
	}

	
}
