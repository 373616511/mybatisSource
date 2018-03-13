package com.asyf.demo.collection.order;

public class Test {
    public static void main(String[] args) {
        int a[] = {1, 3, 4, 5, 32, 56, 43};
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i; j < a.length - 1; j++) {
                int temp = 0;
                if (a[i] > a[j + 1]) {
                    temp = a[j + 1];
                    a[j + 1] = a[i];
                    a[j] = temp;
                }
            }
        }
        for (int i : a) {
            System.out.println(i);
        }
    }
}
