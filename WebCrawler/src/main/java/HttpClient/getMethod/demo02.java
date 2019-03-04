package HttpClient.getMethod;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class demo02 {

    public static void main(String[] args) throws IOException {
        HttpClient httpClient = HttpClients.custom().build();   //初始化httpclient
        HttpGet httpGet = new HttpGet("http://www.w3school.com.cn/b.asp");
        HttpResponse httpResponse = null;   //请求响应
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity httpEntity = httpResponse.getEntity();
        System.out.println("get code" + httpResponse.getStatusLine().getStatusCode());
        String entity = EntityUtils.toString(httpEntity, "gbk");
        System.out.println(entity);
    }
}
