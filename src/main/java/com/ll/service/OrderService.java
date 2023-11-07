package com.ll.service;

import com.ll.domain.Quote;
import com.ll.dto.ParamDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OrderService {

    private final JsonService jsonService = new JsonService();
    private final Map<Integer, Quote> storage;
    private int sequence;

/*
    public OrderService() {
        storage.put(1, new Quote("현재를 사랑하라", "작자미상"));
        storage.put(2, new Quote("과거에 집착하지 마라", "작자미상"));
        this.sequence = storage.size();
    }
*/

    public OrderService(Map<Integer, Quote> storage) {
        this.storage = storage;
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
            if (validateParam(paramDto)) {
                deleteQuote(paramDto);
            }
            return;
        }

        if (order.equals("수정")) {
            if (validateParam(paramDto)) {
                updateQuote(paramDto, scanner);
            }
            return;
        }

        if (order.equals("빌드")) {
            buildToJson(storage);

        }
    }

    private void buildToJson(Map<Integer, Quote> storage) {
        try {
            jsonService.build(storage);
        } catch (IOException e) {
            System.out.println("빌드에 실패하였습니다.");
            throw new RuntimeException(e);
        }
        System.out.println(JsonService.getJSON_FILE_NAME() + " 파일의 내용이 갱신되었습니다.");
    }

    private boolean validateParam(ParamDto paramDto) {
        if (!isContainQueryString(paramDto)) {
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

    public void updateQuote(ParamDto paramDto, Scanner scanner) {

        Map<String, String> queryString = paramDto.getQueryString();
        int id = Integer.parseInt(queryString.get("id"));

        Quote quote = storage.get(id);
        System.out.printf("명언(기존) :%s \n", quote.getQuote());
        System.out.print("명언 :");
        String newQuote = scanner.nextLine();

        System.out.printf("작가(기존) :%s \n", quote.getSpeaker());
        System.out.print("작가 :");
        String newSpeaker = scanner.nextLine();
        Quote updateQuote = new Quote(newQuote, newSpeaker);
        storage.put(id, updateQuote);
        System.out.println(id + "번 명언이 수정되었습니다.");
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
        return new HashMap<>(storage);
    }
}
