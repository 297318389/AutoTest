package com.nanruan.cases.tms;

import com.alibaba.fastjson.JSONObject;
import com.nanruan.config.TestConfig;
import com.nanruan.model.InterfaceName;
import com.nanruan.utils.CaseHelper;
import com.nanruan.utils.CaseInfo;
import com.nanruan.utils.Json2Map;
import com.nanruan.utils.ReadExcel;
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

/**
 * 拆单
 */
public class UrbanTest {
    private static ResourceBundle bundle=ResourceBundle.getBundle("application");

    CloseableHttpResponse closeableHttpResponse;

    @BeforeClass
    public void beforeClass(){
        TestConfig.urbanUrl=bundle.getString("test.url")+bundle.getString("tms.urban.uri");

    }

    //从excel里读取数量拆单用例做为数据源
    @DataProvider(name = "urbanNumData")
    protected Object[][] dataInfo2() throws IOException {

        Object[][] myObj = null;
        List<Map<String, String>> list = ReadExcel.readXlsx(TestConfig.caseExcelPath, "urban_num");
        myObj = CaseHelper.getObjArrByList(list);
        return myObj;
    }
    //从excel里读取线路拆单用例做为数据源
    @DataProvider(name = "urbanLineData")
    protected Object[][] dataInfo1() throws IOException {

        Object[][] myObj = null;
        List<Map<String, String>> list = ReadExcel.readXlsx(TestConfig.caseExcelPath, "urban_line");
        myObj = CaseHelper.getObjArrByList(list);
        return myObj;
    }

    @Test(description = "客户订单查询")
    public void  checkTest() throws IOException, InterruptedException {
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_fh);



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

    @Test(description = "长途待调度订单查询_指定ID")
    public  void longDispatchTest() throws IOException {
        System.out.println("当前接口:"+ TestConfig.longDispatchUrl);
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_cy);
        JSONObject param=new JSONObject();
        param.put("code",TestConfig.supplierCode+"-1");
        System.out.println("发送请求:"+param.toString());
        String result;
        closeableHttpResponse=TestConfig.restClient.post(TestConfig.longDispatchUrl,param.toString(),headermap);
        result= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println("返回结果:"+result);
        Map<String, Object> map= Json2Map.json2Map(result);
        Assert.assertEquals(map.get("code").toString(),"0","查询失败");


    }

    //数量拆单
    @Test(description = "数量拆单",dataProvider = "urbanNumData")
    public void numTest(CaseInfo caseInfo) throws IOException, InterruptedException {
        System.out.println("当前接口:"+ TestConfig.urbanUrl);
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_cy);

        String inputJson=caseInfo.getRequest();

        //替换用例里的订单id,单据编号,合同编号
        inputJson= inputJson.replace("{{orderID}}","");
        inputJson= inputJson.replace("{{code}}","");
        inputJson= inputJson.replace("{{pactCode}}","");
        System.out.println("发送请求:"+inputJson);


        String result;
        closeableHttpResponse=TestConfig.restClient.post(TestConfig.urbanUrl,inputJson,headermap);
        result=EntityUtils.toString(closeableHttpResponse.getEntity());

        System.out.println("返回结果:"+result);

        Assert.assertNotNull(result,"数量拆单接口未返回");
        Map<String, Object> map= Json2Map.json2Map(result);
        Assert.assertEquals(map.get("code").toString(),"0","数量拆单失败");
        Thread.sleep(3000);

    }

    //线路拆单
    @Test(description = "线路拆单",dataProvider = "urbanLineData")
    public void lineTest(CaseInfo caseInfo) throws InterruptedException, IOException {
        System.out.println("当前接口:"+ TestConfig.urbanUrl);
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization","Bearer "+TestConfig.token_cy);

        String inputJson=caseInfo.getRequest();

        //替换用例里的订单id,单据编号,合同编号
        inputJson= inputJson.replace("{{orderID}}","");
        inputJson= inputJson.replace("{{code}}","");
        inputJson= inputJson.replace("{{pactCode}}","");
        System.out.println("发送请求:"+inputJson);

        String result;
        closeableHttpResponse=TestConfig.restClient.post(TestConfig.urbanUrl,inputJson,headermap);
        result=EntityUtils.toString(closeableHttpResponse.getEntity());

        System.out.println("返回结果:"+result);

        Assert.assertNotNull(result,"线路拆单接口未返回");
        Map<String, Object> map= Json2Map.json2Map(result);
        Assert.assertEquals(map.get("code").toString(),"0","线路拆单失败");
        Thread.sleep(3000);
    }
}
