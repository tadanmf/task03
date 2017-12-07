package com.task03.content.vo;

import java.sql.Timestamp;

public class ContentVO {
	private int idx;
	private String title;
	private String content;
	private String m_id;
	private String m_nick;
	private int m_idx;
	private Timestamp date;
	
	public ContentVO() {}

	public ContentVO(int idx, String title, String content, String m_id, String m_nick, int m_idx, Timestamp date) {
		super();
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.m_id = m_id;
		this.m_nick = m_nick;
		this.m_idx = m_idx;
		this.date = date;
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

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_nick() {
		return m_nick;
	}

	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
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

	@Override
	public String toString() {
		return "ContentVO [idx=" + idx + ", title=" + title + ", content=" + content + ", m_id=" + m_id + ", m_nick="
				+ m_nick + ", date=" + date + "]";
	}
	
}
