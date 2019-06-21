package com.haothink.initializer.biz.bean.dos;

import java.io.Serializable;
import java.util.Date;

public class UserDO implements Serializable {

    private Long id;

    private String name;

    private String password;

    private String email;

    private Date gmtcreate;

    private Date gmtmodify;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getGmtcreate() {
        return gmtcreate;
    }

    public void setGmtcreate(Date gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    public Date getGmtmodify() {
        return gmtmodify;
    }

    public void setGmtmodify(Date gmtmodify) {
        this.gmtmodify = gmtmodify;
    }


    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gmtcreate=" + gmtcreate +
                ", gmtmodify=" + gmtmodify +
                '}';
    }
}