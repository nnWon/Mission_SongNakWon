package com.ll.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.TestUtil;

import java.util.Scanner;

class OrderServiceTest {

    private final OrderService orderService = new OrderService();

    @BeforeEach
    void beforeEach(){
        orderService.clear();
    }
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

    @Test
    @DisplayName("명언 등록이 수행되면 Map에 저장된다..")
    void createQuoteStoredInMap() {
        int cycle = 2;
        for (int i = 0; i < cycle; i++) {
            Scanner scanner = TestUtil.genScanner("""
                현재를 사랑하라.
                작자미상
                """.stripIndent());
            orderService.createQuote(scanner);
        }
        Assertions.assertThat(orderService.getStorageSize()).isEqualTo(cycle);
    }

    @Test
    @DisplayName("명언을 등록할 때마다 명언 번호가 증가한다.")
    void createQuoteCountNumberTest() {
        int result = 0;
        int cycle = 10;
        for (int i = 0; i < cycle; i++) {
            Scanner scanner = TestUtil.genScanner("""
                현재를 사랑하라.
                작자미상
                """.stripIndent());
            result = orderService.createQuote(scanner);
        }
        Assertions.assertThat(result).isEqualTo(cycle);
    }
}