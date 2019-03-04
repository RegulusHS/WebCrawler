package QueryRunner;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class QueryRunnerTest {
    //获取数据库信息
    static DataSource ds = getDataSource("jdbc:mysql://127.0.0.1:3306/csdncourse");
    //使用QueryRunner库,操作数据库
    static QueryRunner qr = new QueryRunner(ds);
    private static DataSource getDataSource(String connectURI){
        BasicDataSource ds = new BasicDataSource();
        //MySQL的jdbc驱动
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");              //所要连接的数据库名
        ds.setPassword("root");                //MySQL的登陆密码
        ds.setUrl(connectURI);
        return ds;
    }
    //第一类方法
    public void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //按照SQL查询多个结果,这里需要构建Bean对象
    public <T> List<T> getListInfoBySQL(String sql, Class<T> type) {
        List<T> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<T>(type));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //查询一列
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<Object> getListOneBySQL (String sql, String id) {
        List<Object> list = null;
        try {
            list = (List<Object>) qr.query(sql, new ColumnListHandler(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //将所爬数据插入数据库
    public static void insertData(List<CarSaleModel> datalist) {
        Object[][] params = new Object[datalist.size()][2];
        for (int i = 0; i < datalist.size(); i++) {
            //需要存储的字段
            params[i][0] = datalist.get(i).getMonth();
            params[i][1] = datalist.get(i).getSales();
        }
        QueryRunner qr = new QueryRunner(ds);
        try {
            //执行批处理语句
            qr.batch("INSERT INTO carsales(month,sales) VALUES (?,?)", params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("新闻数据入库完毕");
    }

    public static void main(String[] args) {
        QueryRunnerTest queryRunnerTest = new QueryRunnerTest();
        //执行更新操作
        queryRunnerTest.executeUpdate("update carsales set sales = '3000' where month = '2017-10-01'"); //执行更新操作
        System.out.println("更新操作完成!");
        //查询单列
        List<Object> listdata = queryRunnerTest.getListOneBySQL("select sales from carsales", "sales");
        for (int i = 0; i < listdata.size(); i++) {
            System.out.println(listdata.get(i).toString());
        }
        System.out.println("按列读取数据完成！");
        //查询多列
        List<CarSaleModel> mutllistdata = queryRunnerTest.getListInfoBySQL("select month,sales from carsales", CarSaleModel.class);
        for (CarSaleModel model : mutllistdata) {
            System.out.println(model.getMonth() + "\t" + model.getSales());
        }
        System.out.println("读取多列数据完成");
    }
}
