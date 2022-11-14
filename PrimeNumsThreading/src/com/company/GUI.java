package com.company;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void main(String args[]){
        JFrame frame = new JFrame("Prime Producer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,600);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel label = new JLabel("N:");
        JTextField tf = new JTextField(8); // accepts upto 10 characters
        panel1.add(label); // Components Added using Flow Layout
        panel1.add(tf);
        JLabel label2 = new JLabel("Buffer Size:");
        JTextField tf2 = new JTextField(8); // accepts upto 10 characters
        panel1.add(label2); // Components Added using Flow Layout
        panel1.add(tf2);

        frame.getContentPane().add(BorderLayout.NORTH, panel1);
        frame.setVisible(true);
    }
}
