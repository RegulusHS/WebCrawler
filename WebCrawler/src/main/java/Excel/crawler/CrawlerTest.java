package Excel.crawler;

import jxl.write.WriteException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 1. 请求URL
 * 2. 解析URL对应的HTML内容
 * 3. 封装数据到 post.xls
 */
public class CrawlerTest {
    public static void main(String[] args) throws IOException, WriteException {
        //获取数据
        List<PostModel> data = crawerData("http://baa.bitauto.com/CS55/");
        //存储数据
        DataToExcelByPoi.writeInfoListToExcel("F:\\learn\\WebCrawler\\src\\main\\java\\Excel\\data/post2.xls", "sheet1", data);
    }

    //解析数据
    private static List<PostModel> crawerData(String url) throws IOException {
        //所爬数据封装于集合中
        List<PostModel> datalist = new ArrayList<PostModel>();
        //获取URL对应的HTML内容
        Document doc = Jsoup.connect(url).timeout(5000).get();
        //定位需要采集的每个帖子
        Elements elements = doc.select("div[class=line-bg]").select("div[class=postslist_xh]");
        for (Element ele : elements) {
            String post_id = ele.select("li.bt").select("a").attr("href").split("-")[1].replaceAll("\\D", "");
            String post_title = ele.select("li.bt").select("a").text();
            //创建对象和封装数据
            PostModel model = new PostModel();
            model.setPost_id(post_id);
            model.setPost_title(post_title);
            datalist.add(model);
        }
        return datalist;
    }
}
