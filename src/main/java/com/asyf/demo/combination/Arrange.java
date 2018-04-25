package com.asyf.demo.combination;


import java.util.ArrayList;
import java.util.List;

public class Arrange {
    public static void main(String[] args) {
        Arrange arrange = new Arrange();
        List<Character> data = new ArrayList<Character>();
        data.add('a');
        data.add('b');
        data.add('c');
        data.add('d');

        //输出A(n,n)的全排列
        for (int i = 1; i <= data.size(); i++) {
            //arrange.arrangeSelect(data, new ArrayList<Character>(), i);
        }
        arrange.arrangeSelect(data, new ArrayList<Character>(), 4);
    }

    /**
     * 计算A(n,k)
     *
     * @param data
     * @param target
     * @param k
     */
    public <E> void arrangeSelect(List<E> data, List<E> target, int k) {
        List<E> copyData;
        List<E> copyTarget;
        if (target.size() == k) {
            for (E i : target) {
                System.out.print(i);
            }
            System.out.println();
        }

        for (int i = 0; i < data.size(); i++) {
            copyData = new ArrayList<E>(data);
            copyTarget = new ArrayList<E>(target);
            System.out.println(i + "===========" + copyData.get(i));
            copyTarget.add(copyData.get(i));
            copyData.remove(i);
            arrangeSelect(copyData, copyTarget, k);
        }
    }

}