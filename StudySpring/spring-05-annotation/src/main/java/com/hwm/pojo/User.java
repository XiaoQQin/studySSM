package com.hwm.pojo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


//等价于<bean id="user" class="com.hwm.pojo.User"/>
@Component
@Scope("singleton")
public class User {

    //等价于<property name="name" value="hwm"/>,也可以在set方法上使用
    @Value("hwm")
    public String name;

    public void setName(String name) {
        this.name = name;
    }
}
