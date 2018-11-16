package com.asyf.demo.pinyin;

import java.util.*;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("test");
        Map<String, List> map = new HashMap<String, List>(0);
        List<Map<String, Object>> list = new ArrayList<>();
        // 根据empIds查询姓名
        Map<String, Object> emp1 = new HashMap<>();
        emp1.put("empId", 12000362);
        emp1.put("empName", "荣熙昊");
        emp1.put("level", "20");

        // 根据empIds查询姓名
        Map<String, Object> emp2 = new HashMap<>();
        emp2.put("empId", 12975271);
        emp2.put("empName", "艾晓刚");
        emp2.put("level", "20");

        Map<String, Object> emp3 = new HashMap<>();
        emp3.put("empId", 12975273);
        emp3.put("empName", "孙涤非");
        emp3.put("level", "20");

        list.add(emp3);
        list.add(emp1);
        list.add(emp2);

        for (Map<String, Object> m : list) {
            String empName = (String) m.get("empName");
            System.out.println(empName + "=====" + Pinyin4jUtil.converterToFirstSpell(empName));
        }
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String e = (String) o1.get("empName");
                String e2 = (String) o2.get("empName");
                try {
                    String eP1 = Pinyin4jUtil.converterToFirstSpell(e);
                    String eP2 = Pinyin4jUtil.converterToFirstSpell(e2);
                    int i = eP1.compareTo(eP2);
                    return i;
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                return 0;
            }
        });
        System.out.println("排序后的结果：");
        for (Map<String, Object> m : list) {
            String empName = (String) m.get("empName");
            System.out.println(empName + "=====" + Pinyin4jUtil.converterToFirstSpell(empName));
        }
    }
}
