package com.ll.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.domain.Quote;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonService {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final static String FILE_PATH = "./file/";
    private final static String JSON_FILE_NAME = "quotes_json.txt";

    public void build(Map<Integer, Quote> storage) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH+JSON_FILE_NAME), storage);
    }

    public static String getJSON_FILE_NAME() {
        return JSON_FILE_NAME;
    }
}
