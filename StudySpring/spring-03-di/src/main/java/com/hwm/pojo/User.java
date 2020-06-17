package com.hwm.pojo;

public class User {
    private String name;
    private int age;

    //无参构造函数，使用set注入，可以使用p命名空间
    public User() {
    }

    //可以适用c命名空间
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
