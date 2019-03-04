package Gson;

import com.google.gson.Gson;

import java.util.List;

public class demo02 {

    public static void main(String[] args) {
        //复杂一点的JSON数据
        String json = "{\"goodRateShow\":99,\"poorRateShow\":1,\"poorCountStr\":\"500+\",\"book\": [{\"id\":\"01\",\"language\": \"Java\",\"edition\": \"third\",\"author\": \"Herbert Schildt\"},{\"id\":\"07\", \"language\": \"C++\",\"edition\": \"second\",\"author\": \"E.Balagurusamy\"}]}";
        Gson gson = new Gson(); //初始化操作
        //转化成Java对象
        BookSummaryModel summaryModel = gson.fromJson(json, BookSummaryModel.class);
        //对象中拿到集合
        List<BookModel> listmodel = summaryModel.getBook();
        //输出数据
        for (BookModel model : listmodel) {
            System.out.println(summaryModel.getGoodRateShow() + "\t" + summaryModel.getPoorCountStr() + "\t" + model.getId() + "\t" + model.getLanguage() + "\t" + model.getEdition());
        }
    }
}
