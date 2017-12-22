package com.asyf.demo.designPatterns.singleton.singleton;

/**
 * Created by Administrator on 2017/10/20.
 */
public class Singleton2 {

    private static Singleton2 instance = null;

    private Singleton2() {
    }

    private static synchronized void syncInit() {
        if (instance == null) {
            instance = new Singleton2();
        }
    }

    public static Singleton2 getInstance() {
        if (instance == null) {
            //线程A，线程B
            //A，B同时执行到这里，A执行syncInit()，instance==null，创建对象，
            // B等待,A离开syncInit(),B进入，instance!=null，B直接离开syncInit()
            //，保证了单例的实现
            syncInit();
        }
        return instance;

    }
}
