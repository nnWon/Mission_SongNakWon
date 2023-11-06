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

    public OrderService(Map<Integer, Quote> storage) {
        for (int key : storage.keySet()) {
            this.storage.put(key, storage.get(key));
        }
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

        if (order.equals("삭제")) {
            if (validateParam(paramDto)){
                deleteQuote(paramDto);
            }
            return;
        }
    }

    private boolean validateParam(ParamDto paramDto) {
        if(!isContainQueryString(paramDto)) {
            return false;
        }

        Map<String, String> queryString = paramDto.getQueryString();
        int id = Integer.parseInt(queryString.get("id"));
        if (storage.get(id) == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return false;
        }
        return true;
    }

    public void deleteQuote(ParamDto paramDto) {
        Map<String, String> queryString = paramDto.getQueryString();
        int id = Integer.parseInt(queryString.get("id"));
        storage.remove(id);
        System.out.println(id + "번 명언은 삭제되었습니다.");

    }

    private boolean isContainQueryString(ParamDto paramDto) {
        if (paramDto.getQueryString().isEmpty()) {
            System.out.println(paramDto.getOrder() + "?id={번호}형식으로 입력하세요.");
            return false;
        }
        return true;
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

    public Map<Integer, Quote> getStorage() {
        return storage;
    }
}
