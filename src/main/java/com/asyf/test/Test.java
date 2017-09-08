package com.asyf.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.asyf.mapper.UserMapper;
import com.asyf.model.User;

public class Test {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession s = sqlSessionFactory.openSession();
        System.out.println("SqlSession--" + s);
        UserMapper u = s.getMapper(UserMapper.class);
        //User user = u.selectByPrimaryKey(1);
        //System.out.println(user.toString());
        List<User> users = u.listAllUser();
        System.err.println("----------" + users.size());

    }

}
