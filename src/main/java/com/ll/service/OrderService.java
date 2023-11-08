package com.ll.service;

import com.ll.domain.Quote;
import com.ll.dto.ParamDto;
import com.ll.service.command.*;
import com.ll.storage.Storage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OrderService {

    private final Map<String, CommandService> commandServiceMap = new HashMap<>();
    private final Storage storage;

    public OrderService(Storage storage) {
        initCommandServiceMap();
        this.storage = storage;
    }

    private void initCommandServiceMap() {
        this.commandServiceMap.put("등록", new CreateCommandService());
        this.commandServiceMap.put("목록", new ListCommandService());
        this.commandServiceMap.put("삭제", new DeleteCommandService());
        this.commandServiceMap.put("수정", new UpdateCommandService());
        this.commandServiceMap.put("빌드", new BuildCommandService());
    }

    public void execute(Scanner scanner, ParamDto paramDto) {

        String order = paramDto.getOrder();
        CommandService command = commandServiceMap.get(order);
        if (command != null) {
            command.execute(scanner, paramDto, storage);
        }
    }

    //Test용 코드
    public int getStorageSize() {
        return storage.getSize();
    }

    public void clear() {
        storage.clear();
    }

    public Map<Integer, Quote> getStorage() {
        return new HashMap<>(storage.getQuotes());
    }
}
