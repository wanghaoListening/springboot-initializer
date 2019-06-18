package com.haothink.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wanghao
 * @date 2019年06月18日 19:11
 * description:
 */
public class UserDTO implements Serializable {


    private static final long serialVersionUID = -2833216095559682259L;

    private Long id;

    private String name;

    private String password;

    private String email;

    private Date gmtcreate;


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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gmtcreate=" + gmtcreate +
                '}';
    }
}
