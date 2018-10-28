package com.asyf.demo.proxy;

/**
 * Created by Administrator on 2017/10/19.
 */
public class HelloServiceMain {
    /**
     * JDK实现动态代理需要实现类通过接口定义业务方法，对于没有接口的类，
     * 如何实现动态代理呢，这就需要CGLib了。CGLib采用了非常底层的字节码
     * 技术，其原理是通过字节码技术为一个类创建子类，并在子类中采用方
     * 法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。JDK动态代理
     * 与CGLib动态代理均是实现Spring AOP的基础。
     * <p>
     * cglib是针对类来实现代理的，他的原理是对指定的目标类生成一个子类，
     * 并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理。
     *
     * @param args
     */
    public static void main(String[] args) {
        //1.JDK动态代理
        HelloServiceProxy helloServiceProxy = new HelloServiceProxy();
        HelloService h = (HelloService) helloServiceProxy.bind(new HelloServiceImpl());
        h.sayHello("张三");

        //2.CGLIB动态代理
        /*HelloServiceCgLib helloServiceCgLib = new HelloServiceCgLib();
        HelloServiceImpl h = (HelloServiceImpl) helloServiceCgLib.getInstance(new HelloServiceImpl());
        h.sayHello("sss");*/
    }
}
