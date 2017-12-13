package com.task03.tag.dao;

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

import com.task03.tag.vo.TagVO;

@Repository
public class TagDAO {
	Logger log = LoggerFactory.getLogger(TagDAO.class);
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<TagVO> mapper = BeanPropertyRowMapper.newInstance(TagVO.class);
	
	public List<TagVO> getTagList() {
		String sql = "SELECT * FROM tag";
		
		return jdbcTemplate.query(sql, new HashMap(), mapper);
	}

	public int addTag(TagVO vo) {
		String sql = "INSERT INTO tag (tag) VALUES (:tag)";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("tag", vo.getTag());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(sql, params, keyHolder);
		
		return keyHolder.getKey().intValue();
	}

	public int checkTag(TagVO vo) {
		String sql = "SELECT idx FROM tag WHERE tag = :tag";
		int result = -1;
		
		Map<String, Object> params = new HashMap<>();
		params.put("tag", vo.getTag());
		
		try {
			result = jdbcTemplate.queryForObject(sql, params, Integer.class);
		} catch (Exception e) {
		}
		
		log.info("result: " + result);
		
		return result;
	}

}
