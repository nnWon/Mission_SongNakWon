package com.ll.service.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.domain.Quote;
import com.ll.dto.JsonFileDto;
import com.ll.dto.ParamDto;
import com.ll.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BuildCommandService implements CommandService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final static String FILE_PATH = "./file/";
    private final static String JSON_FILE_NAME = "quotes_json.txt";

    @Override
    public void execute(Scanner scanner, ParamDto paramDto, Storage storage) {
        List<JsonFileDto> jsonFileDtos = quotesToJsonFileDtos(storage.getQuotes());
        buildToJson(jsonFileDtos);
    }

    private void buildToJson(List<JsonFileDto> jsonFileDtos) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH + JSON_FILE_NAME), jsonFileDtos);
        } catch (IOException e) {
            System.out.println("빌드에 실패하였습니다.");
            throw new RuntimeException(e);
        }
        System.out.println(JSON_FILE_NAME + " 파일의 내용이 갱신되었습니다.");
    }

    private List<JsonFileDto> quotesToJsonFileDtos(Map<Integer, Quote> quotes) {
        return quotes.entrySet().stream()
                .map(m -> new JsonFileDto(m.getKey(), m.getValue()))
                .collect(Collectors.toList());
    }
}
