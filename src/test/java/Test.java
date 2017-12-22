/**
 * Created by Administrator on 2017/12/22.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("test");
        long start = System.currentTimeMillis();
        long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("程序耗时：" + (end - start) + "ms");
    }
}
