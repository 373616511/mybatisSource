package com.asyf.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.asyf.mybatis.mapper.UserMapper;
import com.asyf.mybatis.model.User;

public class Test {

    public static void main(String[] args) {
        SqlSession s = null;
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //初始化 sqlSessionFactory 读取 mybatis-config.xml 配置文件---configuration
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //openSessionFromDataSource---executor---sqlSession
            s = sqlSessionFactory.openSession();
            System.out.println("SqlSession--" + s);
            UserMapper u = s.getMapper(UserMapper.class);
            int num = 0;
            while (num++ < 1000000) {
                //
                User u2 = new User();
                //u2.setId("27187");
                u2.setCreateDate(new Timestamp(System.currentTimeMillis()));
                u2.setName("ceshi" + num);
                u2.setUsername("username");
                u2.setAge("23");
                u2.setEmail("sahjhdja");
                u.insert(u2);
            }
            //
            User param = new User();
            param.setId("1");
            //User user = u.selectByPrimaryKey(param);
            //System.out.println(user == null ? "user对象null" : user.toString());
            PageHelper.startPage(1, 3);
            List<User> users = u.findAllUser(new HashMap<String, String>());

            for (User user : users) {
                System.out.println(user + "--");
            }
            PageInfo<User> page = new PageInfo<>(users);
            System.out.println(page.getPageSize());
            s.commit();
            // List<User> users = u.listAllUser();
            //System.err.println("----------" + users.size());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

}
