package com.asyf.demo.io.txt;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        File file = new File("C:/Users/Administrator/Desktop/INSERT.txt");
        File file2 = new File("C:/Users/Administrator/Desktop/INSERT2.txt");
        try {
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file2), "gbk");
            BufferedWriter writer = new BufferedWriter(write);


            //
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), "gbk");
            BufferedReader b = new BufferedReader(read);
            String tempString = null;
            int line = 1;
            while ((tempString = b.readLine()) != null) {
                if (tempString.contains("插入学生")) {
                    String s = tempString.substring(tempString.lastIndexOf("]") + 4);
                    System.out.println(line + ": " + s);
                    writer.write(s + "\r\n");
                    line++;
                }
            }
            b.close();
            read.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
