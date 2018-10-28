package com.asyf.demo.designPatterns.future;

public class RealData implements Data {

    protected final String result;

    public RealData(String param) {
        System.out.println("传入参数" + param + ",数据查询中...");
        //模拟RealData的构造可能很慢，需要用户等待很久
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = "数据查询成功";
    }

    @Override
    public String getResult() {
        return null;
    }

}
