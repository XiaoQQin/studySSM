package com.hwm.dao;

import com.hwm.pojo.User;
import com.hwm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class myTest {
    @Test
    public void test(){


        //测试二级缓存，二级缓存在mapper中生效
        //只有一级缓存(sqlSession)关闭，才回存取到二级缓存里

        SqlSession sqlSession1 = MybatisUtils.getSqlSession();
        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        User user1 = mapper1.QueryUserById(1);
        System.out.println(user1);
        sqlSession1.close();//注意sqlSession1关闭，说明一级缓存关闭



        SqlSession sqlSession2 = MybatisUtils.getSqlSession();
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.QueryUserById(1);
        System.out.println(user2);
        sqlSession2.close();


        System.out.println(user1==user2);

    }
}
