package com.hwm.pojo;

public class Address {

    private String adress;

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAdress() {
        return adress;
    }

    @Override
    public String toString() {
        return "Address{" +
                "adress='" + adress + '\'' +
                '}';
    }
}
