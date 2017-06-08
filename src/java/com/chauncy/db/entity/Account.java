package com.chauncy.db.entity;

import java.io.Serializable;

/**
 * 账目
 * Created by 13969 on 2017/5/31.
 */
public class Account implements Serializable{
    private String id;
    private boolean pay;
    private double money;
    private String payTime;
    private String purpose;
    private String place;

    public Account() {
    }

    public Account(String id, boolean cost, double money, String time, String purpose, String place) {
        this.id = id;
        this.pay = cost;
        this.money = money;
        this.payTime = time;
        this.purpose = purpose;
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPay() {
        return pay;
    }

    public void setPay(boolean pay) {
        this.pay = pay;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", pay=" + pay +
                ", money=" + money +
                ", payTime='" + payTime + '\'' +
                ", purpose='" + purpose + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
