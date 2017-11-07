package com.asyf.demo.designPatterns.builder;

/**
 * Created by Administrator on 2017/11/7.
 */
public abstract class Builder {
    protected abstract void buildPartA();

    protected abstract void buildPartB();

    protected abstract void buildPartC();

    protected abstract Product getResult();
}
