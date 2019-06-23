package com.haothink.initializer.home.beans.po;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

/**
 * @author wanghao
 * @date 2019年06月23日 15:16
 * description:
 */
public class UserPO {

    @Pattern(regexp = "[a-zA-Z0-9_\u4e00-\u9fa5]{2,50}",message = "用户名格式不正确")
    private String name;

    @Pattern(regexp = "[A-Za-z][0-9A-Za-z]{4,15}",message = "密码格式不正确")
    private String password;

    @Email(message="email格式不正确")
    private String email;


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

    @Override
    public String toString() {
        return "UserPO{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
