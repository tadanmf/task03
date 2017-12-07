package com.task03.member.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.task03.member.controller.MemberController;
import com.task03.member.vo.MemberVO;

@Repository
public class MemberDAO {
	
	public static final String SELECT_BY_ID =
			"SELECT id, nick FROM member WHERE id = :id";
	
	Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<MemberVO> memberMapper = BeanPropertyRowMapper.newInstance(MemberVO.class);
	
	public MemberVO doLogin(MemberVO vo) {
		String sql = "SELECT * FROM member WHERE id = :id";
		Map<String, String> params = Collections.singletonMap("id", vo.getId());
		
		return jdbcTemplate.queryForObject(sql, params, memberMapper);
	}

	public int doSignup(MemberVO vo) {
		String sql = "INSERT INTO member (id, pw, nick) VALUES (:id, :pw, :nick)";
		
		Map<String, String> params = new HashMap<>();
		params.put("id", vo.getId());
		params.put("pw", vo.getPw());
		params.put("nick", vo.getNick());
		
		int num = 0;
		try {
			num = jdbcTemplate.update(sql, params);
		} catch(DuplicateKeyException e) {
		}

		return num;
	}
	
	public Map<String,Object> findById(String id) {
		log.info("id: " + id);
		Map result = null;
		Map<String, String> params = Collections.singletonMap("id", id);
		log.info(params.toString());
		try {
			result = jdbcTemplate.queryForObject(SELECT_BY_ID, params, new ColumnMapRowMapper());
		} catch(EmptyResultDataAccessException e) {
		}
		log.info(result.toString());
		return result;
	}	

}
