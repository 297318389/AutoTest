package com.nanruan.cases.tms;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nanruan.config.TestConfig;
import com.nanruan.utils.Json2Map;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DispatchTest {
    private static ResourceBundle bundle=ResourceBundle.getBundle("application");

    CloseableHttpResponse closeableHttpResponse;

    private Object[] beanListResult;
    @BeforeClass
    public void  beforeClass() {
        TestConfig.longDispatchUrl = bundle.getString("test.url") + bundle.getString("tms.longDispatch.uri");//长途待调度订单
        TestConfig.urbanDispatchUrl = bundle.getString("test.url") + bundle.getString("tms.urbanDispatch.uri");//市内待调度订单
        TestConfig.dispatchUrl = bundle.getString("test.url") + bundle.getString("tms.dispatch.uri");//订单调度
        TestConfig.receiveUrl = bundle.getString("test.url") + bundle.getString("tms.receive.uri");//普通订单接收
        TestConfig.orderacceptedlistUrl = bundle.getString("test.url") + bundle.getString("tms.orderacceptedlist.uri");//待接收普通订单
        TestConfig.orderacceptedcarUrl = bundle.getString("test.url") + bundle.getString("tms.orderacceptedcar.uri");//待接收拼车单
        TestConfig.carSharingUrl = bundle.getString("test.url") + bundle.getString("tms.carSharing.uri");//拼车待调度
        TestConfig.combineOrdersUrl = bundle.getString("test.url") + bundle.getString("tms.combineOrders.uri");//拼车
        TestConfig.combineSendUrl = bundle.getString("test.url") + bundle.getString("tms.combineSend.uri");//拼车待调度—发送
        TestConfig.entrustUrl=bundle.getString("test.url")+bundle.getString("tms.entrust.uri");//已委托订单
        TestConfig.consignmentOrderUrl=bundle.getString("test.url")+bundle.getString("tms.consignmentOrder.uri");//已委托拼车单
    }

    //长途待调度订单查询——指定单据编号测试 ,dependsOnGroups = {"addorderTrue"}
    @Test(groups = "longDispatch",description = "长途待调度订单查询_指定ID")
    public  void longDispatchTest() throws IOException {
        System.out.println("当前接口:"+ TestConfig.longDispatchUrl);
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_fh);
        if( TestConfig.addedOrders.length>0)
        {
           // HashMap hashMap=new HashMap();
            //hashMap.put("orderID",beanList.get("id").toString());
           // hashMap.put("orderCode", beanList.get("code").toString());
            //hashMap.put("pactCode",beanList.get("pactCode").toString());
            for(int i=0;i<TestConfig.addedOrders.length;i++){
                HashMap hashMap= (HashMap) TestConfig.addedOrders[i];
                JSONObject param=new JSONObject();
                param.put("code",hashMap.get("orderCode").toString());
                System.out.println("发送请求:"+param.toString());
                String result;
                closeableHttpResponse=TestConfig.restClient.post(TestConfig.longDispatchUrl,param.toString(),headermap);
                result= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
                System.out.println("返回结果:"+result);
                Map<String, Object> map= Json2Map.json2Map(result);
                Assert.assertEquals(map.get("code").toString(),"0","查询失败");
            }
        }

    }

    //普通订单调度 ,dependsOnMethods = {"longDispatchTest"}
    @Test(description = "订单详情调度,不修改订单信息")
    public void dispathcTest() throws IOException, InterruptedException {
        System.out.println("当前接口:"+ TestConfig.dispatchUrl);

        HashMap<String,String> headermap=new HashMap<String, String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_fh);

        JSONObject param=new JSONObject();
               param.put("supplierID","2913");
        param.put("supplierName","深圳市佳行通物流有限公司");
        param.put("scheduleDirectly","1");
        param.put("orderID",((HashMap)TestConfig.addedOrders[0]).get("orderID").toString());
        param.put("code",((HashMap)TestConfig.addedOrders[0]).get("orderCode").toString());
        param.put("pactCode",((HashMap)TestConfig.addedOrders[0]).get("pactCode").toString());
        param.put("trackType", 1); //为什么一定要传设备编号,不传就会置空创建订单时填写的设备编号
        param.put("deviceCode", "351608087057019");

        closeableHttpResponse=TestConfig.restClient.post(TestConfig.dispatchUrl,param.toString(),headermap);

        System.out.println("发送请求:"+param.toString());
        String result;
        result=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println("返回结果:"+result);

        Map<String, Object> map= Json2Map.json2Map(result);
        Assert.assertEquals(map.get("code").toString(),"0","调度失败");
        Thread.sleep(3000);
    }

    //已委托订单查询_查询刚才调度的订单 dependsOnMethods = {"dispathcTest"},
    @Test(description = "查询已委托订单")
    public void entrustTest() throws IOException {
        System.out.println("当前接口:"+ TestConfig.entrustUrl);
        HashMap<String,String> headermap=new HashMap<String, String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_fh);

        JSONObject param=new JSONObject();
        param.put("orderID",((HashMap)TestConfig.addedOrders[0]).get("orderID").toString());
        param.put("code",((HashMap)TestConfig.addedOrders[0]).get("orderCode").toString());
        param.put("pactCode",((HashMap)TestConfig.addedOrders[0]).get("pactCode").toString());
        System.out.println("发送请求:"+param.toString());

        closeableHttpResponse=TestConfig.restClient.post( TestConfig.entrustUrl, param.toString(),headermap);
        String result;
        result=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);
        Assert.assertEquals(map.get("code").toString(),"0","已委托订单查询失败");

    }

    //查所有待长途待调度订单（发货时间为当月） dependsOnMethods = {"dispathcTest"},
    @Test(description = "长途待调度订单查询_所有")
    public  void longDispatchTest1() throws IOException {
        System.out.println("当前接口:"+ TestConfig.longDispatchUrl);

        HashMap<String,String> headermap=new HashMap<String, String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_fh);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());

        JSONObject param=new JSONObject();
        param.put("startFromTime",first+" 00:00:00");
        param.put("endFromTime",last+" 23:59:59");//设置发货时间为当月，否则过了关帐期不能拼车
        param.put("pageSize",1000000); //每页显示数设置多一些,否则默认只返回10条
        System.out.println("发送请求:"+param.toString());

        closeableHttpResponse=TestConfig.restClient.post(TestConfig.longDispatchUrl,param.toString(),headermap);

        String result;
        result=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println("返回结果:"+result);

        Map<String, Object> map= Json2Map.json2Map(result);
        Assert.assertEquals(map.get("code").toString(),"0","查询失败");
        Map mapData =(Map) map.get("data");
        Assert.assertNotNull(mapData,"data为空");
        JSONArray beanListArray = (JSONArray) mapData.get("beanList");
        Assert.assertNotNull(beanListArray,"beanList为空");


        beanListResult=new Object[beanListArray.size()];//接口返回的ID
        //取两个订单做拼车用
        if(beanListArray.size()>0) {
            int i = 0;
            for (Object beanList : beanListArray) {
                Map bean = (Map) beanList;
                HashMap obj=new HashMap();
                obj.put("id",bean.get("id"));
                obj.put("code",bean.get("code"));
                beanListResult[i] =obj;

                i++;
            }
        }


        /*int totalCount=Integer.parseInt(mapData.get("totalCount").toString());
        SqlSession session= DatabaseUtil.getSqlSession();
        ISqlMapper sqlMapper=session.getMapper(ISqlMapper.class);
        List<OrderIndex> list = sqlMapper.queryLongDistance(Integer.parseInt(TestConfig.userCompanyid));
        DatabaseUtil.closeSqlSession(session);
        Assert.assertEquals(totalCount,list.size(),"接口返回的数量与数据库查找的数量不一样");
        String[] checkResult;//通过sql查到的ID
        String[] beanListResult;//接口返回的ID
        if(beanListArray.size()>0){
            beanListResult=new String[beanListArray.size()];
            int i=0;
           for(Object beanList:beanListArray){
               Map bean=(Map)beanList;
               beanListResult[i] =bean.get("id").toString();
                i++;
           }
            if(list.size()>0){
                checkResult=new String[list.size()];
                int j=0;
                for(OrderIndex index:list){
                    checkResult[j]= index.getIndex_ID().toString();
                    j++;
                }
                Assert.assertEqualsNoOrder(beanListResult,checkResult,"接口返回的id列表与数据库查找的不一样");
            }
        }*/
    }



    //拼车_从长途待调度订单里取两个当月订单做拼车.dependsOnMethods = {"longDispatchTest1"}
    @Test(description = "拼车_从长途待调度订单里取两个当月订单做拼车")
    public  void  combineOrdersTest() throws IOException, InterruptedException {
        System.out.println("当前接口:"+TestConfig.combineOrdersUrl);

       /* SqlSession session= DatabaseUtil.getSqlSession();
        ISqlMapper sqlMapper=session.getMapper(ISqlMapper.class);
        List<OrderIndex> list = sqlMapper.queryLongDistanceCurMonth(Integer.parseInt(TestConfig.userCompanyid));
        DatabaseUtil.closeSqlSession(session);*/
        JSONArray array = null;
        /*if(beanListResult.length>2){
            array=new JSONArray();
            Map bean0= (Map) beanListResult[0];
            Map bean1= (Map) beanListResult[1];
            array.add(bean0.get("id"));
            array.add(bean1.get("id"));
        }*/
        array=new JSONArray();
        array.add(((HashMap)TestConfig.addedOrders[1]).get("orderID").toString());
        array.add(((HashMap)TestConfig.addedOrders[2]).get("orderID").toString());

        HashMap<String,String> headermap=new HashMap<String, String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_fh);

        JSONObject param=new JSONObject();
        param.put("ordersLst",array);
        param.put("sendDirectly", 0); //0 保存
        param.put( "srcClass", 4);
        param.put("pactCode", "pc_jxt_test");
        param.put( "carName", "");
        param.put("carID","");
        param.put( "driverName", "");
        param.put( "driverID", "");
        param.put( "supplierName", "深圳市佳行通物流有限公司");
        param.put(  "supplierID", 2913);
        param.put(  "supplierSymbolID", 0);
        param.put( "shipMode", 2);
        System.out.println("发送请求:"+param.toString());

        closeableHttpResponse=TestConfig.restClient.post(TestConfig.combineOrdersUrl,param.toString(),headermap);

        String result;
        result=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);
        Assert.assertEquals(map.get("code").toString(),"0","操作失败");
        Thread.sleep(3000);
    }


    private String pcID="";
    //查询刚才保存的拼车待调度订单 ,dependsOnMethods = {"combineOrdersTest"}
    @Test(description = "查询拼车待调度订单")
    public void carSharingTest() throws IOException, InterruptedException {
        System.out.println("当前接口:"+TestConfig.carSharingUrl);
        HashMap<String,String> headermap=new HashMap<String, String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_fh);

        JSONObject param=new JSONObject();
        param.put("pactCode","pc_jxt_test");
        //Map bean0= (Map) beanListResult[0];
       // String code= bean0.get("code").toString();
        String code=((HashMap)TestConfig.addedOrders[2]).get("orderCode").toString();
        param.put("code",code); //从保存的拼车子单里取一个code做为检索条件
        System.out.println("发送请求:"+param.toString());

        closeableHttpResponse=TestConfig.restClient.post(TestConfig.carSharingUrl,param.toString(),headermap);

        String result;
        result=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);
        Assert.assertEquals(map.get("code").toString(),"0","查询失败");
        Map mapData =(Map) map.get("data");
        Assert.assertNotNull(mapData,"data为空");
        JSONArray beanListArray = (JSONArray) mapData.get("beanList");
        Assert.assertNotNull(beanListArray,"beanList为空");
        Map bean=(Map) beanListArray.get(0);
        pcID=bean.get("id").toString();
        TestConfig.pcID=pcID;
        Thread.sleep(3000);
    }

    //发送待调度拼车单 ,dependsOnMethods = {"carSharingTest"}
    @Test(groups ="combineSend",description = "拼车待调度_发送")
    public void combineSendTest() throws IOException {
        System.out.println("当前接口:"+TestConfig.combineSendUrl);
        HashMap<String,String> headermap=new HashMap<String, String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_fh);

        JSONObject param=new JSONObject();
        param.put("description","自动化测试");
        param.put("orderID",pcID);
        System.out.println("发送请求:"+param.toString());

        closeableHttpResponse=TestConfig.restClient.post(TestConfig.combineSendUrl,param.toString(),headermap);

        String result;
        result=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);
        Assert.assertEquals(map.get("status").toString(),"0","拼车单发送失败");

    }



    //已委托拼车单查询:查询刚才调度的拼车单 ,dependsOnMethods = {"combineSendTest"}
    @Test(groups = "consignmentOrder",description = "查询已委托拼车单")
    public void consignmentOrderTest() throws IOException {
        System.out.println("当前接口:"+TestConfig.consignmentOrderUrl);
        HashMap<String,String> headermap=new HashMap<String, String>();
        headermap.put("Content-Type","application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_fh);

        JSONObject param=new JSONObject();
        param.put("pactCode","pc_jxt_test");
        //Map bean0= (Map) beanListResult[0];
       // String code= bean0.get("code").toString();
        String code=((HashMap)TestConfig.addedOrders[2]).get("orderCode").toString();
        param.put("code",code); //从保存的拼车子单里取一个code做为检索条件
        System.out.println("发送请求:"+param.toString());

        closeableHttpResponse=TestConfig.restClient.post(TestConfig.consignmentOrderUrl,param.toString(),headermap);
        String result;
        result=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);
        Assert.assertEquals(map.get("code").toString(),"0","拼车单发送失败");
    }

}
