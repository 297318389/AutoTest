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
 * 订单接收
 */
public class ReceiveTest {
    private ResourceBundle bundle= ResourceBundle.getBundle("application");

    CloseableHttpResponse closeableHttpResponse;

    final static Logger Log = Logger.getLogger(ReceiveTest.class);

    @BeforeClass
    public void beforeClass(){
        TestConfig.receiveUrl=bundle.getString("test.url")+bundle.getString("tms.receive.uri");//接收普通订单
        TestConfig.orderacceptedlistUrl=bundle.getString("test.url")+bundle.getString("tms.orderacceptedlist.uri");//待接收普通订单
        TestConfig.receiveCombineOrdersUrl=bundle.getString("test.url")+bundle.getString("tms.receiveCombineOrders.uri");//接收拼车单
        TestConfig.orderacceptedcarlistUrl=bundle.getString("test.url")+bundle.getString("tms.orderacceptedcarlist.uri");//已接收拼车单
    }

    //切换到承运方登录,做接收操作 dependsOnGroups = {"consignmentOrder"},
    @Test(description = "承运方登录")
    public void supplierLoginTest() throws IOException {
        System.out.println("当前接口:"+TestConfig.loginUrl);
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json");

        JSONObject param=new JSONObject();
        param.put("userAccount","jxt");
        param.put("userPassword","1234");
        param.put("checked","true");
        param.put("forceLogin","false");

        System.out.println("发送请求:"+param.toString());

        String result;
        closeableHttpResponse = TestConfig.restClient.post(TestConfig.loginUrl,param.toString(),headermap);
        result= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);
        Map mapData =(Map) map.get("data");
        String token="";
        token=mapData.get("token").toString();

        Assert.assertEquals(map.get("code").toString(),"0","承运方登录失败");
        Assert.assertNotNull(token,"没有返回token");
        TestConfig.token_cy=token;
    }

    //,dependsOnGroups = {"loginCY","consignmentOrder"}
    @Test(groups="receiverTrue",description = "接收普通订单")
    public void receiveTest() throws IOException, InterruptedException {
        System.out.println("当前接口:"+TestConfig.receiveUrl);
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_cy);

        JSONObject param=new JSONObject();
        param.put("orderID",((HashMap)TestConfig.addedOrders[0]).get("orderID").toString());
        param.put("accept","1");//accept:1 接收 0 拒绝
        System.out.println("发送请求:"+param.toString());

        String result;
        closeableHttpResponse=TestConfig.restClient.post(TestConfig.receiveUrl,param.toString(),headermap);
        result=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);

        Assert.assertEquals(map.get("status").toString(),"0","普通订单接收失败");

        Map mapData =(Map) map.get("data");
        Assert.assertNotNull(mapData,"data为空");
        String supplierOrderID=mapData.get("supplierOrderID").toString();
        Assert.assertNotNull(supplierOrderID,"supplierOrderID为空");
        TestConfig.supplierOrderID=supplierOrderID;
        System.out.println("接收后新ID:"+supplierOrderID);

        Thread.sleep(3000);

        //接收成功后,承运方会生成一条客户订单,其index_srcOrderID是被接收订单的ID,index_ID是返回的supplierOrderID
        /*SqlSession session = DatabaseUtil.getSqlSession();
        ISqlMapper sqlMapper=session.getMapper(ISqlMapper.class);

        OrderIndex index=new OrderIndex();
        index.setIndex_ID(Long.parseLong(supplierOrderID));
        index.setIndex_SrcOrderID(Long.parseLong(TestConfig.orderID));

        System.out.println(supplierOrderID+" "+TestConfig.orderID);
        // 查询单个对象
        OrderIndex orderIndex=sqlMapper.queryIndexBySupplierOrderID(index);
        System.out.println(orderIndex);
        Assert.assertNotNull(orderIndex,"数据库没有生成接收后的订单");
        DatabaseUtil.closeSqlSession(session);*/
    }

    //接收完后查看待接收订单,将不会再有刚才接收过的订单 dependsOnMethods = {"receiveTest"},
    @Test(description = "待接收普通订单")
    public void orderacceptedlistTest() throws IOException, InterruptedException {
        Thread.sleep(2000);
        System.out.println("当前接口:"+TestConfig.orderacceptedlistUrl);
        HashMap<String,String> headermap=new HashMap<String, String>();
        headermap.put("content-type","application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_cy);

        JSONObject param=new JSONObject();

        String result;
        closeableHttpResponse=TestConfig.restClient.post(TestConfig.orderacceptedlistUrl,param.toString(),headermap);
        result=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);

        Assert.assertEquals(map.get("code").toString(),"0","待接收普通订单查询失败");

        Map mapData =(Map) map.get("data");
        Assert.assertNotNull(mapData,"data为空");


        JSONArray beanListArray = (JSONArray) mapData.get("beanList");
        Assert.assertNotNull(beanListArray,"beanList为空");
        if(beanListArray.size()>0){
            for(Object bean:beanListArray){
                Map b=(Map)bean;
                String id=b.get("id").toString();
                Assert.assertNotEquals(id,((HashMap)TestConfig.addedOrders[0]).get("orderID").toString(),"已接收的普通订单还存在于待接收普通订单里");
            }
        }
    }

    //接收拼车单 ,dependsOnGroups = {"loginCY"}
    @Test(description = "接收拼车单")
    public void receiveCombineOrdersTest() throws IOException, InterruptedException {
        System.out.println("当前接口:"+TestConfig.receiveCombineOrdersUrl);

        HashMap<String,String> headermap=new HashMap<String, String>();
        headermap.put("content-type","application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_cy);

        JSONObject param=new JSONObject();
        param.put("orderID",TestConfig.pcID);
        param.put("accept",1);

        System.out.println("发送请求:"+param.toString());
        closeableHttpResponse=TestConfig.restClient.post(TestConfig.receiveCombineOrdersUrl,param.toString(),headermap);
        String result;
        result=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);
        Assert.assertEquals(map.get("status").toString(),"0","拼车单接收失败");

        Thread.sleep(3000);

    }

    //已接收拼车单_有刚才接收的订单 dependsOnMethods = {"receiveCombineOrdersTest"},
    @Test(description = "已接收拼车单")
    public void orderacceptedcarlistTest() throws IOException {
        System.out.println("当前接口:"+TestConfig.orderacceptedcarlistUrl);

        HashMap<String,String> headermap=new HashMap<String, String>();
        headermap.put("content-type","application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_cy);

        JSONObject param=new JSONObject();
        String result;
        closeableHttpResponse=TestConfig.restClient.post(TestConfig.orderacceptedcarlistUrl,param.toString(),headermap);
        result=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);

        Assert.assertEquals(map.get("code").toString(),"0","已接收拼车单查询失败");
        Map mapData =(Map) map.get("data");
        Assert.assertNotNull(mapData,"data为空");

        JSONArray beanListArray = (JSONArray) mapData.get("beanList");
        Assert.assertNotNull(beanListArray,"beanList为空");
        boolean flag=false;
        if(beanListArray.size()>0){
            for(Object bean:beanListArray){
                Map b=(Map)bean;
                String id=b.get("id").toString();
                if(id.equals(TestConfig.pcID)){
                    flag=true;
                    break;
                }
            }
        }
        Assert.assertTrue(flag,"接收的拼车单不存在于已接收拼车单里");
    }

    //待接收拼车单查询,没有刚才接收的订单 dependsOnMethods = {"receiveCombineOrdersTest"},
    @Test(description = "待接收普通订单")
    public void orderacceptedcarTest() throws IOException, InterruptedException {
        System.out.println("当前接口:"+TestConfig.orderacceptedcarUrl);
        HashMap<String,String> headermap=new HashMap<String, String>();
        headermap.put("content-type","application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_cy);

        JSONObject param=new JSONObject();

        String result;
        closeableHttpResponse=TestConfig.restClient.post(TestConfig.orderacceptedcarUrl,param.toString(),headermap);
        result=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);

        Assert.assertEquals(map.get("code").toString(),"0","待接收拼车单查询失败");

        Map mapData =(Map) map.get("data");
        Assert.assertNotNull(mapData,"data为空");

        JSONArray beanListArray = (JSONArray) mapData.get("beanList");
        Assert.assertNotNull(beanListArray,"beanList为空");
        if(beanListArray.size()>0){
            for(Object bean:beanListArray){
                Map b=(Map)bean;
                String id=b.get("id").toString();
                Assert.assertNotEquals(id,TestConfig.pcID,"已接收的拼车单还存在于待接收拼车单里");
            }
        }
    }

}
