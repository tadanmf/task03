package com.task03.member.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task03.member.dao.MemberDAO;
import com.task03.member.vo.MemberVO;

@Service
public class MemberService {
	Logger log = LoggerFactory.getLogger(MemberService.class);
	
	@Autowired
	private MemberDAO dao;

	public MemberVO doLogin(MemberVO vo) {
		MemberVO member = dao.doLogin(vo);
		
		log.info(member.toString());
		
		if(! vo.getPw().equals(member.getPw())) {
			member = null;
		}
		
		return member;
	}

	public String doSignup(MemberVO vo) {
		
		return dao.doSignup(vo) == 1 ? "성공" : "실패";
		
	}
	
	public Map<String,Object> findById(String id) {
		return dao.findById(id);
	}

}
