package Jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 增加头信息
 */
public class jsoupDemo02 {

    public static void main(String[] args) throws IOException {
        Connection connect = Jsoup.connect("http://www.w3school.com.cn/b.asp"); //获取请求连接
        //使用Map集合存储头信息
        Map<String, String> header = new HashMap<String, String>();
        header.put("Host", "www.w3school.com.cn");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        header.put("Accept-Language", "zh-CN,zh;q=0.9");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Upgrade-Insecure-Requests", "1");
        header.put("Connection", "keep-alive");
        //添加头信息
        Connection conheader = connect.headers(header);
        //使用ger()请求页面内容
        Document document = conheader.get();
        //输出页面内容
        System.out.println(document);
    }
}
