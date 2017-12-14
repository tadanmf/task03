package com.task03.image.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.task03.content.dao.ContentDAO;

@Repository
public class ImageDAO {
Logger log = LoggerFactory.getLogger(ContentDAO.class);
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public int addImage(int c_idx, String image) {
		String sql = "INSERT INTO image (image, c_idx) VALUES (:image, :c_idx)";
		
		Map<String, Object> params = new HashMap<>();
		params.put("image", image);
		params.put("c_idx", c_idx);
		
		return jdbcTemplate.update(sql, params);
	}

}
