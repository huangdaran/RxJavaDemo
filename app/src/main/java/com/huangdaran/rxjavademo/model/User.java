package com.huangdaran.rxjavademo.model;

/**
 * Created by Administrator on 2016/10/25.
 */

public class User {
    private String sex;
    private String name;
    public User(){}
    public User(String name,String sex){
        this.name = name;
        this.sex = sex;
    }

    public User getUser(){
        return this;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
