package com.task03.content.vo;

import java.sql.Timestamp;

public class ContentVO {
	private int idx;
	private String title;
	private String content;
	private String id;
	private String nick;
	private int m_idx;
	private Timestamp date;
	private String format_date;
	
	public ContentVO() {}

	public ContentVO(int idx, String title, String content, String m_id, String m_nick, int m_idx, Timestamp date, String format_date) {
		super();
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.id = m_id;
		this.nick = m_nick;
		this.m_idx = m_idx;
		this.date = date;
		this.format_date = format_date;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String m_id) {
		this.id = m_id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String m_nick) {
		this.nick = m_nick;
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

	public String getFormat_date() {
		return format_date;
	}

	public void setFormat_date(String format_date) {
		this.format_date = format_date;
	}
	

	@Override
	public String toString() {
		return "ContentVO [idx=" + idx + ", title=" + title + ", content=" + content + ", id=" + id + ", nick=" + nick
				+ ", m_idx=" + m_idx + ", date=" + date + ", format_date=" + format_date + "]";
	}
	
}
