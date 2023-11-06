package com.ll.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.TestUtil;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private final OrderService orderService = new OrderService();

    @Test
    @DisplayName("명언 등록이 수행되면 등록 메세지가 출력된다.")
    void createQuote() {
        String order = "등록";
        Scanner scanner = TestUtil.genScanner("""
                나의 죽음을 적들에게 알리지 말라!
                이순신
                종료
                """.stripIndent());

        orderService.execute(scanner, order);
    }
}