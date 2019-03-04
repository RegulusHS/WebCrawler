package HttpClient.timeout;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class demo02 {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();   //初始化httpClient
        HttpGet httpGet = new HttpGet("http://www.w3school.com.cn/b.asp");  //Get请求(Post方法相似)
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();    //设置请求和传输超时时间
        httpGet.setConfig(requestConfig);   //httpGet信息配置
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = EntityUtils.toString(response.getEntity(), "gbk");
        System.out.println(result);
    }
}
