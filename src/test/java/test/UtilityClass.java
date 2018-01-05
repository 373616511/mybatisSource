package test;

import com.mysql.jdbc.AssertionFailedException;

/**
 * Created by Administrator on 2017/12/22.
 */
public class UtilityClass {
    //suppress default constructor for noninstantiability
    //为了不可实例化阻止默认的构造器
    //该类不可以被继承
    private UtilityClass() {
        throw new AssertionError();
    }
}
