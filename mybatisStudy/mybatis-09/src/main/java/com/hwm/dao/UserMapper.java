package com.hwm.dao;

import com.hwm.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User QueryUserById(@Param("uid")int id);
}
