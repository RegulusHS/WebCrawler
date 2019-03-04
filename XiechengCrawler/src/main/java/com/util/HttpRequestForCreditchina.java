package com.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HttpRequestForCreditchina {
    private HttpClient httpClient;
    public HttpEntity getEntityByHttpGetMethod(String url) {
        HttpGet httpGet = new HttpGet(url); //使用的请求方法
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity httpEntity = response.getEntity();   //获取网页内容流
        return httpEntity;
    }
    public String getHTMLContentByHttpGetMethod(String url, String code) {
        //获取html内容
        try {
            return EntityUtils.toString(getEntityByHttpGetMethod(url), code);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    //HTTPS协议是由SSL+HTTP协议
    public void initSSLClient() {
        RequestConfig defaultConfig = null;
        PoolingHttpClientConnectionManager pcm = null;
        try {
            X509TrustManager xtm = new SSL509TrustManager();    //创建信任管理
            //创建SSLContext对象,并使用指定的信任管理器初始化
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{xtm}, null);
            //从SSLContext对象中得到SSLConnectionSocketFactory对象
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
            //设置全局请求配置
            defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setExpectContinueEnabled(true)
                    .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
            //设置协议http和https对应的处理socket连接工厂的对象
            Registry<ConnectionSocketFactory> sfr = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", sslConnectionSocketFactory).build();
            //基于配置创建连接管理器
            pcm = new PoolingHttpClientConnectionManager(sfr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        //初始化httpclient
        httpClient = HttpClients.custom().setConnectionManager(pcm).setDefaultRequestConfig(defaultConfig).build();
    }
}
