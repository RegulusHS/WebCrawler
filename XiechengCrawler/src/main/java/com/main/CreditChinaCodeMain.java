package com.main;


import com.db.MySQLControlForCreditchina;
import com.model.ListInfoModel;
import com.parse.CreditchinaParse;
import com.util.HttpRequestForCreditchina;

import java.util.List;

public class CreditChinaCodeMain {
    public static HttpRequestForCreditchina request = new HttpRequestForCreditchina();

    public static void main(String[] args) {
        //初始化安全协议,防止不让访问
        request.initSSLClient();
        //请求守信名单数据,需要设置页数,这里共5页
        for (int i = 1; i < 6; i++) {
            String html = request.getHTMLContentByHttpGetMethod("https://www.creditchina.gov.cn/api/credit_info_search?objectType=2&page="+i+"&pageSize=10&creditType=2&_=1551678308266", "utf-8");
            //解析红名单数据
            List<ListInfoModel> list = CreditchinaParse.getcodedata(html);
            //存储数据
            MySQLControlForCreditchina.executeInsertCodeInfo(list);
        }
    }
}
