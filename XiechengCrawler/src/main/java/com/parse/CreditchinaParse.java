package com.parse;

import com.alibaba.fastjson.JSON;
import com.model.DetailInfoModel;
import com.model.ListInfoModel;

import java.util.List;

public class CreditchinaParse {
    public static List<ListInfoModel> getcodedata(String jsondata) {
        //采用Fastjson解析json数据, 这里已将json数据封装到对象Fastjson中了
        List<ListInfoModel> jsonList = JSON.parseArray(jsondata.split("results\":")[1].split(",\"exportButtonValid")[0], ListInfoModel.class);
        //返回所爬数据
        return jsonList;
    }
    public static List<DetailInfoModel> getbasicinfodata(String jsonData) {
        System.out.println(jsonData.split("result\":")[1].split(",\"status")[0]);
        List<DetailInfoModel> jsonlist = JSON.parseArray("["+jsonData.split("result\":")[1].split(",\"status")[0]+"]",DetailInfoModel.class);
        System.out.println(jsonlist.get(0).getId());
        return jsonlist;
    }
}
