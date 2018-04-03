package com.asyf.demo.callback;

/**
 * 回调测试，原文出处http://www.cnblogs.com/xrq730/p/6424471.html
 * <p>
 * 最后是回调，回调的思想是：
 * <p>
 * 类A的a()方法调用类B的b()方法
 * 类B的b()方法执行完毕主动调用类A的callback()方法
 * <p>
 * <p>
 * 代码分析
 * <p>
 * 分析一下上面的代码，上面的代码我这里做了两层的抽象：
 * <p>
 * （1）将老师进行抽象
 * <p>
 * 将老师进行抽象之后，对于学生来说，就不需要关心到底是哪位老师询问我问题，只要我根
 * 据询问的问题，得出答案，然后告诉提问的老师就可以了，即使老师换了一茬又一茬，对我学生而言都是没有任何影响的
 * （2）将学生进行抽象
 * <p>
 * 将学生进行抽象之后，对于老师这边来说就非常灵活，因为老师未必对一
 * 个学生进行提问，可能同时对Ricky、Jack、Lucy三个学生进行提问，这样就可
 * 以将成员变量Student改为List<Student>，这样在提问的时候遍历Student列表进行提问，然后得到每个学生的回答即可
 * 这个例子是一个典型的体现接口作用的例子，之所以这么说是因为我想到有些朋
 * 友可能不太明白接口的好处，不太明白接口好处的朋友可以重点看一下这个例子，多多理解。
 * <p>
 * 总结起来，回调的核心就是回调方将本身即this传递给调用方，这样调用方就可以在调用
 * 完毕之后告诉回调方它想要知道的信息。回调是一种思想、是一种机制，至于具体如何实现，如何通过代
 * 码将回调实现得优雅、实现得可扩展性比较高，一看开发者的个人水平，二看开发者对业务的理解程度。
 * <p>
 * <p>
 * ☆☆☆☆☆☆回调的核心就是回调方将本身即this传递给调用方
 */
public class CallbackTest {

    public static void main(String[] args) {
        new CallbackTest().testCallback();
    }

    public void testCallback() {
        Student student = new Ricky();
        Teacher teacher = new Teacher(student);

        teacher.askQuestion();

    }

}
