package com.blueocean.common.vo;

public class Page {
	private Long count = 0L;
	private Object data;
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
