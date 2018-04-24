package com.asyf.demo.serialize;

import java.io.*;

public class SerializeDemo {
	public static void main(String[] args) {
		Employee e = new Employee();
		e.name = "张三";
		e.address = "Phokka Kuan, Ambehta Peer";
		e.SSN = 11122333;
		e.number = 101;
		try {
			File f = new File("D:/tmp");
			if (!f.exists()) {
				// 创建文件夹
				f.mkdirs();
			}
			File file = new File("D:/tmp/employee.ser");
			/*
			 * if (!file.exists()) { // 创建文件,写文件时会生成文件，不需要创建文件
			 * file.createNewFile(); }
			 */

			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(e);
			out.flush();
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in D:/tmp/employee.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
