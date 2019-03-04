package com.parse;

import com.model.JdModel;
import com.util.TimeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JDParse {
    public static List<JdModel> getData(String html, String keyword) {
        //获取的数据,存放在集合中
        List<JdModel> data = new ArrayList<JdModel>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
        Elements elements = doc.select("li[class=gl-item]");
        for (Element ele : elements) {
            String itemId = ele.attr("data-sku");
            String itemName = ele.select("div[class~=p-name?]").select("em").text();
            String itemPrice = ele.select("div[class=p-price]").select("strong").text();
            String commentNumber = ele.select("div[class=p-commit]").text();
            String itemurl = "https://item.jd.com/" + itemId + ".html";
            String shopname = "";
            String shopurl = "";
            if(ele.select("div[class=p-shop]").select("a[class=curr-shop]").text().length() != 0) {
                shopname = ele.select("div[class=p-shop]").select("a[class=curr-shop]").text();
                shopurl = "https:"+ele.select("div[class=p-shop]").select("a[class=curr-shop]").attr("href");
            }else {
                shopname = "京东自营";
                shopurl = "https://mall.jd.com/index-" + ele.select("div[class=p-shop]").attr("data-shopid") + ".html";
            }
            String crawl_time = TimeUtils.GetNowDate("yyyy-MM-dd HH:mm:ss");
            JdModel jdModel = new JdModel();
            jdModel.setItemId(itemId);
            jdModel.setItemName(itemName);
            jdModel.setItemPrice(itemPrice);
            jdModel.setCommentNumber(commentNumber);
            jdModel.setItemurl(itemurl);
            jdModel.setShopname(shopname);
            jdModel.setShopurl(shopurl);
            jdModel.setCrawlTime(crawl_time);
            data.add(jdModel);
        }
        return data;
    }
}
