package com.hwm.demo4;

public class Client {
    public static void main(String[] args) {
        UserService u1=new UserServiceImpl1();
        UserService u2=new UserServiceImpl2();


        ProxyInnovationHandler pih=new ProxyInnovationHandler();

        pih.setTarget(u2);

        UserService proxy = (UserService)pih.getProxy();

        proxy.add();
    }
}
