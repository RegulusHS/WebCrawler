package Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * jsoup使用中的遍历
 */
public class jsoupDemo05 {

    public static void main(String[] args) throws IOException {
        //获取URL对应的HTML内容
        Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").get();
        //层层定位到要解析的内容，可以发现包含多个li标签
        Elements elements = doc.select("div[id=course]").select("li");
        //遍历每一个li节点
        for (Element element : elements) {
            String title = element.select("a").text();  //.text()为解析标签中的文本内容
            String course_url = element.select("a").attr("href");   //.attr(String)表示获取标签内某一属性的内容
            System.out.println("课程的标题为:" + title + "\t对应的URL为" + course_url);
        }

        //层层定位到要解析的内容，可以发现包含多个li标签
        System.out.println("通过id提取的结果为:" + doc.getElementById("course").text());    //通过id定位，并获取文本
        System.out.println("通过tag提取的结果为:" + doc.getElementById("course").getElementsByTag("a").text());
        System.out.println("通过attr提取的结果为:" + doc.getElementById("course").getElementsByAttribute("href").text());
        System.out.println("通过class提取的元素为:" + doc.getElementsByClass("browserscripting"));  //获取HTML文档中指定class名的所有元素
    }
}
