package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Fire Emblem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Menu menu = new Menu(frame);
        frame.setContentPane(menu);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}