package algorithm.permutation;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/1/9.
 */
public class permutation {

    private static int a = 0;

    public static void permute(Object[] array, int start) {
        a++;
        if (start == array.length) {  // 输出
            System.out.println(Arrays.toString(array));
        } else
            for (int i = start; i < array.length; ++i) {
                swap(array, start, i);  //  交换元素
                permute(array, start + 1);  //交换后，再进行全排列算法
                swap(array, start, i);  //还原成原来的数组，便于下一次的全排列
            }
    }

    private static void swap(Object[] array, int s, int i) {
        Object t = array[s];
        array[s] = array[i];
        array[i] = t;
    }

    public static void main(String[] args) {
        Object[] array = new Object[]{"a", "b", "c","d"};
        permute(array, 0);
        System.out.print("permute()方法执行次数：" + a);
    }
}
