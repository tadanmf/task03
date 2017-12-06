package com.task03.comment.vo;

import java.sql.Timestamp;

public class CommentVO {
	private int idx;
	private String content;
	private int m_idx;
	private Timestamp date;
	
	public CommentVO() {}
	
	public CommentVO(int idx, String content, int m_idx, Timestamp date) {
		super();
		this.idx = idx;
		this.content = content;
		this.m_idx = m_idx;
		this.date = date;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		return "CommentVO [idx=" + idx + ", content=" + content + ", m_idx=" + m_idx + ", date=" + date + "]";
	}
	
}
