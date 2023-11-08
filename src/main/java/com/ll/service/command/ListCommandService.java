package com.ll.service.command;

import com.ll.dto.ParamDto;
import com.ll.storage.Storage;

import java.util.Scanner;

public class ListCommandService implements CommandService{
    @Override
    public void execute(Scanner scanner, ParamDto paramDto, Storage storage) {
        storage.getQuotes().forEach((key, value) -> System.out.printf("%d / %s / %s \n", key, value.getSpeaker(), value.getQuote()));
    }
}
