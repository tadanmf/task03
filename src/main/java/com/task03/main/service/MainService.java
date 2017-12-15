package com.task03.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task03.comment.dao.CommentDAO;
import com.task03.content.dao.ContentDAO;
import com.task03.content.vo.ContentVO;
import com.task03.page.vo.PageVO;
import com.task03.statistic.vo.StatisticVO;

@Service
public class MainService {
	Logger log = LoggerFactory.getLogger(MainService.class);
	
	@Autowired
	ContentDAO contentDao;
	@Autowired
	CommentDAO commentDao;
	
	public Map<String, Object> goMain(String t_idx, String listCount, String pageNumber) {
//		log.info("t_idx: " + t_idx + ", listCount: " + listCount);
		
		// type: tag 별 조회인지, content detail 조회인지, main 호출인지 구분 
		String type = "none";
		int idx = -1;
		
		// 페이징
		PageVO page = new PageVO();
		
		if(t_idx != null && ! ("-1".equals(t_idx)) ) {
			// tag별 조회일 경우
			type = "by_t_idx";
			idx = Integer.parseInt(t_idx);
		} else {
			// main 호출일 경우
			if(listCount != null) {
				page.setCountList(Integer.parseInt(listCount));
			}
			if(pageNumber != null) {
				page.setPage(Integer.parseInt(pageNumber));
			}
		}
		
		/* 글 개수 */
		int count = contentDao.getContentCount(idx);
		
		page.setTotalCount(count);
		
		Map<String, Object> map = new HashMap<>();
		map = getContent(type, idx, page);
		map.put("count", count);
		
		return map;
		
	}
	
	public Map<String, Object> getContent(String type, int idx, PageVO page) {
		/* 글 목록 조회 */
		int totalPage = page.getTotalCount() / page.getCountList();
		if(page.getTotalCount() % page.getCountList() > 0) totalPage++;
		page.setTotalPage(totalPage);
		if(page.getTotalPage() < page.getPage()) page.setPage(page.getTotalPage());
		page.setStartPage(page.getPage() - page.getCountPage());
		page.setEndPage(page.getPage() + page.getCountPage());
		if(page.getStartPage() < 1) page.setStartPage(1);
		if(page.getTotalPage() < page.getEndPage()) page.setEndPage(page.getTotalPage());
		
		List<ContentVO> contentList = contentDao.getContent(type, idx, page);
		
		Map<String, Object> map = new HashMap<>();
		
		if(! "by_c_idx".equals(type)) {
			List<ContentVO> jsonContentList = parseJson(contentList);
			map.put("contentList", jsonContentList);
		} else {
			map.put("contentList", contentList);
		}
		
		log.info("page: " + page);
		
		map.put("page", page);
		
		return map;
	}
	
	public Map<String, Object> getStat() {
		List<StatisticVO> commentStatList = commentDao.getStat();
		List<StatisticVO> contentStatList = contentDao.getStat();
		
		Map<String, Object> map = new HashMap<>();
		map.put("commentStatList", commentStatList);
		map.put("contentStatList", contentStatList);
		
//		log.info("map: " + map);
		
		return map;
	}
	
	/* 에디터 데이터 parse json to string */
	public List<ContentVO> parseJson(List<ContentVO> voList) {
		String string_vo;
		JSONObject json_obj;
		JSONArray json_arr;
//		JSONObject json_content;
//		JSONObject json_data;
//		JSONObject json_image;
		
		List<ContentVO> contentList = new ArrayList<>();
		for(ContentVO vo : voList) {
			string_vo = vo.getContent();
//			log.info("string_vo: " + string_vo);
			
			json_obj = new JSONObject(string_vo);
	//		log.info("json_obj: " + json_obj);
	//		log.info("ops: " + json_obj.get("ops"));
			
			json_arr = (JSONArray) json_obj.get("ops");
			String preview = getPreview(json_arr);
	//		log.info("json_arr: " + json_arr);
			
//			json_content = (JSONObject) json_arr.get(0);
	//		log.info("json_content: " + json_content.getString("insert"));
	//		System.out.println("json_content: " + json_content.getString("insert"));
			
//			json_data = (JSONObject) json_arr.get(1);
	//		log.info("json_data: " + json_data.get("insert"));
	//		System.out.println("json_data: " + json_data.get("insert"));
			
//			json_image = (JSONObject) json_data.get("insert");
	//		log.info("json_image: " + json_image);
//			System.out.println("json_image: " + json_image.get("image").toString());
			
			vo.setContent(preview);
//			log.info("vo: " + vo);
			
			contentList.add(vo);
		}
		return contentList;
	}
	
	public String getPreview(JSONArray data) {
		String result = "";
		
		JSONObject json_content;
		int data_leng = data.length();
		for(int i=0; i < data_leng; i++) {
//			log.info("data.get(" + i + "): " + data.get(i));
			
			json_content = (JSONObject) data.get(i);
			
//			log.info("json_content: " + json_content);
			
//			log.info("json_content.get().getClass(): " + json_content.get("insert").getClass());
			
			if(json_content.get("insert") instanceof java.lang.String) {
				if(! ("\n".equals(json_content.getString("insert")))) {
					result += json_content.getString("insert");
				}
			}
		}
		String ellipsisString = "...";
		String outputString = result;
		int length = 100;
		if(result.length() > 0 && result.length() > length) {
			outputString = result.substring(0, length);
			outputString += ellipsisString;
		}
		
//		log.info("outputString: " + outputString);
		
		return outputString;
		
	}

}
