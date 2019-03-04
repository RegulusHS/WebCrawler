package Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class jsoupDemo01 {
    public static void main(String[] args) {
        try {
            //该网址通过两种方法都能请求到内容
//            Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").get();
            Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").post();
            System.out.println(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
