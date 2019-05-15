package com.nanruan.cases;

import com.alibaba.fastjson.JSONObject;
import com.nanruan.config.TestConfig;
import com.nanruan.model.InterfaceName;
import com.nanruan.utils.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ResourceBundle;

import org.testng.annotations.DataProvider;

public class LoginTest {
    private ResourceBundle bundle= ResourceBundle.getBundle("application");

    CloseableHttpResponse closeableHttpResponse;

    final static Logger Log = Logger.getLogger(LoginTest.class);

    @BeforeTest(groups = "loginTrue",description = "测试准备工作,获取httpclient")
    public void beforeTest() throws IOException {
        TestConfig.loginUrl= ConfigFile.getUrl(InterfaceName.login);


       // TestConfig.defaultHttpClient= (DefaultHttpClient) TestConfig.wrapClient(new DefaultHttpClient());
        TestConfig.caseExcelPath= ConfigFile.getCaseExcelPath();
        TestConfig.restClient=new RestClient();
    }
    @DataProvider(name = "dataInfo")
    protected Object[][] dataInfo1() throws IOException {
        Object[][] myObj = null;
        List<Map<String, String>> list = ReadExcel.readXlsx(TestConfig.caseExcelPath,"login_fahuo");
        myObj = CaseHelper.getObjArrByList(list);
        return myObj;
    }

    @DataProvider(name = "dataInfo_cy")
    protected Object[][] dataInfo_cy() throws IOException {
        Object[][] myObj = null;
        List<Map<String, String>> list = ReadExcel.readXlsx(TestConfig.caseExcelPath,"login_chenyun");
        myObj = CaseHelper.getObjArrByList(list);
        return myObj;
    }

    //@Test(groups = "loginFalse",description = "用户登录失败接口测试")
    public void loginFalse() throws  Exception{
        throw  new Exception("aaaa");
    }


    @Test(dataProvider="dataInfo",groups = "loginTrue",description = "发货方登录")
    public void loginTrue(CaseInfo c) throws IOException{
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
        String inputJson=c.getRequest();
        ///获取用例说明
       // System.out.println(c.getCaseDesc());
        ///获取用例需要的参数
       // System.out.println(c.getCaseParam());

//        Map<String,String> caseParamMap=c.getCaseParam();
//        for (String key : caseParamMap.keySet()) {
//             caseParamMap.get(key);
//             param.put(key,caseParamMap.get(key));
//        }

//        StringEntity entity=new StringEntity(param.toString(),"UTF-8");
      //  StringEntity entity=new StringEntity(inputJson,"UTF-8");
       // post.setEntity(entity);
        closeableHttpResponse = TestConfig.restClient.post(TestConfig.loginUrl,inputJson,headermap);

       // TestConfig.store=TestConfig.defaultHttpClient.getCookieStore();
        String result;
       // HttpResponse response=TestConfig.defaultHttpClient.execute(post);
       // result= EntityUtils.toString(response.getEntity(),"UTF-8");

        //验证状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 200,"status code is not 200");

        //断言响应json内容中name和job是不是期待结果
        result = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
       // JSONObject responseJson = JSON.parseObject(result);

        //获取执行用例需要的前置条件
        // System.out.println(c.getCasePreset());
        //System.out.println(TestConfig.loginUrl);

        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);
        Map mapData =(Map) map.get("data");
        String token="";
        token=mapData.get("token").toString();
        //System.out.println( token);

        Assert.assertEquals(map.get("code").toString(),"0","登录失败");
        Assert.assertNotNull(token,"没有返回token");
        TestConfig.token_fh=token;

        Map userInfo=(Map)mapData.get("userInfo");
        TestConfig.userCompanyid_fh= userInfo.get("userCompanyid").toString();//将用户公司ID存起来
    }

    @Test(dataProvider="dataInfo_cy",groups = "loginCY",description = "承运方登录")
    public void loginCYTest(CaseInfo c) throws IOException{
        System.out.println("当前接口:"+TestConfig.loginUrl);
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
        String inputJson=c.getRequest();
        System.out.println("发送请求:"+inputJson);
        closeableHttpResponse = TestConfig.restClient.post(TestConfig.loginUrl,inputJson,headermap);
        String result;
        //验证状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 200,"status code is not 200");

        //断言响应json内容中name和job是不是期待结果
        result = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);
        Map mapData =(Map) map.get("data");
        String token="";
        token=mapData.get("token").toString();
        //System.out.println( token);

        Assert.assertEquals(map.get("code").toString(),"0","登录失败");
        Assert.assertNotNull(token,"没有返回token");
        TestConfig.token_cy=token;

        Map userInfo=(Map)mapData.get("userInfo");
        TestConfig.userCompanyid_cy= userInfo.get("userCompanyid").toString();//将用户公司ID存起来
    }

}
