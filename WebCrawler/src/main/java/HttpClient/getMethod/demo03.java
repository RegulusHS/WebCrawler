package HttpClient.getMethod;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class demo03 {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();   //初始化HttpClient
        HttpGet httpGet = new HttpGet("http://www.w3school.com.cn/b.asp");
        CloseableHttpResponse response = null;  //请求响应
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String entity = EntityUtils.toString(response.getEntity(), "gbk");
        System.out.println(entity);
        response.close();
    }
}
