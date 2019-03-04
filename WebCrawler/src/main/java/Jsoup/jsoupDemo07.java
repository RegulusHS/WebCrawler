package Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class jsoupDemo07 {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://db.auto.sohu.com/cxdata/xml/sales/model/model1001sales.xml").timeout(5000).get();
        Elements sales_ele = doc.select("sales");
        for (Element elem : sales_ele) {
            int salesNum = Integer.valueOf(elem.attr("salesNum"));
            String date = elem.attr("date");
            System.out.println("月份:" + date + "\t销量" + salesNum);
        }
    }
}
