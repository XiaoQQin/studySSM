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
    public void testgetUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.getUserById(1);
        System.out.println(user);
        sqlSession.close();
    }


    @Test
    public void testLog4j(){

        logger.info("info:进入testLog4f");
        logger.debug("debug:进入了debug");
        logger.error("error:进入了error");
    }


    @Test
    public void testgetUserByLimit(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("startIndex",2);
        map.put("pageSize",2);
        List<User> userByLimit = mapper.getUserByLimit(map);
        for (User user : userByLimit) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void testgetUserByRowBounds(){
        //通过使用java代码来实现分页

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        RowBounds rowBounds = new RowBounds(1, 2);
        List<User> userList = sqlSession.selectList("com.hwm.dao.UserMapper.getUserByRowBounds",null,rowBounds);

        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();


    }

}
