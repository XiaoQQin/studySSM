package com.hwm.dao;

import com.hwm.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //获取全部用户
    List<User> getUserList();

    //根据id查询用户
    User getUserById(int id);

    //插入一个用户
    int addUser(User user);
    //使用Map
    int addUser2(Map<String,Object> map);
    //修改用户
    int updateUser(User user);

    void deleteUser(int id);

    //模糊查询

    List<User> getUserLike(String value);

}
