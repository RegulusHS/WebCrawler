package com.main;


import com.db.MySQLControl;
import com.model.XieChengModel;
import com.parse.HtmlParse;
import com.util.HttpRequest;

import java.util.List;

public class CrawlerMain {
    public static void main(String[] args) {
        HttpRequest httpRequest = new HttpRequest();
        for (int i = 0; i < 100; i++) {
            int page = i;   //爬取的页数
            //拼接url
            String everypageurl = "http://vacations.ctrip.com/grouptravel/p1740331s0"+"-comment-" + page + ".html";
            //调用HttpRequest中的方法获取网页内容
            String html = httpRequest.getHTMLContentByHttpGetMethod(everypageurl, "utf-8");
            //针对每页的HTML,调用HtmlParse类中的方法进行解析
            List<XieChengModel> datalist = HtmlParse.getData(html);
            //针对已获取的数据，调用MySQLControl中的方法插入数据
            MySQLControl.executeInsert(datalist);
        }
    }
}
