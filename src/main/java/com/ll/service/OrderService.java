package com.ll.service;

import java.util.Scanner;

public class OrderService {

    public void execute(Scanner scanner, String order) {
        if (order.equals("등록")){
            System.out.print("명언 : ");
            String quote = scanner.nextLine();
            System.out.print("작가 : ");
            String speaker = scanner.nextLine();
            System.out.println("명언이 등록되었습니다.");
        }
    }
}
