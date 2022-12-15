package com.company;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Producer extends Thread {

    private int buffer;
    private long currentPrime;
    private long N;

    private final Queue <Long> messages = new LinkedList<>();


    Producer(int buffer,long n){
        this.buffer = buffer;
        this.N = n;
        this.currentPrime = 2;
    }

    @Override
    public void run() {
        try {
            while (this.currentPrime < this.N) {
                produce();
            }
        } catch (Exception exp) {
        }
    }


    private boolean isPrime (long num){
        boolean yesPrime = true;
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0){
                yesPrime = false;
                break;
            }
        }
        return yesPrime;
    }


    private synchronized void produce() throws Exception {
        while (messages.size() == this.buffer) {
            System.out.println("Queue limit reached. Waiting for consumer");
            wait();
            System.out.println("Producer got notification from consumer");
        }

        if(isPrime(currentPrime)){
            messages.add(this.currentPrime);
            System.out.println("Producer produced " + this.currentPrime);
            currentPrime++;
            notify();
        }
        else{
            currentPrime++;
            this.produce();
        }
    }

    public synchronized Long consume() throws Exception {
        notify();
        while (messages.isEmpty()) {
            wait();
        }
        return messages.remove();
    }

    public long getCurrentPrime() {
        return currentPrime;
    }

    public long getN() {
        return N;
    }
}
