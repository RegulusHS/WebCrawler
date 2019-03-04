package HttpClient.getMethod;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class demo01 {

    public static void main(String[] args) throws IOException {
        HttpClient client = new DefaultHttpClient();    //初始化httpclient
        String personalUrl = "http://www.w3school.com.cn/b.asp";    //请求的地址URL
        HttpGet getMethod = new HttpGet(personalUrl);   //get方法请求
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");    //初始化HTTP响应
        response = client.execute(getMethod);   //执行响应
        String status = response.getStatusLine().toString();    //响应状态
        int StatusCode = response.getStatusLine().getStatusCode();  //响应状态码
        ProtocolVersion protocolVersion = response.getProtocolVersion();    //协议的版本号
        String phrase = response.getStatusLine().getReasonPhrase();     //是否ok
        if(StatusCode == 200) {
            //获取实体内容,这里为HTML内容
            String entity = EntityUtils.toString(response.getEntity(), "gbk");
            //输出实体内容
            System.out.println(entity);
            EntityUtils.consume(response.getEntity());  //消耗实体
        }else {
            //关闭HttpEntity的流实体
            EntityUtils.consume(response.getEntity());  //消耗实体
        }
    }
}
