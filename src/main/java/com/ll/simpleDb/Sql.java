package com.ll.simpleDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Sql {

    private final Connection connection;
    private StringBuilder sb = new StringBuilder();
    private List<Object> params = new ArrayList<>();

    public Sql(Connection connection) {
        this.connection = connection;
    }

    public Sql append(String sql) {
        sb.append(sql + " ");
        return this;
    }

    public Sql append(String sql, Object param) {
        sb.append(sql + " ");
        params.add(param);
        return this;
    }

    public long insert() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        long result = 0L;
        System.out.println("sql = "+sb);
        try {
            connection = this.connection;
            preparedStatement = connection.prepareStatement(sb.toString());
            if (!params.isEmpty()) {
                for (int i = 0; i < params.size(); i++) {
                    Object param = params.get(i);
                    if (param instanceof String) {
                        preparedStatement.setString(i+1, (String) param);
                    }
                }
            }

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, preparedStatement, null);
        }
        return result;
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
