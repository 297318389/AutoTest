package com.nanruan.cases.tms;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nanruan.config.TestConfig;
import com.nanruan.model.InterfaceName;
import com.nanruan.utils.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AddorderTest {
    private static ResourceBundle bundle=ResourceBundle.getBundle("application");

    CloseableHttpResponse closeableHttpResponse;

    private Object[] addedOrders;//新增成功的订单
    int i=0;
    @BeforeClass
    public void beforeClass(){
        TestConfig.addOrderUrl= ConfigFile.getUrl(InterfaceName.addorder);//创建订单
        TestConfig.checktrackingUrl=bundle.getString("test.url")+bundle.getString("tms.checktracking.uri");//运输订单查询
        TestConfig.checkUrl=bundle.getString("test.url")+bundle.getString("tms.check.uri");//客户订单查询
    }


    //从excel里读取创建订单用例做为数据源
    @DataProvider(name = "ordersInfo")
    protected Object[][] dataInfo1() throws IOException {

        Object[][] myObj = null;
        List<Map<String, String>> list = ReadExcel.readXlsx(TestConfig.caseExcelPath,InterfaceName.addorder.toString());
        myObj = CaseHelper.getObjArrByList(list);
        addedOrders=new Object[myObj.length];
        return myObj;
    }


    //创建订单测试 ,dependsOnGroups = {"loginTrue"}
    @Test(groups = "addorderTrue",description = "创建订单",dataProvider = "ordersInfo")
    public void addOrderTest(CaseInfo c) throws IOException, InterruptedException {
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_fh);

        String inputJson=c.getRequest();
        System.out.println("发送请求:"+inputJson);
        String result;

        closeableHttpResponse=TestConfig.restClient.post(TestConfig.addOrderUrl,inputJson,headermap);
        result=EntityUtils.toString(closeableHttpResponse.getEntity());

        System.out.println("返回结果:"+result);
        Assert.assertNotNull(result,"创建订单接口未返回");
        Map<String, Object> map= Json2Map.json2Map(result);
        Map mapData =(Map) map.get("data");
        String orderID="";
        String orderCode="";
        orderID=mapData.get("orderID").toString();
        orderCode=mapData.get("orderCode").toString();
        //TestConfig.orderID=orderID; //把订单ID和单据编号存到全局
       // TestConfig.orderCode=orderCode;
        Assert.assertEquals(map.get("code").toString(),"0","创建订单失败");
        Assert.assertNotNull(orderID,"没有返回orderID");
        Assert.assertNotNull(orderCode,"没有返回orderCode");

        //将创建的订单id和coded存起来,后面再循环做验证
        HashMap obj=new HashMap();
        obj.put("orderID",orderID);
        obj.put("orderCode",orderCode);
        addedOrders[i] =obj;
        i++;
        Thread.sleep(3000);


        //先不做数据库验证
        /*SqlSession session = DatabaseUtil.getSqlSession();
        ISqlMapper sqlMapper=session.getMapper(ISqlMapper.class);
        // 查询单个对象
        OrderIndex orderIndex=sqlMapper.queryIndexById(Integer.parseInt(orderID));
        System.out.println(orderIndex);
        Assert.assertNotNull(orderIndex,"数据库没有订单");
        DatabaseUtil.closeSqlSession(session);*/

    }


    //客户订单查询测试  创建订单成功的话，客户订单里，会有刚才创建的订单 ,dependsOnMethods = {"addOrderTest"}
    @Test(description = "客户订单查询")
    public void  checkTest() throws IOException, InterruptedException {
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_fh);

        if(addedOrders.length>0){
            for(Object obj:addedOrders) {
                Map bean= (Map)obj;
                JSONObject param = new JSONObject();
                param.put("code",bean.get("orderCode"));
                System.out.println("发送请求:"+param.toString());
                String result;
                closeableHttpResponse = TestConfig.restClient.post(TestConfig.checkUrl, param.toString(), headermap);
                result = EntityUtils.toString(closeableHttpResponse.getEntity());

                System.out.println("返回结果:" + result);
                Map<String, Object> map = Json2Map.json2Map(result);

                Assert.assertEquals(map.get("code").toString(), "0", "查询失败");
            }
        }

    }

    //运输订单查询测试  创建订单成功的话，运输订单里，会有刚才创建的订单  ,dependsOnMethods = {"addOrderTest"}
    @Test(description = "运输订单查询")
    public void  checktrackingTest() throws IOException, InterruptedException {
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
        headermap.put("Authorization","Bearer "+TestConfig.token_fh);

        if(addedOrders.length>0) {
            TestConfig.addedOrders=new Object[addedOrders.length];
            int j=0;
            for (Object obj : addedOrders) {
                Map bean= (Map)obj;
                JSONObject param = new JSONObject();
                param.put("code", bean.get("orderCode"));
                System.out.println("发送请求:" + param.toString());

                String result;
                closeableHttpResponse = TestConfig.restClient.post(TestConfig.checktrackingUrl, param.toString(), headermap);
                result = EntityUtils.toString(closeableHttpResponse.getEntity());
                System.out.println("返回结果:" + result);

                Map<String, Object> map = Json2Map.json2Map(result);

                Assert.assertEquals(map.get("code").toString(), "0", "查询失败");
                Map mapData = (Map) map.get("data");
                Assert.assertNotNull(mapData, "data为空");

                JSONArray beanListArray = (JSONArray) mapData.get("beanList");
                Assert.assertNotNull(beanListArray, "beanList为空");
                Assert.assertNotEquals(beanListArray.size(), 0, "beanList没有内容");
                Map beanList = (Map) beanListArray.get(0);

                //将运输订单ID,单据编号，合同编号存到全局，后面调度时用
                HashMap hashMap=new HashMap();
                hashMap.put("orderID",beanList.get("id").toString());
                hashMap.put("orderCode", beanList.get("code").toString());
                hashMap.put("pactCode",beanList.get("pactCode").toString());
                TestConfig.addedOrders[j]=hashMap;
                j++;
            }
        }

    }
}
