package com.ll.simpleDb;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SimpleDb {

    private DataSource dataSource;

    public SimpleDb(String url, String username, String password, String dbName) {
        String formattedUrl = urlFormatting(url);
        this.dataSource = initDataSource(formattedUrl, username, password, dbName);
    }

    private String urlFormatting(String url) {
        return "jdbc:mysql://"+url+":3306/";
    }

    private DataSource initDataSource(String url, String username, String password, String dbName){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url+dbName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
