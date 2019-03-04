package com.db;

import com.model.XieChengModel;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class MySQLControl {
    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/csdncourse");
    static QueryRunner qr = new QueryRunner(ds);
    public static void executeInsert(List<XieChengModel> data) {
        Object[][] params = new Object[data.size()][4]; //数据的维度
        for (int i=0; i<params.length; i++) {
            params[i][0] = data.get(i).getUserId();
            params[i][1] = data.get(i).getUserType();
            params[i][2] = data.get(i).getScore();
            params[i][3] = data.get(i).getCommentInfo();
        }
        try {
            qr.batch("insert into xiecomment(user_id,user_type,score,comment_info)"
                        + "values (?,?,?,?)", params);
            System.out.println("执行数据库完毕！"+"成功插入数据："+data.size()+"条");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
