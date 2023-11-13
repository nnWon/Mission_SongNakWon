package com.ll.service.command;

import com.ll.dto.ParamDto;
import com.ll.storage.Storage;
import com.ll.validator.ParamValidator;

import java.util.Map;
import java.util.Scanner;

public class DeleteCommandService implements CommandService {

    private final ParamValidator paramValidator = new ParamValidator();

    @Override
    public void execute(Scanner scanner, ParamDto paramDto, Storage storage) {
        if (paramValidator.validateParam(paramDto,storage)) {
            deleteQuote(paramDto, storage);
        }
    }

    private static void deleteQuote(ParamDto paramDto, Storage storage) {
        Map<String, String> queryString = paramDto.getQueryString();
        int id = Integer.parseInt(queryString.get("id"));
        storage.getQuotes().remove(id);
        System.out.println(id + "번 명언은 삭제되었습니다.");
    }
}
