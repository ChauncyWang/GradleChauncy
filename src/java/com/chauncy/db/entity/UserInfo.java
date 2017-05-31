package com.chauncy.db.entity;

import java.io.Serializable;

/**
 * 用户信息
 * Created by chauncy on 17-3-26.
 */
public class UserInfo implements Serializable {
	private boolean isMan;
	private int age;
	private boolean isCity;

	public UserInfo() {

	}

	public UserInfo(boolean isMan, int age, boolean isCity) {
		this.isMan = isMan;
		this.age = age;
		this.isCity = isCity;
	}

	public boolean isMan() {
		return isMan;
	}

	public void setMan(boolean man) {
		isMan = man;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isCity() {
		return isCity;
	}

	public void setCity(boolean city) {
		isCity = city;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"isMan=" + isMan +
				", age=" + age +
				", isCity=" + isCity +
				'}';
	}
}
