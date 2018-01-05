package test;

/**
 * Created by Administrator on 2018/1/5.
 */
public class Super {

    //消除类中可被覆盖方法的自用性
    public Super() {
        //将课继承的方法移出到私有的辅助方法，调用私有辅助方法 代替 调用可继承的方法
        //overrideMe();
        assist();
    }

    public void overrideMe() {
        assist();
        System.out.println("super");
    }

    private void assist() {
        System.out.println("辅助方法,从可继承的方法移出（该可继承的方法再new的时候需要调用）");
    }
}
