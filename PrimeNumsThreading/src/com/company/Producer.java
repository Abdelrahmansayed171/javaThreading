package com.company;

public class Producer extends Thread {
    @Override
    public void run() {
        try {
            Queue.produce();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
