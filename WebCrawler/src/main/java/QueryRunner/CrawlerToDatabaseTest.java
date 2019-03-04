package QueryRunner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerToDatabaseTest {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://db.auto.sohu.com/cxdata/xml/sales/model/model1001sales.xml").timeout(5000).get();
        List<CarSaleModel> datalist = new ArrayList<CarSaleModel>();
        Elements sales_ele = doc.select("sales");
        for (Element elem : sales_ele) {
            String salesnum = elem.attr("salesnum");
            String date = elem.attr("date");
            CarSaleModel model = new CarSaleModel();
            model.setMonth(date);
            model.setSales(salesnum);
            datalist.add(model);
            System.out.println("月份:" + date + "\t销量:" + salesnum);
        }
        QueryRunnerTest.insertData(datalist);
    }
}
