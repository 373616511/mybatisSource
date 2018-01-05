package test;

import java.util.Date;

/**
 * 初始化顺序依次是：（静态变量、静态初始化块）–>（变量、初始化块）–> 构造器；
 * 如果有父类，
 * 则顺序是：父类static方法 –> 子类static方法 –> 父类构造方法- -> 子类构造方法
 * <p>
 * 子类的变量初始化在超类的构造方法之后
 */
public class Sub extends Super {
    private String a = "a";
    private static String b = "static";

    static {
        System.out.println("子类静态代码块" + b);
    }

    {
        System.out.println("子类代码块" + a);
    }

    public Sub() {
        a = "b";
    }


    @Override
    public void overrideMe() {
        System.out.println("sub");
        //如果在此处对a进行操作，超类构造方法调用了此方法，此时a还没有被初始化，报错NullPointerException
        //不要轻易重写超类构造函数调用的方法
        System.out.println(a);
    }

    //调用两次子类的构造器
    public static void main(String[] args) {
        Super s = new Sub();
        s.overrideMe();
    }
}
