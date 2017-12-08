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
	private RowMapper<ContentVO> mapper = BeanPropertyRowMapper.newInstance(ContentVO.class);
	
	
	public int doWrite(ContentVO vo) {
		String sql = "INSERT INTO content (title, content, m_idx) VALUES (:title, :content, :m_idx)";
		
		Map<String, Object> params = new HashMap<>();
		params.put("title", vo.getTitle());
		params.put("content", vo.getContent());
		params.put("m_idx", vo.getM_idx());
		
		return jdbcTemplate.update(sql, params);
	}


	public List<ContentVO> getContentList() {
		String sql = "SELECT c.idx idx, c.title title, c.content content, c.m_idx m_idx, c.date DATE, m.id id, m.nick nick "
						+ "FROM content c LEFT JOIN member m ON c.m_idx = m.idx ORDER BY c.idx DESC";
		
		return (List<ContentVO>) jdbcTemplate.query(sql, mapper);
	}

}