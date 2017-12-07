package com.task03.member.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
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
	private NamedParameterJdbcTemplate JdbcTemplate;
	
	public List<MemberVO> doLogin(MemberVO vo) {
		List<MemberVO> memberList = new ArrayList<>();
		MemberVO member = new MemberVO();
		
		member.setIdx(1);
		member.setId("admin");
		member.setLevel("A");
		member.setNick("대장");
		member.setPw("a");
		
		memberList.add(member);
		return memberList;
	}

	public void doSignup(MemberVO vo) {
		
	}
	
	public Map<String,Object> findById(String id) {
		log.info("id: " + id);
		Map result = null;
		Map<String, String> params = Collections.singletonMap("id", id);
		log.info(params.toString());
		try {
			result = JdbcTemplate.queryForObject(SELECT_BY_ID, params, new ColumnMapRowMapper());
		} catch(EmptyResultDataAccessException e) {
		}
		log.info(result.toString());
		return result;
	}	

}
