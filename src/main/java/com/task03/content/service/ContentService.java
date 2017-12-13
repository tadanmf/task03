package com.task03.content.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task03.content.dao.ContentDAO;
import com.task03.content.vo.ContentVO;

@Service
public class ContentService {
	Logger log = LoggerFactory.getLogger(ContentService.class);
	
	@Autowired
	ContentDAO dao;
	
	public int doWrite(ContentVO vo) {
		return dao.doWrite(vo);
	}

	public List<ContentVO> getContentList() {
		List<ContentVO> contentList = dao.getContentList();
		
//		log.info("***" + contentList.toString());
		
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
		SimpleDateFormat today_df = new SimpleDateFormat("HH:mm");
		
		// 오늘 글이면 today_df 아니면 df
		for(ContentVO content : contentList) {
			String date = df.format(content.getDate());
			String today = df.format(new Date());
			
			if(date.equals(today)) {
				content.setFormat_date(today_df.format(content.getDate()));
			} else {
				content.setFormat_date(df.format(content.getDate()));
			}
		}
		
		log.info(contentList.toString());
		
		return contentList;
	}

	public int getContentCount() {
		
		return dao.getContentCount();
	}

}
