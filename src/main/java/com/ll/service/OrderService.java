package com.ll.service;

import com.ll.domain.Quote;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OrderService {

    private final Map<Integer, Quote> storage = new HashMap<>();
    private int sequence = 0;

    public void execute(Scanner scanner, String order) {
        if (order.equals("등록")) {
            createQuote(scanner);
        }
    }

    public int createQuote(Scanner scanner) {
        System.out.print("명언 : ");
        String quote = scanner.nextLine();
        System.out.print("작가 : ");
        String speaker = scanner.nextLine();
        storage.put(++sequence, new Quote(quote, speaker));
        System.out.println(sequence + "번 명언이 등록되었습니다.");
        return sequence;
    }
}
