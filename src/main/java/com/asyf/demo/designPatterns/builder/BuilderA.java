package com.asyf.demo.designPatterns.builder;

/**
 * Created by Administrator on 2017/11/7.
 */
public class BuilderA extends Builder {
    private Product product = new Product();

    @Override
    protected void buildPartA() {
        product.add("partA");
    }

    @Override
    protected void buildPartB() {
        product.add("partB");
    }

    @Override
    protected void buildPartC() {
        product.add("partC");
    }

    @Override
    protected Product getResult() {
        return product;
    }
}
