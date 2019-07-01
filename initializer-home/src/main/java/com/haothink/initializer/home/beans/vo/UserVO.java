package com.haothink.initializer.home.beans.vo;


import java.io.Serializable;
import java.util.Date;

/**
 * @author wanghao
 * @date 2019年06月26日 18:46
 * description:
 */
public class UserVO implements Serializable {


    private static final long serialVersionUID = -3657118469466596857L;
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
}
