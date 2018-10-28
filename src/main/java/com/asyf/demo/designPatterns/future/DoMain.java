package com.asyf.demo.designPatterns.future;

import com.mongodb.gridfs.CLI;

public class DoMain {

    public static void main(String[] args) {
        Client client = new Client();//构造发送请求对象
        //立即返回
        Data test_data = client.request("test_data");
        System.out.println("请求发送完毕....");
        //模拟客户端的其他操作
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //调用真实数据
        System.out.println(test_data.getResult() );
    }
}
