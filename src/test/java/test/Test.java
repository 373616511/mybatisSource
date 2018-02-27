package test;

/**
 * Created by Administrator on 2017/12/22.
 */
public class Test {
    public static void main(String[] args) {
        String a = "1(3),2(2),3(3)";
        String[] strs = a.split(",");
        for (String s : strs) {
            System.out.println(s.substring(0, s.indexOf("(")) + "----" + s.substring(s.indexOf("(") + 1, s.indexOf("(") + 2));
        }
        short s1 = 1;
        //s1 = s1 + 1;
        s1 += 1;
        /*System.out.println("test");
        long start = System.currentTimeMillis();
        long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("程序耗时：" + (end - start) + "ms");*/
    }
}
