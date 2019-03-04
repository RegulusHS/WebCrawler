package HttpClient.postMethod;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class demo03 {

    public static void main(String[] args) throws IOException {
        HttpClient httpClient = HttpClients.custom().build();   //初始化httpclient
        HttpGet httpGet = new HttpGet("http://www.w3school.com.cn/b.asp");  //使用的请求方法
        //请求头配置
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpGet.setHeader("Cache-Control", "max-age=0");
        httpGet.setHeader("Host", "www.w3school.com.cn");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36"); //这项内容很重要
        HttpResponse response = httpClient.execute(httpGet);    //发出get请求
        //获取响应状态码
        int code = response.getStatusLine().getStatusCode();
        HttpEntity httpEntity = response.getEntity();   //获取网页内容流
        String entity = EntityUtils.toString(httpEntity, "gbk");    //以字符串的形式(需设置编码)
        System.out.println(code + "\n" + entity);   //输出所获得的内容
        EntityUtils.consume(httpEntity);    //关闭内容流
    }
}
