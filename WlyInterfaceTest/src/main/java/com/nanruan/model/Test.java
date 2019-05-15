package com.nanruan.model;

import com.nanruan.config.TestConfig;
import com.nanruan.dao.mapper.ISqlMapper;
import com.nanruan.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Test {

//    public static void main(String[] args){
//    String result="{\n" +
//                "    \"code\": 0,\n" +
//                "    \"message\": \"查询成功\",\n" +
//                "    \"data\": {\n" +
//                "        \"totalCount\": 1,\n" +
//                "        \"pages\": null,\n" +
//                "        \"beanList\": [\n" +
//                "            {\n" +
//                "                \"fromaddress\": \"上海市上海市黄浦区东方之珠\",\n" +
//                "                \"id\": 1978515,\n" +
//                "                \"supplierName\": \"\",\n" +
//                "                \"code\": \"19031430635-1\",\n" +
//                "                \"pactCode\": \"zyht2019031401\",\n" +
//                "                \"signType\": null\n" +
//                "            }\n" +
//                "        ]\n" +
//                "    }\n" +
//                "}";
//        Map<String, Object> map= Json2Map.json2Map(result);
//        Map mapData =(Map) map.get("data");
//        JSONArray array = (JSONArray) mapData.get("beanList");
//        Map beanList= (Map) array.get(0);
//
//        System.out.println( beanList.get("id"));
//    }

//    public static void main(String [] args){
//        String[] a = new String[]{"a3","a1","a2"};
//
//        String[] a1 = new String[]{"a3","a1","a2"};
//
//        String[] b = new String[]{"a1","a2","a3"};
//
//        Assert.assertEquals(a, a1);
//
//        Assert.assertNotEquals(a, b,"aaa");
//
//    }

    //测试获取所有数据：
   /* public static void main(String[] args) {
        // 获取SqlSession对象
        SqlSession session = DatabaseUtil.getSqlSession();
        // 查询全部对象


        ISqlMapper sqlMapper=session.getMapper(ISqlMapper.class);
        OrderIndex o=new OrderIndex();
        o.setIndex_ID((long) 1990886);
        o.setIndex_SrcOrderID((long) 1990885);

        OrderIndex orderIndex=sqlMapper.queryIndexBySupplierOrderID(o);
        System.out.println(orderIndex);
        Assert.assertNotNull(orderIndex,"数据库没有生成接收后的订单");
        DatabaseUtil.closeSqlSession(session);
    }*/
    private static String firstDay;
    private static String lastDay;
    public static void main(String[] args) {
                 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                 //获取前月的第一天
                 Calendar cal_1=Calendar.getInstance();//获取当前日期
                 cal_1.add(Calendar.MONTH, -1);
                 cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
                 firstDay = format.format(cal_1.getTime());
                 System.out.println("-----1------firstDay:"+firstDay);
                 //获取前月的最后一天
                 Calendar cale = Calendar.getInstance();
                 cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
                 lastDay = format.format(cale.getTime());
                 System.out.println("-----2------lastDay:"+lastDay);


                 //获取当前月第一天：
                 Calendar c = Calendar.getInstance();
                 c.add(Calendar.MONTH, 0);
                 c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
                 String first = format.format(c.getTime());
                 System.out.println("===============first:"+first);

                //获取当前月最后一天
                 Calendar ca = Calendar.getInstance();
                ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
                String last = format.format(ca.getTime());
                System.out.println("===============last:"+last);

             }

    //测试根据ID获取数据
//    public static void main(String[] args) {
//        // 获取SqlSession对象
//        SqlSession session = DatabaseUtil.getSqlSession();
//        // 查询单个对象
//        OrderIndex orderIndex=session.selectOne("com.nanruan.model.OrderIndex.queryIndexById", 1089949);
//        System.out.println(orderIndex.toString());
//        //关闭SqlSession对象
//        DatabaseUtil.closeSqlSession(session);
//    }

//    //添加：
//    public static void main(String[] args) {
//        // 获取SqlSession对象
//        SqlSession session = DatabaseUtil.getSqlSession();
//        OrderIndex orderIndex = new OrderIndex();
//        orderIndex.setUserName("xinxin");
//
//        session.insert("com.nanruan.model.OrderIndex.insertUsers", user);
//        //提交
//        session.commit();
//        // 关闭SqlSession对象
//        DatabaseUtil.closeSqlSession(session);
//    }
//    //修改：
//
//    public static void main(String[] args) {
//        // 获取SqlSession对象
//        SqlSession session = DatabaseUtil.getSqlSession();
//        Users user = new Users();
//        user.setId(11);
//        user.setPassword("hahahaha");
//        user.setPhone("3243223242");
//        session.update("com.nanruan.model.OrderIndex.updateUsers", user);
//        //提交
//        session.commit();
//        // 关闭SqlSession对象
//        DatabaseUtil.closeSqlSession(session);
//    }
//
//    // 删除：
//    public static void main(String[] args) {
//        // 获取SqlSession对象
//        SqlSession session = DatabaseUtil.getSqlSession();
//        Users user = new Users();
//        user.setId(11);
//        session.delete("com.nanruan.model.OrderIndex.deleteUsers", user);
//        //提交
//        session.commit();
//        // 关闭SqlSession对象
//        DatabaseUtil.closeSqlSession(session);
//    }

}
