package com.ll.service.command;

import com.ll.domain.Quote;
import com.ll.dto.ParamDto;
import com.ll.storage.Storage;
import com.ll.validator.ParamValidator;

import java.util.Map;
import java.util.Scanner;

public class UpdateCommandService implements CommandService {

    private final ParamValidator paramValidator = new ParamValidator();

    @Override
    public void execute(Scanner scanner, ParamDto paramDto, Storage storage) {
        if (paramValidator.validateParam(paramDto, storage)) {
            updateQuote(scanner, paramDto, storage);
        }
    }

    private void updateQuote(Scanner scanner, ParamDto paramDto, Storage storage) {
        Map<String, String> queryString = paramDto.getQueryString();
        int id = Integer.parseInt(queryString.get("id"));

        Quote quote = storage.getQuotes().get(id);
        System.out.printf("명언(기존) :%s \n", quote.getQuote());
        System.out.print("명언 :");
        String newQuote = scanner.nextLine();

        System.out.printf("작가(기존) :%s \n", quote.getSpeaker());
        System.out.print("작가 :");
        String newSpeaker = scanner.nextLine();
        Quote updateQuote = new Quote(newQuote, newSpeaker);
        storage.getQuotes().put(id, updateQuote);
        System.out.println(id + "번 명언이 수정되었습니다.");
    }
}
