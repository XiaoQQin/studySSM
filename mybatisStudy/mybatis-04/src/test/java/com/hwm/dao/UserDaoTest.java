package com.hwm.dao;

import com.hwm.pojo.User;
import com.hwm.utils.MybatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {

    static Logger logger=Logger.getLogger(UserDaoTest.class);

    @Test
    public void testgetUsers(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = mapper.getUsers();

        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }


    @Test
    public void testaddUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        mapper.addUser(new User(5,"hwm1","faf"));

//        User userById = mapper.getUserById(1);
//        System.out.println(userById);

        mapper.upate(new User(5,"hhhh5","ddd5"));
        sqlSession.close();
    }


}
