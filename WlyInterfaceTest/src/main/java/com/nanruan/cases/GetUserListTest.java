package com.nanruan.cases;

import com.alibaba.fastjson.JSONObject;
import com.nanruan.config.TestConfig;
import com.nanruan.model.InterfaceName;
import com.nanruan.utils.ConfigFile;

import com.nanruan.utils.RestClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class GetUserListTest {

    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    @BeforeClass
    public void beforeClass(){
        TestConfig.getUserListUrl=ConfigFile.getUrl(InterfaceName.getUserList);
    }

    @Test(dependsOnGroups = {"loginTrue"})
    public void getUserListInfo() throws IOException {
        System.out.println(TestConfig.getUserListUrl);
        System.out.println(getResult());

    }
    private String getResult() throws IOException {
//        currentPage: 1
//        id: 1969
//        name: ""
//        order: ""
//        pageSize: 10
//        sortBy: ""
//        user: 1;
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
        headermap.put("Authorization","Bearer "+TestConfig.token_fh);


       // HttpPost post=new HttpPost(TestConfig.getUserListUrl);
        JSONObject param=new JSONObject();
        param.put("id","1969");
        param.put("user","1");
        restClient=new RestClient();
        closeableHttpResponse=restClient.post(TestConfig.getUserListUrl,param.toString(),headermap);
       // post.setHeader("content-type","application/json");
       // post.setHeader("Authorization","Bearer "+TestConfig.token);
       // StringEntity entity=new StringEntity(param.toString(),"UTF-8");
       // post.setEntity(entity);
       // HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        String result;
       // result= EntityUtils.toString(response.getEntity(),"UTF-8");
        result=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        return result;
    }
}
