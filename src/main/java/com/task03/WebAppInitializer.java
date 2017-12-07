package com.task03;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	Logger log = LoggerFactory.getLogger(WebAppInitializer.class);
	
	DataSource dataSource;

	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		return characterEncodingFilter;
	}

	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("건강검진 안뇽~");
		log.info("스프링 시작1");
		log.debug("dataSource: " + dataSource);
		
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootConfig.class);
		rootContext.setServletContext(servletContext);

		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}

}
