package com.ll.simpleDb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class SimpleDbTest {

    private SimpleDb simpleDb;

    @BeforeAll
    public void beforeAll() throws SQLException {
        simpleDb = new SimpleDb("localhost", "root", "test11", "simpleDb__test");
        //simpleDb.setDevMode(true);
        createArticleTable();
    }

    private void createArticleTable() throws SQLException {
        simpleDb.run("DROP TABLE IF EXISTS article");

        simpleDb.run("""                                                
                CREATE TABLE article (
                    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
                    PRIMARY KEY(id),
                    createdDate DATETIME NOT NULL,
                    modifiedDate DATETIME NOT NULL,
                    title VARCHAR(100) NOT NULL,
                    `body` TEXT NOT NULL,
                    isBlind BIT(1) NOT NULL DEFAULT 0
                )
                """);
    }

    @Test
    public void makeArticleTestData() {
        IntStream.rangeClosed(1, 6).forEach(no -> {
            boolean isBlind = no > 3;
            String title = "제목%d".formatted(no);
            String body = "내용%d".formatted(no);

            simpleDb.run("""
                    INSERT INTO article
                    SET createdDate = NOW(),
                    modifiedDate = NOW(),
                    title = ?,
                    `body` = ?,
                    isBlind = ?
                    """, title, body, isBlind);
        });
    }

    @Test
    void DbConnectionTest() throws SQLException {
        simpleDb = new SimpleDb("localhost", "root", "test11", "simpleDb__test");
        Connection connection = simpleDb.getConnection();
        System.out.println("connection = " + connection);
        System.out.println("connection.getClass() = " + connection.getClass());
    }
}