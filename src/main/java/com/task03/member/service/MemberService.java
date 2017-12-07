package com.task03.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task03.member.dao.MemberDAO;
import com.task03.member.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;

	public MemberVO doLogin(MemberVO vo) {
		List<MemberVO> memberList = dao.doLogin(vo);
		
		MemberVO member = new MemberVO();
		
		for(MemberVO m : memberList) {
			if(m.getPw().equals(vo.getPw())) {
				member.setIdx(m.getIdx());
				member.setId(m.getId());
				member.setLevel(m.getLevel());
				member.setNick(m.getNick());
				member.setPw(m.getPw());
			}
		}
		
		return member;
	}

	public void doSignup(MemberVO vo) {
		dao.doSignup(vo);
	}
	
	public Map<String,Object> findById(String id) {
		return dao.findById(id);
	}

}
