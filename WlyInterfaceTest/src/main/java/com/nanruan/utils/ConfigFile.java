package com.nanruan.utils;

import com.nanruan.model.InterfaceName;

import java.util.ResourceBundle;

public class ConfigFile {
    private static ResourceBundle bundle=ResourceBundle.getBundle("application");
    public static String getUrl(InterfaceName name){
        String address=bundle.getString("test.url");
        String uri="";
        String testUrl;
        if(name==InterfaceName.login){
            uri=bundle.getString("login.uri");
        }
        if(name== InterfaceName.getUserList){
            uri=bundle.getString("getUserList.uri");
        }
        if(name==InterfaceName.addorder){
            uri=bundle.getString("tms.addorder.uri");
        }
        if(name==InterfaceName.dispatch){
            uri=bundle.getString("tms.dispatch.uri");
        }
        testUrl=address+uri;
        return testUrl;
    }

    public static String getCaseExcelPath(){
        String path=bundle.getString("case.excel.path");
        System.out.println("用例excel path:"+path);
        return path;
    }
}
