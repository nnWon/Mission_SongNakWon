package com.ll.file;

import com.ll.domain.Quote;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class FileSystem {
    private final static String FILE_PATH = "./file/quotes.txt";
    private final static Charset CHARSET = StandardCharsets.UTF_8;

    public void SaveQuotes(Map<Integer, Quote> storage) {

        try (FileWriter fw = new FileWriter(FILE_PATH, CHARSET);
             BufferedWriter bw = new BufferedWriter(fw);) {

            for (Integer key : storage.keySet()) {
                Quote quote = storage.get(key);
                String line = key + "," + quote.getQuote() + "," + quote.getSpeaker();
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
