package com.ll.simpleDb;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class SimpleDb {

    private DataSource dataSource;

    public SimpleDb(String url, String username, String password, String dbName) {
        String formattedUrl = urlFormatting(url);
        this.dataSource = initDataSource(formattedUrl, username, password, dbName);
    }

    private String urlFormatting(String url) {
        return "jdbc:mysql://" + url + ":3306/";
    }

    private DataSource initDataSource(String url, String username, String password, String dbName) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url + dbName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    public void run(String sql){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    public void run(String sql, String title, String body, boolean isBlind) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,body);
            preparedStatement.setBoolean(3,isBlind);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
