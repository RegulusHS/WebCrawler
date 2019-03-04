package HttpClient.postMethod;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * HS_jupiter
 * huangshan0724
 */
public class demo02 {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClients.custom().build();   //初始化httpclient
        String renRenLoginURL = "http://www.renren.com/PLogin.do";  //登录的地址
        HttpPost httpPost = new HttpPost(renRenLoginURL);   //采用post方法
        //建立一个NameValuePair数组，用于存储欲传送的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("origURL","http://www.renren.com/home"));
        nvps.add(new BasicNameValuePair("email", "你的邮箱地址"));
        nvps.add(new BasicNameValuePair("password", "你的登录密码"));
        HttpResponse response = null;
        try {
            //表单参数提交
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpPost.abort();
        }
    }
}
