package com.hwm.dao;

import com.hwm.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //获取全部用户
    List<User> getUserList();


    //插入一个用户
    int addUser(User user);

    //修改用户
    int updateUser(User user);

    void deleteUser(int id);



}
