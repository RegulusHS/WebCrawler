package com.main;

import com.db.MySQLControlForCreditchina;
import com.model.DetailInfoModel;
import com.model.ListInfoModel;
import com.parse.CreditchinaParse;
import com.util.HttpRequestForCreditchina;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CreditChinaDetailInfoMain {

    public static HttpRequestForCreditchina request = new HttpRequestForCreditchina();

    public static void main(String[] args) throws InterruptedException {
        request.initSSLClient();
        List<ListInfoModel> codelist = MySQLControlForCreditchina.getListInfoBySQL("select encryStr from companycode where idCardOrOrgCode NOT IN (select regno from detailinfo)", ListInfoModel.class);
        Queue<String> queue = new LinkedList<String>();
        for (int i = 0; i < codelist.size(); i++) {
            String encryStr = codelist.get(i).getEncryStr();
            queue.offer(encryStr);
        }
        boolean t = true;
        int count = 1;
        while (t) {
            if(queue.isEmpty()) {
                t = false;
            } else {
                String encryStr = queue.poll();
                String url = "https://www.creditchina.gov.cn/api/credit_info_detail?encryStr="+encryStr+"%0A";
                System.out.println(url);
                String json = request.getHTMLContentByHttpGetMethod(url, "utf-8");
                if(json.contains("访问受限") || json.startsWith("<!DOCTYPE")) {
                    queue.offer(encryStr);  //如果访问受限,则继续添加da队列中，否则执行解析
                    //设置休息时间
                    Thread.sleep(35*1000);
                    System.out.println("休息");
                }else {
                    //解析数据
                    List<DetailInfoModel> infoModels = CreditchinaParse.getbasicinfodata(json);
                    MySQLControlForCreditchina.executeInsertDetailInfo(infoModels);
                }
                if(count%3 == 0) {
                    //产生随机数
                    int m = (int)(Math.random()*30) + 10;
                    Thread.sleep(m*1000);
                }
                count++;
            }
        }
    }
}
