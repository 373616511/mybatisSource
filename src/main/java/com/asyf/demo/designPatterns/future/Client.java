package com.asyf.demo.designPatterns.future;

public class Client {
    final FutureData futureData = new FutureData();

    public Data request(final String queryStr) {
        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }.start();
        return futureData;
    }
}
