package com.task03;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class RootConfig {
	
	Logger log = LoggerFactory.getLogger(RootConfig.class);
	
	@Bean
	public DataSource dataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://10.10.20.151:3306/task3");
	    dataSource.setUsername("root");
	    dataSource.setPassword("maria");
	    
	    log.info("root-config dataSource: " + dataSource);
	    
	    return dataSource;
	}
	
//	@Bean
//	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
//		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
//		
//		log.info("root-config template: " + template);
//		
//		return template;
//	}

}
