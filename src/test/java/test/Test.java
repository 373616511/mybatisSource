package test;

/**
 * Created by Administrator on 2017/12/22.
 */
public class Test {
    public static void main(String[] args) {
        String aa = "1(3),2(2),3(3)";
        String[] strs = aa.split(",");
        for (String s : strs) {
            System.out.println(s.substring(0, s.indexOf("(")) + "----" + s.substring(s.indexOf("(") + 1, s.indexOf("(") + 2));
        }
        //short s1 = 1;
        //s1 = s1 + 1;
        //s1 += 1;
        /*System.out.println("test");
        long start = System.currentTimeMillis();
        long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("程序耗时：" + (end - start) + "ms");*/
        int a = 5, b = 10;
        swap(a, b);
        System.out.println("a=" + a + ",b=" + b);
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());
        String strtest = "abcde";
        StringBuffer sb = new StringBuffer(strtest);
        sb.reverse();
        String s = null;
        assert s != null ? true : false;
        assert false;
        System.out.println("end");
        System.out.println("反转后的字符串：" + sb.toString());

    }

    public static void swap(int x, int y) {
        int temp = x;
        x = y;
        y = temp;
    }
}
