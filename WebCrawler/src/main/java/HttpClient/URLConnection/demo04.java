package HttpClient.URLConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class demo04 {

    public static void main(String[] args) throws Exception {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("171.97.67.160", 3128)); //设置代理
        URL url = new URL("http://www.w3school.com.cn/b.asp");
//        URLConnection conn = url.openConnection(proxy); //以代理的方式建立连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "gbk"));
        String line;
        String html = "";
        while((line = bufferedReader.readLine()) != null) {
            html += line;
        }
        System.out.println(html);
    }
}
