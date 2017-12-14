package com.task03.comment.dao;

import java.util.Collections;
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

import com.task03.comment.controller.CommentController;
import com.task03.comment.vo.CommentVO;

@Repository
public class CommentDAO {
	Logger log = LoggerFactory.getLogger(CommentController.class);
	

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<CommentVO> mapper = BeanPropertyRowMapper.newInstance(CommentVO.class);

	public int writeComment(CommentVO vo) {
		String sql = "INSERT INTO comment (m_idx, c_idx, comment) VALUES (:m_idx, :c_idx, :comment)";
		
		Map<String, Object> params = new HashMap<>();
		params.put("m_idx", vo.getM_idx());
		params.put("c_idx", vo.getC_idx());
		params.put("comment", vo.getComment());
		
		return jdbcTemplate.update(sql, params);

	}

	public List<CommentVO> getComment(int idx) {
		String sql = "SELECT * FROM comment LEFT JOIN member m ON comment.m_idx = m.idx WHERE c_idx = :idx ORDER BY comment.idx DESC";
		
		Map<String, Integer> params = Collections.singletonMap("idx", idx);
		
		return (List<CommentVO>) jdbcTemplate.query(sql, params, mapper);
	}

}