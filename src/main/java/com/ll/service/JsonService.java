package com.ll.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.domain.Quote;
import com.ll.dto.JsonFileDto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final static String FILE_PATH = "./file/";
    private final static String JSON_FILE_NAME = "quotes_json.txt";

    public void build(Map<Integer, Quote> storage) throws IOException {
        List<JsonFileDto> jsonFileDtos = storageToJsonFileDtos(storage);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH + JSON_FILE_NAME), jsonFileDtos);
    }

    private List<JsonFileDto> storageToJsonFileDtos(Map<Integer, Quote> storage) {
        return storage.entrySet().stream()
                .map(m -> new JsonFileDto(m.getKey(), m.getValue()))
                .collect(Collectors.toList());
    }

    public static String getJSON_FILE_NAME() {
        return JSON_FILE_NAME;
    }
}
