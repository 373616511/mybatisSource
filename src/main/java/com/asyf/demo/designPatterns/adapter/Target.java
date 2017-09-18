package com.asyf.demo.designPatterns.adapter;

/**
 * Created by Administrator on 2017/9/18.
 */
public interface Target {
    /**
     * 这是源类Adaptee也有的方法
     */
    public void sampleOperation1();

    /**
     * 这是源类Adapteee没有的方法
     */
    public void sampleOperation2();
}
