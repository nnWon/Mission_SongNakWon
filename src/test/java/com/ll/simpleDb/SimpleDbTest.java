package com.ll.simpleDb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class SimpleDbTest {

    private SimpleDb simpleDb;

    @Test
    void DbConnectionTest() throws SQLException {
        simpleDb = new SimpleDb("localhost", "root", "test11", "simpleDb__test");
        Connection connection = simpleDb.getConnection();
        System.out.println("connection = "+connection);
        System.out.println("connection.getClass() = "+connection.getClass());
    }
}