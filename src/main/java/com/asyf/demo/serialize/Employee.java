package com.asyf.demo.serialize;

import java.io.Serializable;

public class Employee implements Serializable {
	public String name;
	public String address;
	// transient是Java语言的关键字，用来表示一个域不是该对象串行化的一部分。
	// 当一个对象被串行化的时候，transient型变量的值不包括在串行化的表示中，然而非transient型的变量是被包括进去的。
	// public transient int SSN;
	public int SSN;
	public int number;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSSN() {
		return SSN;
	}

	public void setSSN(int sSN) {
		SSN = sSN;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
