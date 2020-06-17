package com.hwm.pojo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


//这里注解的意思，就是陪spring接管了，注册到容器中
@Component
public class User {

    @Value("hwmm")//注入属性值
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
