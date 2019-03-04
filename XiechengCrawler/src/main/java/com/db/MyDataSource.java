package com.db;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class MyDataSource {
    public static DataSource getDataSource(String connectURI) {
        BasicDataSource ds = new BasicDataSource();
        //MySQL的jdbc驱动
        ds.setDriverClassName("com.mysql.jdbc.Driver"); //驱动名称
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setUrl(connectURI);
        return ds;
    }
}
