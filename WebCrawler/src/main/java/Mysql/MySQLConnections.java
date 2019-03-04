package Mysql;

import java.sql.*;

public class MySQLConnections {
    private String driver = "";
    private String dbURL = "";
    private String user = "";
    private String password = "";
    private static MySQLConnections connection = null;
    private MySQLConnections() {
        driver = "com.mysql.jdbc.Driver";   //数据库驱动
        dbURL = "jdbc:mysql://127.0.0.1:3306/csdncourse";   //JDBC的URL
        user = "root";
        password = "root";
        System.out.println("dbURL:" + dbURL);
    }

    public static Connection getConnection() {
        Connection conn = null;
        if(connection == null) {
            try {
                connection = new MySQLConnections();    //初始化
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        try {
            Class.forName(connection.driver);   //调用Class.forName()方法加载驱动程序
            conn = DriverManager.getConnection(connection.dbURL, connection.user, connection.password); //建立数据库连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Connection con = getConnection();
        Statement stmt =con.createStatement();  //创建Statement对象
        System.out.println("成功连接到数据库！");
        String sql = "select * from carsales";  //要执行的SQL
        ResultSet rs = stmt.executeQuery(sql);  //结果集
        while(rs.next()) {
            //输出1,2 两列
            System.out.print(rs.getString(1) + "\t");
            System.out.print(rs.getString(2) + "\t");
        }
        con.close();    //关闭连接
    }
}
