package com.ll.file;

import com.ll.domain.Quote;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
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

    public Map<Integer, Quote> LoadQuotes() {

        try (FileReader fr = new FileReader(FILE_PATH, CHARSET);
             BufferedReader br = new BufferedReader(fr);) {

            String line = br.readLine();
            Map<Integer, Quote> storage = new HashMap<>();

            if (quotesFileIsEmpty(line)) {
                return storage;
            }

            while (line != null) {
                loadStorage(storage, line);
                line = br.readLine();
            }
            return storage;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadStorage(Map<Integer, Quote> storage, String line) {
        String[] split = line.split(",");
        int key = Integer.parseInt(split[0]);
        Quote quote = new Quote(split[1], split[2]);
        storage.put(key, quote);
    }

    private boolean quotesFileIsEmpty(String line) {
        return line == null;
    }
}
