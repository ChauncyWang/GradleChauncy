package com.chauncy.db.entity;

/**
 * 账目
 * Created by 13969 on 2017/5/31.
 */
public class Account {
    private String id;
    private boolean cost;
    private double money;
    private String time;
    private String purpose;
    private String place;

    public Account() {
    }

    public Account(String id, boolean cost, double money, String time, String purpose, String place) {
        this.id = id;
        this.cost = cost;
        this.money = money;
        this.time = time;
        this.purpose = purpose;
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCost() {
        return cost;
    }

    public void setCost(boolean cost) {
        this.cost = cost;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
                ", cost=" + cost +
                ", money=" + money +
                ", time='" + time + '\'' +
                ", purpose='" + purpose + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
