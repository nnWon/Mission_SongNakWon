package com.ll.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ParamDto {
    private String order;
    private Map<String, String> queryString = new HashMap<>();

    public ParamDto(String orderString) {
        if (orderString.contains("?")) {
            String[] split = orderString.split("\\?");
            this.order = split[0];
            this.queryString = parsingQueryString(split[1]);
            return;
        }
        this.order = orderString;
    }

    private Map<String, String> parsingQueryString(String str) {
        Map<String, String> queryString = new HashMap<>();
        String[] split = str.split("&");
        for (String keyAndValue : split) {
            String[] keyAndValueSplit = keyAndValue.split("=");
            String key = keyAndValueSplit[0];
            String value = keyAndValueSplit[1];
            queryString.put(key, value);
        }
        return queryString;
    }
}
