package com.hwm.mapper;

import com.hwm.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //查找所有用户
    public List<User> selectUsers();

    //增加一个用户
    public void addUser(User user);

    //删除一个用户
    public  void deleteUser(@Param("uid") int id);
}
