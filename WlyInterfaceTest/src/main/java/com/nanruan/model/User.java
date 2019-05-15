package com.nanruan.model;

import lombok.Data;

@Data
public class User {
    private  int id;
    private String userName;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
