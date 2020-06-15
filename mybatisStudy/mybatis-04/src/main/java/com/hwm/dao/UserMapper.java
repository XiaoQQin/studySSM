package com.hwm.dao;

import com.hwm.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();
    @Select("select * from user where id=#{id}")
    User getUserById(@Param("id") int id);
    @Insert("insert into user(id,name,pwd) values(#{id},#{name},#{password})")
    int addUser(User user);

    @Update({"update user set name=#{name},pwd=#{password} where id=#{id}"})
    int upate(User user);
}
