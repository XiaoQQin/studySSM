package com.hwm.demo;

public class client {
    public static void main(String[] args) {

        //房东出租房子
        HouseOwneer houseOwneer=new HouseOwneer();
        //代理，中介帮房东出租房子，一般会有附加操作
        Proxy proxy=new Proxy(houseOwneer);

        //不用面对房东，直接面对中介
        proxy.rent();
    }
}
