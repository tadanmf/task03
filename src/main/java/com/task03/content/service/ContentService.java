package com.task03.content.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task03.comment.dao.CommentDAO;
import com.task03.comment.vo.CommentVO;
import com.task03.content.dao.ContentDAO;
import com.task03.content.vo.ContentVO;
import com.task03.tag.dao.TagDAO;
import com.task03.tag.vo.TagVO;

@Service
public class ContentService {
	Logger log = LoggerFactory.getLogger(ContentService.class);
	
	@Autowired
	ContentDAO dao;
	@Autowired
	CommentDAO comment_dao;
	@Autowired
	TagDAO tag_dao;
	
	SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
	SimpleDateFormat today_df = new SimpleDateFormat("HH:mm");
	SimpleDateFormat day_df = new SimpleDateFormat("YYYY-MM-dd HH:mm");
	
	public int doWrite(ContentVO vo) {
		return dao.doWrite(vo);
	}

	public List<ContentVO> getContentList(String type, int idx) {
		List<ContentVO> contentList = dao.getContent(type, idx);

		// 오늘 글이면 today_df 아니면 day_df
		String date = "", today = "";
		for(ContentVO content : contentList) {
			date = df.format(content.getDate());
			today = df.format(new Date());
			
			if(date.equals(today)) {
				content.setFormat_date(today_df.format(content.getDate()));
			} else {
				content.setFormat_date(day_df.format(content.getDate()));
			}
		}
		
		log.info(contentList.toString());
		
		return contentList;
	}

	public int getContentCount(int t_idx) {
		return dao.getContentCount(t_idx);
	}

	/* 디테일 페이지 */
	public Map<String, Object> goDetail(int idx) {
		/* 글 내용 */
		String type = "by_c_idx";
		List<ContentVO> contentList = getContentList(type, idx);
		ContentVO content = contentList.get(0);
		/* 댓글 목록 */
		List<CommentVO> commentList = getComment(idx);
		/* 태그 */
		List<TagVO> tagList = tag_dao.getTagList(idx);
		TagVO tag = null;
		if(! tagList.isEmpty()) {
			tag = tagList.get(0);
		}
		
//		log.info(contentList.toString());
//		log.info(commentList.toString());
//		log.info(tagList.toString());
		
		Map<String, Object> map = new HashMap<>();
		map.put("content", content);
		map.put("commentList", commentList);
		map.put("tag", tag);
		
		return map;
	}
	
	public List<CommentVO> getComment(int idx) {
		List<CommentVO> commentList = comment_dao.getComment(idx);
		
		// 오늘 글이면 today_df 아니면 day_df
		String date = "", today = "";
		for(CommentVO comment : commentList) {
			date = df.format(comment.getDate());
			today = df.format(new Date());
			
			if(date.equals(today)) {
				comment.setFormat_date(today_df.format(comment.getDate()));
			} else {
				comment.setFormat_date(day_df.format(comment.getDate()));
			}
		}
		
		log.info(commentList.toString());
		
		return commentList;
	}
	
	/* 날짜 포맷 */
//	public List<Object> formatDate() {
//		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
//		SimpleDateFormat today_df = new SimpleDateFormat("HH:mm");
//		
//	}

}
