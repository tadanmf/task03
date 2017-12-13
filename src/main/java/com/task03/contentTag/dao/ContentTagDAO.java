package com.task03.contentTag.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.task03.contentTag.service.ContentTagService;

@Repository
public class ContentTagDAO {
	Logger log = LoggerFactory.getLogger(ContentTagService.class);
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public int doWrite(int c_idx, int t_idx) {
		String sql = "INSERT INTO tag_content (t_idx, c_idx) VALUES (:t_idx, :c_idx)";
		
		Map<String, Object> params = new HashMap<>();
		params.put("t_idx", t_idx);
		params.put("c_idx", c_idx);
		
		return jdbcTemplate.update(sql, params);
	}
	
}
