package com.hwm.config;

import com.hwm.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration //这个也会被Spring容器中注册，
//Configuration意思是一个配置类，相当于Beans

@ComponentScan("com.hwm.pojo") //相当于扫描包

//相当于之前的import标签，引入其他配置
@Import(MyConfig2.class)
public class MyConfig {


    //注册一个bean,就相当于之前的bean标签，id为这个方法的名字，class属性为返回值
    @Bean
    public User getUser(){
        return new User();
    }
}
