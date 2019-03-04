package Fastjson;

import com.alibaba.fastjson.JSON;

public class demo01 {

    public static void main(String[] args) {
        //json对象
        String json = "{\"id\":\"07\",\"language\": \"C++\",\"edition\": \"second\",\"author\": \"E.Balagurusamy\"}";
        //使用fastjson解析Json对象
        BookModel model = JSON.parseObject(json, BookModel.class);
        //输出解析结果
        System.out.println(model.getId() + "\t" + model.getLanguage() + "\t" + model.getEdition());
    }
}
