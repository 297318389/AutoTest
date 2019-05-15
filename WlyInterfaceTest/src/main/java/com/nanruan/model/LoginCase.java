package com.nanruan.model;

import lombok.Data;
import org.omg.PortableInterceptor.ServerRequestInfo;

@Data
public class LoginCase {
    private  int id;
    private String username;
    private String password;
    private String expected;

    @Override
    public String toString() {
        return "LoginCase{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", expected='" + expected + '\'' +
                '}';
    }
}
