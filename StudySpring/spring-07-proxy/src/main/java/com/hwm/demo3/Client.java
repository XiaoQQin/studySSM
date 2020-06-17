package com.hwm.demo3;

public class Client {

    public static void main(String[] args) {
        //真实角色
        HouseOwner houseOwner=new HouseOwner();

        //代理角色

        ProxyInnovationHandler pi = new ProxyInnovationHandler();

        //通过调用程序处理角色
        pi.setRent(houseOwner);
        Rent proxy = (Rent) pi.getProxy();
        proxy.rent();
    }
}
