package com.mzl.incomeexpensemanagesystem1.entity;

import java.io.Serializable;

/**
 * @ClassName :   Admin
 * @Description: 管理员
 * @Author: 21989
 * @CreateDate: 2020/7/7 17:22
 * @Version: 1.0
 */
public class Admin implements Serializable {

    private int aid;  //管理员id
    private String adminname;  //管理员登录名
    private String password;  //密码

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAid() {
        return aid;
    }

    public String getAdminname() {
        return adminname;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", adminname='" + adminname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
