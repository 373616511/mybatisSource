package com.asyf.demo.designPatterns.singleton;

/**
 * 面先说一下环境，比如现在有两个类,A和B,两个类都是单例类，这个时候如果A有个B的实例变量，B有个A的实例变量，
 * 会发生什么情况呢？开始我以为会出现栈溢出。但是让我迷惑的是，居然没问题。只是其中一个类的 实例变量会是NULL。
 * 下面看代码。
 *
 * @author zhang_zengmin
 */
public class TestAB {

    /**
     * @param args
     */
    public static void main(String[] args) {

        B b = B.getInstance();
        b.test();
        System.out.println("==========");
        A a = A.getInstance();
        a.test();

        // A a = A.getInstance();
        // a.test();
        // System.out.println("==========");
        // B b = B.getInstance();
        // b.test();
        /**
         1.类Test的main()方法的B.getInstance()生成的invokestatic指令触发了类B的初始化
         2.执行类B的<clinit>方法的过程中，显式调用了B自己的<init>方法（static b = new B()）
         3.B的<init>方法中，调用A.getInstance()生成的invokestatic指令触发了A的初始化（private A a = A.getInstance()）
         4.执行A的<clinit>方法的过程中调用了A自己的<init>方法（static a = new A()）
         5.类A的<init>方法中需要调用B.getInstance()，但是虚拟机中一个类（<class,classloader>为一个类）只会初始化一次，
         因此不会再触发B的初始化，既不会再执行B.<clinit>方法。
         6.那由a.<init>触发的B.getInstance()被执行，输出第一行“B被调用”，B.getInstance()方法结束。\
         虽然这时候B的初始化阶段尚未结束，但是解析阶段已经完成，所以getInstance()方法可以被正确执行，
         但这时候静态字段static B b仍然为null（注意，b.<init>方法还没完呢），所以这个B.getInstance()
         方法返回值为null，所以A.b为null。
         7.A.<clinit>方法结束，第3步的invokestatic指令正式执行，即A.getInstance()被执行，
         输出第二行“A被调用”，返回A的实例，这时候B.a不为null了。
         8.B.<clinit>方法结束，第1步的invokestatic指令正式执行，即B.getInstance()被执行，输出第三行“B被调用”
         9.B.test()方法被执行，输出B.a的toString()方法结果，即第四行“A@35ce36”。
         10.A.getInstance()方法被执行，输出第五行“A被调用”。
         11.A.test()方法被执行，输出null，即A.b的值。
         */
    }

}
