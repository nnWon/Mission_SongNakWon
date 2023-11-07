package com.ll.dto;

import com.ll.domain.Quote;
import lombok.Getter;

@Getter
public class JsonFileDto {

    private int id;
    private String content;
    private String author;

    public JsonFileDto(int id, Quote quote) {
        this.id = id;
        this.content = quote.getQuote();
        this.author = quote.getSpeaker();
    }
}
