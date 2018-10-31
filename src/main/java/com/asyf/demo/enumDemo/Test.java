package com.asyf.demo.enumDemo;

public enum Test {
    a(A.class), b(B.class);

    private final Class<? extends Common> commonClass;

    private Test(Class<? extends Common> commonClass) {
        this.commonClass = commonClass;
    }

    public Class<? extends Common> getCommonClass() {
        return this.commonClass;
    }
}
