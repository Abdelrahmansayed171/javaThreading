package com.company;

import java.awt.event.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4;  //all labels for textField
    JTextField tf1, tf2;   // others fields
    JButton btn1;  //buttons for signup and clear
    JTextField p1;  // password fields


    GUI()
    {
        setSize(400, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Assignment#1");
        l1 = new JLabel("Creating Primes by multi-Threading");
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2 = new JLabel("N:");
        l3 = new JLabel("Buffer Size:");
        l4 = new JLabel("Output File:");
        tf1 = new JTextField();
        tf2 = new JTextField();
        p1 = new JTextField();
        btn1 = new JButton("Process");
        l1.setBounds(20, 30, 400, 30);
        l2.setBounds(20, 70, 200, 30);
        l3.setBounds(20, 110, 200, 30);
        l4.setBounds(20, 150, 200, 30);
        tf1.setBounds(100, 70, 200, 30);
        tf2.setBounds(100, 110, 200, 30);
        p1.setBounds(100, 150, 200, 30);
        btn1.setBounds(20, 200, 100, 30);
        add(l1);
        add(l2);
        add(tf1);
        add(l3);
        add(tf2);
        add(l4);
        add(p1);
        add(btn1);
        getContentPane().setBackground(Color.GRAY);
        btn1.addActionListener(this);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String s1 = tf1.getText();
        String s2 = tf2.getText();
        String fileName = p1.getText();
        long N = Long.parseLong(s1);
        int capacity = Integer.parseInt(s2);
        Queue.N = N;
        Queue.buff = capacity;
        Queue.fileName = fileName;
        Thread producer = new Producer();

        Thread consumer = new Consumer();

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[])
    {
        new GUI();
    }
}