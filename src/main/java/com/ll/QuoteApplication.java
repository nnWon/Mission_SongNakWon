package com.ll;

import com.ll.dto.ParamDto;
import com.ll.file.FileSystem;
import com.ll.service.OrderController;

import java.util.Scanner;

public class QuoteApplication {

    private final Scanner scanner = new Scanner(System.in);
    private final FileSystem fileSystem = new FileSystem();
    private final OrderController orderController;

    public QuoteApplication() {
        fileSystem.createSaveFile();
        this.orderController = new OrderController(fileSystem.LoadQuotes());
    }

    public void run() {
        System.out.println("== 명언 앱 ==");
        executeCommand();
        saveResult();
        scanner.close();
    }

    private void executeCommand() {
        String order = "";
        while (!order.equals("종료")) {
            System.out.print("명령) ");
            order = scanner.nextLine();
            orderController.execute(scanner, new ParamDto(order));
        }
    }

    private void saveResult() {
        fileSystem.SaveQuotes(orderController.getStorage());
    }
}
