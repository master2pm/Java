package com.testplay.user.entity;

import java.util.Date;

public class User {

    public String id;

    public String username;

    public String password;

    public String realname;

    public int phone;

    public Date bith;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Date getBith() {
        return bith;
    }

    public void setBith(Date bith) {
        this.bith = bith;
    }
}
