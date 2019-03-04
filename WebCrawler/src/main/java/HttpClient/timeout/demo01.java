package HttpClient.timeout;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class demo01 {

    public static void main(String[] args) throws IOException {
        //全部设置为10秒
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(1000).setConnectionRequestTimeout(10000).build();
        //配置HttpClient
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(new HttpGet("http://www.w3school.com.cn/b.asp")); //使用get方法发送请求
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = EntityUtils.toString(response.getEntity(), "gbk");  //获取结果，html
        System.out.println(result); //输出结果
    }
}
