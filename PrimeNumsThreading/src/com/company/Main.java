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


        boolean isPrime(int n) {
            // Corner case
            if (n <= 1)
                return false;

            // Check from 2 to n-1
            for (int i = 2; i < n; i++)
                if (n % i == 0)
                    return false;

            return true;
        }
//        boolean khlast = false;
        // Function called by producer thread
        public void produce(long N, int buff) throws InterruptedException {
            int value = 2;
            while (true) {
                synchronized (this) {

                    while (list.size() == buff)
                        wait();

//                    for (int i = 1; i <= N; i++) {
                    if (isPrime(value)) {
                        list.add(value);
                        System.out.println("produced: " + value);
                    }
                    if(value+1 >=N){
//                        khlast=true;
                        break;
                    }
                    else{
                        value++;
                    }
//                    }

                    notify();

//                    Thread.sleep(1000);
                }
            }
        }

        // Function called by consumer thread
        public void consume(String fileName) throws InterruptedException {
            while (true) {
                synchronized (this) {
                    // consumer thread waits while list
                    // is empty
                    while (list.size() == 0)
                        wait();

                    // to retrieve the first job in the list
                    int val = list.removeFirst();
                    System.out.println("consumed: " + val);

                    try {
                        BufferedWriter writer = new BufferedWriter(
                                new FileWriter("./" + fileName, true));
                        writer.write("\"" + val + "\", ");
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
//                    if(khlast)break;
//                    System.out.println("Consumer consumed-"
//                            + val);

                    // Wake up producer thread
                    notify();

                    // and sleep
//                    Thread.sleep(1000);
                }
            }
        }
    }
}