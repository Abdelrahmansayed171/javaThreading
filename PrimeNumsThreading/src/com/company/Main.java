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
        Queue.N = N;
        Queue.buff = capacity;
        Queue.fileName = fileName;
        Thread producer = new Producer();

        Thread consumer = new Consumer();

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

    }

}