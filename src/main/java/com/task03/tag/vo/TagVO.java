package com.task03.tag.vo;

public class TagVO {
	private int idx;
	private String tag;
	private int count;
	
	public TagVO() {}

	public TagVO(int idx, String tag, int count) {
		this.idx = idx;
		this.tag = tag;
		this.count = count;
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
	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "TagVO [idx=" + idx + ", tag=" + tag + ", count= " + count + "]";
	}
	
}
