package com.example.springboot.controller;

import java.io.Serializable;
import java.util.Date;


public class UserResp implements Serializable {

    private static final long serialVersionUID = 7632299590737453926L;
    private String name;
    private long id;
    private Date now;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
