package com.asyf.demo.serialize;

import java.io.*;

public class DeserializeDemo {

    public static void main(String[] args) {
        //序列化
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
            if (!file.exists()) { // 创建文件,写文件时会生成文件，不需要创建文件
                file.createNewFile();
            }


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
        //反序列化
        Employee e2 = null;
        try {
            FileInputStream fileIn = new FileInputStream("D:/tmp/employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e2 = (Employee) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("Deserialized Employee...");
        System.out.println("Name: " + e2.name);
        System.out.println("Address: " + e2.address);
        System.out.println("SSN: " + e2.SSN);
        System.out.println("Number: " + e2.number);

        System.err.println(e.equals(e2));
    }
}