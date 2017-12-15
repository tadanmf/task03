package com.task03.comment.vo;

import java.sql.Timestamp;

public class CommentVO {
	private int idx;
	private String comment;
	private String nick;
	private int m_idx;
	private int c_idx;
	private Timestamp date;
	private String format_date;
	private String _nick;
	
	public CommentVO() {}
	
	public CommentVO(int idx, String comment, String nick, int m_idx, int c_idx, Timestamp date ,String format_date, String _nick) {
		super();
		this.idx = idx;
		this.comment = comment;
		this.nick = nick;
		this.m_idx = m_idx;
		this.c_idx = c_idx;
		this.date = date;
		this.format_date = format_date;
		this._nick = _nick;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getC_idx() {
		return c_idx;
	}

	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}

	public String getFormat_date() {
		return format_date;
	}

	public void setFormat_date(String format_date) {
		this.format_date = format_date;
	}
	
	public String get_nick() {
		return _nick;
	}

	public void set_nick(String _nick) {
		this._nick = _nick;
	}

	@Override
	public String toString() {
		return "CommentVO [idx=" + idx + ", comment=" + comment + ", nick=" + nick + ", m_idx=" + m_idx + ", c_idx="
				+ c_idx + ", date=" + date + ", format_date=" + format_date + ", _nick=" + _nick + "]";
	}

}
