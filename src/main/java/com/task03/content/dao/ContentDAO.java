package com.task03.content.dao;

import java.util.Collections;
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
import com.task03.statistic.vo.StatisticVO;

@Repository
public class ContentDAO {
	Logger log = LoggerFactory.getLogger(ContentDAO.class);
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<ContentVO> mapper = BeanPropertyRowMapper.newInstance(ContentVO.class);
	private RowMapper<StatisticVO> statMapper = BeanPropertyRowMapper.newInstance(StatisticVO.class);
	
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

	public List<ContentVO> getContent(String type, int idx, int listCount) {
		String sql = "SELECT c.idx idx, c.title title, c.content content, c.m_idx m_idx, c.date date, m.id id, m.nick nick ";
		
		if("by_c_idx".equals(type)) {
			sql += ", i.image ";
		}
		
		sql += " FROM content c LEFT JOIN member m ON c.m_idx = m.idx ";
		
		if("by_c_idx".equals(type)) {
			sql += " LEFT JOIN image i ON c.idx = i.c_idx WHERE c.idx = " + idx;
		} else if("by_t_idx".equals(type)) {
			sql += " LEFT JOIN tag_content tc ON c.idx = tc.c_idx LEFT JOIN tag t ON tc.t_idx = t.idx WHERE t.idx = " + idx;
		}
		
		sql += " ORDER BY c.idx DESC LIMIT " + listCount;
		
		return (List<ContentVO>) jdbcTemplate.query(sql, new HashMap<String, Object>(), mapper);
	}

	public int getContentCount(int t_idx) {
		String sql = "SELECT count(*) FROM content c";
		
		if(t_idx != -1) {
			sql += " LEFT JOIN tag_content tc ON c.idx = tc.c_idx WHERE tc.t_idx = :t_idx";
		}
		
		Map<String, Object> params = new HashMap<>();
		params.put("t_idx", t_idx);
		
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}

	public List<StatisticVO> getStat() {
		/* 작성글 많은 사용자 top 5 */
		String sql = "SELECT count(c.idx) `value`, m.nick `key` FROM content c LEFT JOIN member m ON c.m_idx = m.idx " 
				+ " GROUP BY m_idx LIMIT 5 ";
		
		return jdbcTemplate.query(sql, new HashMap<String, Object>(), statMapper); 
	}

	public int doEdit(ContentVO vo) {
		String sql = "UPDATE content SET title = :title, content = :content WHERE idx = :idx";
		
		Map<String, Object> params = new HashMap<>();
		params.put("title", vo.getTitle());
		params.put("content", vo.getContent());
		params.put("idx", vo.getIdx());
		
		return jdbcTemplate.update(sql, params);
	}

	public int doDel(int idx) {
		String sql = "DELETE FROM content WHERE idx = :idx";
		
		Map<String, Integer> params = Collections.singletonMap("idx", idx);
		
		return jdbcTemplate.update(sql, params);
	}

}