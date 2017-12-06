package com.task03.member.vo;

public class MemberVO {
	private int idx;
	private String id;
	private String pw;
	private String nick;
	private String level;
	
	public MemberVO() {}

	public MemberVO(int idx, String id, String pw, String nick, String level) {
		super();
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.nick = nick;
		this.level = level;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "MemberVO [idx=" + idx + ", id=" + id + ", pw=" + pw + ", nick=" + nick + ", level=" + level + "]";
	}
	
}
