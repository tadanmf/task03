package com.task03.content.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.task03.content.vo.ContentVO;
import com.task03.member.controller.MemberController;

@Repository
public class ContentDAO {
	Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<ContentVO> contentMapper = BeanPropertyRowMapper.newInstance(ContentVO.class);
	
	
	public int doWrite(ContentVO vo) {
		String sql = "INSERT INTO content (title, cotent, m_idx) VALUES (:title, :cotent, :m_idx)";
		
		Map params = new HashMap<>();
		params.put("title", vo.getTitle());
		params.put("cotent", vo.getContent());
		params.put("m_idx", vo.getM_idx());
		
		return jdbcTemplate.update(sql, params);
	}


	public List<ContentVO> getContentList() {
		String sql = "SELECT * FROM content";
		
		return (List<ContentVO>) jdbcTemplate.query(sql, contentMapper);
	}

}
