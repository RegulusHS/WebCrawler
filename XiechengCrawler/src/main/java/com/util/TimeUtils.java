package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    //获取当前时间——输入为时间格式,如yyyy-MM-dd HH:mm:ss
    public static String GetNowDate(String formate) {
        String temp_str = "";
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        temp_str = sdf.format(dt);
        return temp_str;
    }
}
