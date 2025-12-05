package main;

import java.util.*;
import units.*;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    public int tileSize = 70;
    int cols = 10;
    int rows = 12;

    int offsetX = 40;
    int offsetY = 40;

    ArrayList<Unit> units;

    public Board(ArrayList<Unit> selectedUnits) {

        this.units = selectedUnits;

        int width = cols * tileSize + offsetX * 2;
        int height = rows * tileSize + offsetY * 2;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);

        int[] startX = {3, 4, 5, 6};
        int startY = 10;

        for (int i = 0; i < units.size(); i++) {
            units.get(i).setPosition(startX[i], startY);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(offsetX, offsetY, cols * tileSize, rows * tileSize);

        g2d.setColor(Color.BLACK);

        for (int r = 0; r <= rows; r++) {
            int y = offsetY + r * tileSize;
            g2d.drawLine(offsetX, y, offsetX + cols * tileSize, y);
        }

        for (int c = 0; c <= cols; c++) {
            int x = offsetX + c * tileSize;
            g2d.drawLine(x, offsetY, x, offsetY + rows * tileSize);
        }

        for (Unit u : units) {
            u.draw(g2d, tileSize, offsetX, offsetY);
        }
    }

}