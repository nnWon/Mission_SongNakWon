package com.ll;

import com.ll.service.OrderService;

import java.util.Scanner;

public class QuoteApplication {

    private final Scanner scanner = new Scanner(System.in);
    private final OrderService orderService = new OrderService();
    public void run(){
        System.out.println("== 명언 앱 ==");

        String order = "";
        while (!order.equals("종료")){
            System.out.print("명령) ");
            order = scanner.nextLine();
            orderService.execute(scanner,order);
        }
        scanner.close();
    }
}
