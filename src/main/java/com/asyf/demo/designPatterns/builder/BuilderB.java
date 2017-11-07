package com.asyf.demo.designPatterns.builder;

/**
 * Created by Administrator on 2017/11/7.
 */
public class BuilderB extends Builder {
    private Product product = new Product();

    @Override
    protected void buildPartA() {
        product.add("partX");
    }

    @Override
    protected void buildPartB() {
        product.add("partY");
    }

    @Override
    protected void buildPartC() {
        product.add("partZ");
    }

    @Override
    protected Product getResult() {
        return product;
    }
}
