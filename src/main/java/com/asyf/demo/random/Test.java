package com.asyf.demo.random;

/**
 * Created by Administrator on 2017/10/13.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        //120
        String a = "230232568963";
                    //418477175805
        int num = 0;
        while (true) {
            num++;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 4; i++) {
                String random = getRandom();
                //Thread.sleep(1000);
                //System.err.println(random);
                sb.append(random);
            }
            System.err.println(sb.toString() + "****" + num);
            if (sb.toString().equals(a)) {
                sb.append(getRandom());
                System.err.println(sb.toString() + "------" + num);
                break;
            }
        }

    }

    //随机生成三位数
    public static String getRandom() {
        int number = 0;
        while (true) {
            number = (int) (Math.random() * 1000);
            if (number >= 100 && number < 1000) {
                break;
            }
        }
        return String.valueOf(number);
    }
}
