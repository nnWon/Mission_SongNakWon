package com.ll.storage;

import com.ll.domain.Quote;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Storage {
    private int sequence;
    private final Map<Integer, Quote> quotes;

    public Storage() {
        this.sequence = 0;
        this.quotes = new HashMap<>();
    }

    public Storage(int sequence, Map<Integer, Quote> quotes) {
        this.sequence = sequence;
        this.quotes = quotes;
    }

    public void addQuote(Quote quote) {
        quotes.put(++sequence, quote);
    }


    public int getSize() {
        return quotes.size();
    }

    public void clear() {
        quotes.clear();
    }
}
