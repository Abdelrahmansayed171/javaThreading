package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
            throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        long N = scanner.nextLong();
        int capacity = scanner.nextInt();
        Scanner scr = new Scanner(System.in);
        String fileName = scr.nextLine();
        Producer producer = new Producer(capacity,N);
        Consumer consumer = new Consumer(producer,fileName);

        producer.setName("Producer-1");
        consumer.setName("Consumer-1");
        producer.start();
        consumer.start();

    }

}