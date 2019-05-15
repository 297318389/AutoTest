package com.nanruan.cases.tms;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nanruan.config.TestConfig;
import com.nanruan.utils.Json2Map;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 签收管理
 */
public class SignManageTest {
    private ResourceBundle bundle= ResourceBundle.getBundle("application");

    CloseableHttpResponse closeableHttpResponse;

    final static Logger Log = Logger.getLogger(SignManageTest.class);

    @BeforeClass
    public void beforeClass(){
        TestConfig.signManageUrl=bundle.getString("test.url")+bundle.getString("tms.signManage.uri");//签收管理查询
        TestConfig.signUrl=bundle.getString("test.url")+bundle.getString("tms.sign.uri");//签收
    }

    //签收管理查询
    //1.按普通订单接收后返回的supplierOrderID通过订单查询接口得到承运方客户订单里的单据编号.
    //2.再根据单据编号检索待签收订单

    //客户订单查询测试  接收成功,会生成新的客户订单,且订单id与supplierOrderID一致 ,dependsOnGroups = {"receiverTrue"}
    @Test(description = "客户订单查询")
    public void  checktrackingTest() throws IOException, InterruptedException {
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+ TestConfig.token_cy);

        JSONObject param=new JSONObject();
        param.put("pactcode",TestConfig.pactCode);
        System.out.println("发送请求:"+param.toString());
        String result;

        closeableHttpResponse=TestConfig.restClient.post(TestConfig.checkUrl,param.toString(),headermap);
        result= EntityUtils.toString(closeableHttpResponse.getEntity());
        System.out.println("返回结果:"+result);

        Map<String, Object> map= Json2Map.json2Map(result);

        Assert.assertEquals(map.get("code").toString(),"0","查询失败");
        Map mapData =(Map) map.get("data");
        Assert.assertNotNull(mapData,"data为空");

        JSONArray beanListArray = (JSONArray) mapData.get("beanList");
        Assert.assertNotNull(beanListArray,"beanList为空");
        Assert.assertNotEquals(beanListArray.size(),0,"beanList没有内容");
        boolean flag=false;
        for(Object bean:beanListArray){
            String orderID= ((Map) bean).get("id").toString();
            if(orderID.equals(TestConfig.supplierOrderID)){
                TestConfig.supplierCode=(String) ((Map) bean).get("code");
                TestConfig.supplierPactCode=(String) ((Map) bean).get("pactCode");
                flag=true;
                break;
            }
        }
        System.out.println("supplierOrderID:"+TestConfig.supplierOrderID);
        System.out.println("supplierCode:"+TestConfig.supplierCode);
        System.out.println("supplierPactCode:"+TestConfig.supplierPactCode);
        Assert.assertTrue(flag,"检索不到已接收订单");

    }



    //dependsOnMethods = {"checktrackingTest"},
    @Test(description = "签收查询")
    public void signManageListTest() throws IOException {
        System.out.println("当前接口:"+TestConfig.signManageUrl);
        HashMap<String,String> headermap=new HashMap<String, String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+ TestConfig.token_cy);
        JSONObject param=new JSONObject();
        param.put("code",TestConfig.supplierCode+"-1"); //查运输订单
        param.put("pactcode",TestConfig.supplierPactCode);
        System.out.println("发送请求:"+param.toString());
        String result;

        closeableHttpResponse=TestConfig.restClient.post(TestConfig.signManageUrl,param.toString(),headermap);
        result= EntityUtils.toString(closeableHttpResponse.getEntity());
        System.out.println("返回结果:"+result);

        Map<String, Object> map= Json2Map.json2Map(result);

        Assert.assertEquals(map.get("code").toString(),"0","查询失败");
        Map mapData =(Map) map.get("data");
        Assert.assertNotNull(mapData,"data为空");
    }

    //承运方运输订单签收dependsOnMethods = {"signManageListTest"},
    @Test(groups = "sign",description = "承运方运输订单签收")
    public void signTest() throws IOException {
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+ TestConfig.token_cy);

        JSONObject param=new JSONObject();
        param.put("orderID",TestConfig.supplierOrderID);
        param.put("signType",1); //有什么用？
        System.out.println("发送请求:"+param.toString());
        String result;

        closeableHttpResponse=TestConfig.restClient.post(TestConfig.signUrl,param.toString(),headermap);
        result= EntityUtils.toString(closeableHttpResponse.getEntity());
        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);

        Assert.assertEquals(map.get("code").toString(),"0","签收失败");
        Assert.assertTrue(Integer.parseInt(map.get("data").toString())>0,"没有签收订单");
    }

    //@todo 批量签收，异常签收
}
