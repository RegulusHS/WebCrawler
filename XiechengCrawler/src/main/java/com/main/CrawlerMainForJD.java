package com.main;

import com.db.MySQLControlForJD;
import com.model.JdModel;
import com.parse.JDParse;
import com.util.HttpRequestForJD;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class CrawlerMainForJD {
    public static void main(String[] args) throws IOException, URISyntaxException {
        //设置关键词
        String keyword = "手机";
        //输入价格区间,页面上是整型,所以这里也要是整型
        int firstPrice = 2000;
        int endPrice = 5000;
        //输入爬取的总页数
        int sumPageNumber = 10;
        for (int i = 1; i < sumPageNumber; i++) {
            //请求网页内容
            String html = HttpRequestForJD.getRawHtml(keyword, sumPageNumber, firstPrice, endPrice);
            //解析网页内容
            List<JdModel> datalist =  JDParse.getData(html, keyword);
            //将抓取的数据插入数据库,插入数据库前,需要建表
            MySQLControlForJD.executeInsert(datalist);
        }
    }
}
