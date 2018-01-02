package com.asyf.demo.jdk.object;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Created by Administrator on 2018/1/2.
 */
public class Stack implements Cloneable {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    //重写clone方法。递归地调用clone，解决数组的相同实例的问题
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Stack stack = (Stack) super.clone();
        stack.elements = elements.clone();
        return stack;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Stack s = new Stack();
        Stack s2 = (Stack) s.clone();
        for (int i = 0; i < 10; i++) {
            s2.push(i);
            s.push("---" + i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(s2.pop());
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(s.pop());
        }
    }
}
