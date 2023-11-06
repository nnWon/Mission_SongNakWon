package com.ll.file;

import com.ll.domain.Quote;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemTest {

    private final FileSystem fileSystem = new FileSystem();

    @Test
    @DisplayName("Quote Map을 전달하면 파일로 저장한다.")
    void SaveQuotesTest(){
        Map<Integer, Quote> storage = new HashMap<>();
        storage.put(1, new Quote("현재를 사랑하라", "작자미상"));
        storage.put(2, new Quote("과거에 집착하지 마라", "작자미상"));
        storage.put(3, new Quote("사람은 오로지 가슴으로만 올바로 볼 수 있다. 본질적인 것은 눈에 보이지 않는다", "생텍쥐페리"));

        fileSystem.SaveQuotes(storage);
    }

    @Test
    @DisplayName("quotes 파일을 읽어서 Map 형태로 반환한다.")
    void LoadQuotesTest(){
        SaveQuotesTest();
        FileSystem fileSystem = new FileSystem();
        Map<Integer, Quote> quotes = fileSystem.LoadQuotes();

        Assertions.assertThat(quotes.size()).isEqualTo(3);
    }
}