package com.ll.file;

import com.ll.domain.Quote;
import com.ll.storage.Storage;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileSystem {
    private final static String FILE_PATH = "./file/quotes.txt";
    private final static Charset CHARSET = StandardCharsets.UTF_8;

    public void createSaveFile() {
        Path saveFilePath = Paths.get(FILE_PATH);
        if (Files.exists(saveFilePath)) {
            return;
        }
        try {
            Files.createFile(saveFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


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

    public Storage LoadQuotes() {

        try (FileReader fr = new FileReader(FILE_PATH, CHARSET);
             BufferedReader br = new BufferedReader(fr);) {

            String line = br.readLine();
            Map<Integer, Quote> map = new HashMap<>();

            if (quotesFileIsEmpty(line)) {
                return new Storage(0, map);
            }

            while (line != null) {
                loadStorage(map, line);
                line = br.readLine();
            }

            int sequence = 0;
            for (int key : map.keySet()) {
                if (sequence < key) {
                    sequence = key;
                }
            }
            return new Storage(sequence, map);

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
