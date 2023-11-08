package com.ll.service;

import com.ll.domain.Quote;
import com.ll.dto.ParamDto;
import com.ll.storage.Storage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.TestUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class OrderServiceTest {

    private final OrderService orderService = new OrderService(new Storage());

    @BeforeEach
    void beforeEach() {
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

        orderService.execute(scanner, new ParamDto(order));
    }

    @Test
    @DisplayName("명언 등록이 수행되면 Map에 저장된다..")
    void createQuoteStoredInMap() {
        String order = "등록";
        int cycle = 2;
        for (int i = 0; i < cycle; i++) {
            Scanner scanner = TestUtil.genScanner("""
                    현재를 사랑하라.
                    작자미상
                    """.stripIndent());
            orderService.execute(scanner, new ParamDto(order));
        }
        Assertions.assertThat(orderService.getStorageSize()).isEqualTo(cycle);
    }

    @Test
    @DisplayName("명언을 등록할 때마다 명언 번호가 증가한다.")
    void createQuoteCountNumberTest() {
        String order = "등록";
        int cycle = 10;
        for (int i = 0; i < cycle; i++) {
            Scanner scanner = TestUtil.genScanner("""
                    현재를 사랑하라.
                    작자미상
                    """.stripIndent());
            orderService.execute(scanner, new ParamDto(order));
        }
        Assertions.assertThat(orderService.getStorage().size()).isEqualTo(cycle);
    }

    @Test
    @DisplayName("삭제?id=번호 형태로 전달하면 명언이 삭제된다.")
    void deleteQuoteTest() {
        Map<Integer, Quote> map = new HashMap<>();
        map.put(1, new Quote("현재를 사랑하라", "작자미상"));
        map.put(2, new Quote("과거에 집착하지 마라", "작자미상"));
        map.put(3, new Quote("사람은 오로지 가슴으로만 올바로 볼 수 있다. 본질적인 것은 눈에 보이지 않는다", "생텍쥐페리"));
        OrderService orderService = new OrderService(new Storage(3, map));

        orderService.deleteQuote(new ParamDto("삭제?id=2"));

        Assertions.assertThat(orderService.getStorageSize()).isEqualTo(2);
        Assertions.assertThat(orderService.getStorage().get(2)).isNull();
    }

    @Test
    @DisplayName("수정?id=번호 형태로 전달하면 명언이 수정된다.")
    void updateQuoteTest() {
        Map<Integer, Quote> map = new HashMap<>();
        map.put(1, new Quote("현재를 사랑하라", "작자미상"));
        map.put(2, new Quote("과거에 집착하지 마라", "작자미상"));
        map.put(3, new Quote("사람은 오로지 가슴으로만 올바로 볼 수 있다. 본질적인 것은 눈에 보이지 않는다", "생텍쥐페리"));
        OrderService orderService = new OrderService(new Storage(3, map));

        Scanner scanner = TestUtil.genScanner("""
                새로운 명언.
                새로운 작자
                """.stripIndent());
        orderService.updateQuote(new ParamDto("수정?id=2"), scanner);
        Quote updateQuote = orderService.getStorage().get(2);

        Assertions.assertThat(orderService.getStorageSize()).isEqualTo(3);
        Assertions.assertThat(updateQuote.getQuote()).isEqualTo("새로운 명언.");
        Assertions.assertThat(updateQuote.getSpeaker()).isEqualTo("새로운 작자");
    }
}