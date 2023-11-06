package com.ll.service;

import com.ll.domain.Quote;
import com.ll.dto.ParamDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OrderService {

    private final Map<Integer, Quote> storage = new HashMap<>();
    private int sequence = 0;

    public OrderService() {
        storage.put(1, new Quote("현재를 사랑하라", "작자미상"));
        storage.put(2, new Quote("과거에 집착하지 마라", "작자미상"));
        this.sequence = storage.size();
    }

    public void execute(Scanner scanner, ParamDto paramDto) {
        String order = paramDto.getOrder();
        if (order.equals("등록")) {
            createQuote(scanner);
            return;
        }

        if (order.equals("목록")) {
            printQuoteList();
            return;
        }
    }

    private void printQuoteList() {
        for (int sequence : storage.keySet()) {
            printQuote(sequence);
        }
    }

    private void printQuote(int sequence) {
        Quote quote = storage.get(sequence);
        System.out.printf("%d // %s // %s \n", sequence, quote.getSpeaker(), quote.getQuote());
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

    //Test용 코드
    public int getStorageSize() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
    }
}
