package com.hwm.demo;

public class Proxy implements rent {

    private HouseOwneer houseOwneer;


    public Proxy(HouseOwneer houseOwneer) {
        this.houseOwneer = houseOwneer;
    }

    public void rent() {
        see();
        houseOwneer.rent();

        hetong();
        fare();
    }

    //看房
    public void see(){
        System.out.println("中介带你去看房");
    }

    public void hetong(){
        System.out.println("中介和你签合同");
    }

    public void fare(){
        System.out.println("收中介费");
    }
}
