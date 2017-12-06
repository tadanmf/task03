package com.task03.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task03.member.dao.MemberDAO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;

	public void doLogin() {
		dao.doLogin();
	}

}
