package com.hwm.mapper;

import com.hwm.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {


    public UserMapperImpl() {
    }

    public List<User> selectUsers() {
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        User user=new User(8,"hwm7","pwd7");

        addUser(user);

        deleteUser(3);

        return mapper.selectUsers();
    }

    public void addUser(User user) {
        getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    public void deleteUser(int id) {
        getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }
}
