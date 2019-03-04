package com.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpRequest {
    private HttpClient httpClient;
    public HttpEntity getEntityByHttpGetMethod(String url) {
        httpClient = HttpClients.custom().build();  //初始化httpClient
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
    //提供URL以及网页的编码
    public String getHTMLContentByHttpGetMethod(String url, String code) {
        //获取html内容
        try {
            return EntityUtils.toString(getEntityByHttpGetMethod(url), code);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
