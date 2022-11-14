package com.company;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args)
            throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        long N = scanner.nextLong();
        int capacity = scanner.nextInt();
        Scanner scr = new Scanner(System.in);
        String fileName = scr.nextLine();
        final Queue pc = new Queue(capacity);

        // Create producer thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce(N, capacity);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume(fileName);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        t1.start();
        t2.start();


        t1.join();
        t2.join();

    }


    // This class has a list, producer (adds items to list
    // and consumer (removes items).
    public static class Queue {

        // Create a list shared by producer and consumer
        // Size of list is 2.

        private int buff;

        LinkedList<Integer> list = new LinkedList<>();


        Queue(int buff) {
            this.buff = buff;

        }

        ;




}