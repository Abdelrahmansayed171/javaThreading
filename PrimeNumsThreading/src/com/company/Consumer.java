package com.company;

public class Consumer extends Thread{
    @Override
    public void run() {
        try {
            Queue.consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
