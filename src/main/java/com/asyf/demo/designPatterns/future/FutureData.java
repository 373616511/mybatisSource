package com.asyf.demo.designPatterns.future;

public class FutureData implements Data {

    protected RealData realData = null;//FutureData是RealData的包装
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {//成立表示数据没有查询出来
            try {
                wait();//等待数据装配
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;
    }
}
