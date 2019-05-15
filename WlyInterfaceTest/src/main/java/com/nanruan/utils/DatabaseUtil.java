package com.nanruan.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

public class DatabaseUtil {
    //创建SqlSessionFactory对象
    private static SqlSessionFactory factory;
    static{
        try {
            //获取配置文件资源
            InputStream inputStream=Resources.getResourceAsStream("databaseConfig.xml");
            //获取SqlSessionFactory对象
            factory=new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取SqlSession对象
     */
    public static SqlSession getSqlSession(){
        return factory.openSession();
    }

    /**
     * 关闭SqlSession对象
     */
    public static void closeSqlSession(SqlSession session){
        if(null!=session){
            //关闭Sqlsession对象
            session.close();
        }
        session=null;
    }
}
