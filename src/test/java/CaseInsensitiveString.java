import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */
public final class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        if (s == null)
            throw new NullPointerException();
        this.s = s;
    }

    /*@Override
    public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString) {
            return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
        }
        if (o instanceof String) {
            return s.equalsIgnoreCase((String) o);
        }
        return false;
    }*/

    @Override
    public boolean equals(Object o) {
        System.out.println("比较");
        return o instanceof CaseInsensitiveString &&
                ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
    }

    public static void main(String[] args) {
        CaseInsensitiveString c = new CaseInsensitiveString("test");
        String s = "test";
        System.out.println(c.equals(s));
        System.out.println(s.equals(c));
        List<CaseInsensitiveString> list = new ArrayList<>();
        list.add(c);
        System.out.println(list.contains(c));
    }

}
