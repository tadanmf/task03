package com.task03.statistic.vo;

public class StatisticVO {
	private String key;
	private int value;
	
	public StatisticVO() {}

	public StatisticVO(String key, int value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "StatisticVO [key=" + key + ", value=" + value + "]";
	}
	
}
