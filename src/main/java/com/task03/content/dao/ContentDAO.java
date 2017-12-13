package com.task03.content.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.task03.content.vo.ContentVO;

@Repository
public class ContentDAO {
	Logger log = LoggerFactory.getLogger(ContentDAO.class);
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<ContentVO> mapper = BeanPropertyRowMapper.newInstance(ContentVO.class);
	
	public int doWrite(ContentVO vo) {
		String sql = "INSERT INTO content (title, content, m_idx) VALUES (:title, :content, :m_idx)";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("title", vo.getTitle());
		params.addValue("content", vo.getContent());
		params.addValue("m_idx", vo.getM_idx());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(sql, params, keyHolder);
		
		return keyHolder.getKey().intValue();
	}

	public List<ContentVO> getContentList() {
		String sql = "SELECT c.idx idx, c.title title, c.content content, c.m_idx m_idx, c.date DATE, m.id id, m.nick nick "
						+ "FROM content c LEFT JOIN member m ON c.m_idx = m.idx ORDER BY c.idx DESC";
		
		return (List<ContentVO>) jdbcTemplate.query(sql, mapper);
	}

	public int getContentCount() {
		String sql = "SELECT count(*) FROM content";
		
		Map<String, Object> params = new HashMap<>();
		
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}

}