package com.ll.service.command;

import com.ll.domain.Quote;
import com.ll.dto.ParamDto;
import com.ll.storage.Storage;

import java.util.Map;
import java.util.Scanner;

public class CreateCommandService implements CommandService{
    @Override
    public void execute(Scanner scanner, ParamDto paramDto, Storage storage) {
        System.out.print("명언 : ");
        String quote = scanner.nextLine();
        System.out.print("작가 : ");
        String speaker = scanner.nextLine();
        storage.addQuote(new Quote(quote, speaker));
        System.out.println(storage.getSequence() + "번 명언이 등록되었습니다.");
    }
}
