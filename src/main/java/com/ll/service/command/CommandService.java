package com.ll.service.command;

import com.ll.domain.Quote;
import com.ll.dto.ParamDto;
import com.ll.storage.Storage;

import java.util.Map;
import java.util.Scanner;

public interface CommandService {
    void execute(Scanner scanner, ParamDto paramDto, Storage storage);
}
