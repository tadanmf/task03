package task03;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NamedParameterJdbcTemplateTest {
	Logger log = LoggerFactory.getLogger(NamedParameterJdbcTemplate.class);
	private DataSource dataSource;
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		log.debug("setDataSource :: called");
	    this.dataSource = dataSource;
	}
	
	public static final String SELECT_BY_ID =
			"SELECT id, nick FROM member WHERE id = :id";
	
	private RowMapper<Map<String,Object>> mapper = new ColumnMapRowMapper();

	public Map<String,Object> findById(String id) {
		log.debug("id: " + id);
		log.debug("template: " + template);
		log.debug("dataSource: " + dataSource);
		
		Map<String, String> params = Collections.singletonMap("id", id);
		return template.queryForObject(SELECT_BY_ID, params, mapper);
	}
	
	@Test
	public void main() {
		System.out.println(findById("admin"));
	}

}
