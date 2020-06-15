package com.hwm.dao;

import com.hwm.pojo.User;
import com.hwm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {
    @Test
    public void test(){
        //第一步：获取SqlSesion对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //方式一：getMapper执行SQL
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        List<User> userList = userMapper.getUserList();

        //方式二：通过

        List<User> userList = sqlSession.selectList("com.hwm.dao.UserMapper.getUserList");
        for(User user:userList){
            System.out.println(user);
        }
        //关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void testgetUserById(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.getUserById(1);
        System.out.println(user);
        sqlSession.close();
    }

    //增删改需要提交事务
    @Test
    public void testaddUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int res=mapper.addUser(new User(5,"wdw","daf"));

        if(res>0)System.out.println("插入成功");

        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testaddUser2(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userID",6);
        map.put("username","wdafafa");
        map.put("password","pwd");
        userMapper.addUser2(map);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testgetUserLike(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.getUserLike("李");
        for(User user:users){
            System.out.println(user);
        }
        sqlSession.close();
    }
    //测试更新
    @Test
    public void testUpdateUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.updateUser(new User(1,"hwm","hwm"));

        //事务提交
        sqlSession.commit();
        sqlSession.close();
    }
    //测试删除
    @Test
    public void testDeleteUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser(4);
        sqlSession.commit();
        sqlSession.close();
    }


}
