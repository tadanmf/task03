package com.task03.tag.vo;

public class TagVO {
	private int idx;
	private String tag;
	
	public TagVO() {}

	public TagVO(int idx, String tag) {
		super();
		this.idx = idx;
		this.tag = tag;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "TagVO [idx=" + idx + ", tag=" + tag + "]";
	}
	
}
