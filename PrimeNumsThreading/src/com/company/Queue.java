package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Queue {
// Create a list shared by producer and consumer
    // Size of list is 2.

    public static int buff;
    public static long N;
    public static String fileName;
    public static LinkedList<Integer> list = new LinkedList<>();

    private static boolean isPrime(int n) {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }
    public static int max,cnt = 0;
    public static boolean khlast = false;
    public static void produce() throws InterruptedException {
        int value = 2;
        while (true) {
            synchronized(Queue.class) {
                while (list.size() == buff)
                    Queue.class.wait();

//                    for (int i = 1; i <= N; i++) {
                if (isPrime(value)) {
                    max = value;
                    list.add(value);
                    System.out.println("produced: " + value);
                }
                if (value + 1 >= N) {
                        Thread.yield();
                        khlast = true;
                    break;
                } else {
                    value++;
                }
//                    }

                Queue.class.notify();

//                    Thread.sleep(1000);
            }
        }
    }

    public static void consume() throws InterruptedException {
        while (true) {
            synchronized (Queue.class) {
                while (list.size() == 0)
                    Queue.class.wait();

                // to retrieve the first job in the list
                int val = list.removeFirst();
                System.out.println("consumed: " + val);

                try {
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("./" + fileName, true));
                    writer.write("\"" + val + "\", ");
                    cnt++;
                    writer.close();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                if(val == max && khlast){
                    Thread.yield();
                    break;
                }
                Queue.class.notify();

//                    Thread.sleep(1000);
            }
        }
    }
}

