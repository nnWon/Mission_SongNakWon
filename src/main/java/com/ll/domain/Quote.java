package com.ll.domain;

import lombok.Getter;

@Getter
public class Quote {
    private String quote;
    private String speaker;

    public Quote(String quote, String speaker) {
        this.quote = quote;
        this.speaker = speaker;
    }
}
