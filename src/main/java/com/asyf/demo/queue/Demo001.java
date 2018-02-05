package com.asyf.demo.queue;

import java.util.LinkedList;
import java.util.Queue;

public class Demo001 {


    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1); //进队
        queue.offer(2);
        queue.offer(3);
        for (Integer integer : queue) {
            System.out.println(integer);
        }
        System.out.println("------------------------");
        System.out.println("poll=" + queue.poll());//获取第一个元素并删除
        for (Integer integer : queue) {
            System.out.println(integer);
        }
        System.out.println("------------------------");
        System.out.println("element=" + queue.element());//获取第一个元素
        for (Integer integer : queue) {
            System.out.println(integer);
        }
        System.out.println("------------------------");
        System.out.println("peek=" + queue.peek());//获取第一个元素
        for (Integer integer : queue) {
            System.out.println(integer);
        }
        ;
    }
}
