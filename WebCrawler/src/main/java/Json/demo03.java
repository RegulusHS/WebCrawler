package Json;

import org.json.JSONArray;
import org.json.JSONObject;

public class demo03 {

    public static void main(String[] args) {
        //json数组
        String json = "[{\"id\":\"01\",\"language\": \"Java\",\"edition\": \"third\",\"author\": \"Herbert Schildt\"},{\"id\":\"07\", \"language\": \"C++\",\"edition\": \"second\",\"author\": \"E.Balagurusamy\"}]";
        JSONArray jsonArray = new JSONArray(json);  //解析数组
        //循环数组获取数据
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonobj = jsonArray.getJSONObject(i);    //单个JSON对象
            String id = jsonobj.getString("id");
            String language = jsonobj.getString("language");
            String edition = jsonobj.getString("edition");
            System.out.println(id + "\t" + language + "\t" + edition);
        }
    }
}
