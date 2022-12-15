package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Consumer extends Thread {

    private Producer producer;
    private String fileName;
    private int cnt;
    private long max;

    public Consumer(Producer producer, String fileName){
        this.producer = producer;
        this.fileName = fileName;
        this.cnt = 0;
    }

    @Override
    public void run() {
        try {
            while (true){
                Long data = producer.consume();
                System.out.println("Consumed by: " + Thread.currentThread().getName() + " data: " + data);

                cnt++;

                max = data;

                try {
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("./" + this.fileName, true));
                    writer.write("\"" + data + "\", ");
                    writer.close();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

//                Thread.sleep(500);
            }
        } catch (Exception exp){}
    }

    public int getCnt() {
        return cnt;
    }

    public long getMax() {
        return max;
    }
}
