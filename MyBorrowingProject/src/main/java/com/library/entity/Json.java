package com.library.entity;

public class Json<T> {
	
	
	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	private Integer i;
	private T data;

	public Json(Integer i , T data) {
        this.i = i;
        this.data = data;
    }

}
