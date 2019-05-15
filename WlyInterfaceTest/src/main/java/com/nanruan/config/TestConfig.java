package com.nanruan.config;

import com.nanruan.utils.RestClient;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;

public class TestConfig {
    public static String loginUrl;//登录
    public static String getUserListUrl;//取用户例表
    public static String addOrderUrl;//创建订单
    public static String longDispatchUrl;//长途待调度订单
    public static String urbanDispatchUrl;//市内待调度订单
    public static String carSharingUrl;//拼车待调度订单
    public static String dispatchUrl;//订单调度
    public static String checktrackingUrl;//运输订单查询
    public static String checkUrl;//客户订单查询
    public static String receiveUrl;//接收普通订单
    public  static String receiveCombineOrdersUrl;//接收拼车单
    public static String orderacceptedlistUrl;//待接收普通单
    public static String orderacceptedcarUrl;//待接收拼车单
    public static String orderacceptedcarlistUrl;//已接收拼车单
    public static String combineOrdersUrl;//拼车
    public static String combineSendUrl;//拼车待调度——发送
    public static String entrustUrl;//已委托订单
    public static String consignmentOrderUrl;//已委托拼车单
    public static String signManageUrl;//签收管理查询
    public static String signUrl;//签收
    public static String urbanUrl;//拆单


    public  static DefaultHttpClient defaultHttpClient;
    public static RestClient restClient;
    public static String caseExcelPath;
    public static CookieStore store;

    public static String token_fh;// 发货方token
    public static String userCompanyid_fh;//当前发货方账号所在公司的ID

    public static String token_cy;//承运方token
    public static String userCompanyid_cy;//当前承运方账号所在公司的ID


    public static Object[] addedOrders;//创建的订单_运输订单(id,code,pactcode)

    public static String orderID;//ID
    public static String orderCode;//单据编号
    public static String pactCode;//合同编号

    public static String pcID;//拼车单ID

    public static String supplierOrderID;//订单被接收后,新订单ID
    public static String supplierCode;
    public static String supplierPactCode;
    /**
     * 获取可信任https链接，以避免不受信任证书出现peer not authenticated异常
     *
     * @param base
     * @return
     */
    public static HttpClient wrapClient(HttpClient base) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs,
                                               String string) {
                }
                public void checkServerTrusted(X509Certificate[] xcs,
                                               String string) {
                }
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = base.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", ssf, 443));
            return new DefaultHttpClient(ccm, base.getParams());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
