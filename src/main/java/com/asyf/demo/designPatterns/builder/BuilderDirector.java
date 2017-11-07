package com.asyf.demo.designPatterns.builder;

/**
 * Created by Administrator on 2017/11/7.
 */
public class BuilderDirector {

    private Builder builder = null;

    public BuilderDirector(Builder builder) {
        this.builder = builder;
    }

    public Product build() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }

}
