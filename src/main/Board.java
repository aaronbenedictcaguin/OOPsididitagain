package main;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import units.Unit;

public class Board extends JPanel {

    public int tileSize = 50;
    int cols = 12;
    int rows = 12;

    public int offsetX = 40;
    public int offsetY = 40;

    ArrayList<Unit> units;

    public Unit selectedUnit;

    public Board(ArrayList<Unit> selectedUnits) {
        this.units = selectedUnits;

        int width = cols * tileSize + offsetX * 2;
        int height = rows * tileSize + offsetY * 2;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);

        int[] startX = {3, 4, 5, 6};
        int startY = 10;

        for (int i = 0; i < units.size(); i++) {
            // setPosition uses grid coordinates (col, row)
            units.get(i).setPosition(startX[i], startY);
        }
    }

    /**
     * Finds and returns a Unit at the given grid coordinates.
     */
    public Unit getUnit(int col, int row) {
        for (Unit u : this.units) {
            // Uses Unit.getX() and Unit.getY(), which return the logical grid coordinates
            if (u.getX() == col && u.getY() == row) {
                return u;
            }
        }
        return null;
    }

    /**
     * Executes a validated Move object, updating the Unit's position and handling
     * any unit blocking the target tile.
     */
    public void makeMove(Move move) {

        // 1. Handle captured/blocked unit removal
        if (move.block != null) {
            this.units.remove(move.block);
        }

        // 2. Update the unit's logical GRID position (col, row)
        move.u.setPosition(move.new_col, move.new_row);

        // 3. Update the unit's PIXEL position (x, y) to visually snap to the center of the new tile
        int snapX = this.offsetX + move.new_col * this.tileSize;
        int snapY = this.offsetY + move.new_row * this.tileSize;

        // Directly updates the public x/y fields (Pixel position)
        move.u.x = snapX;
        move.u.y = snapY;
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
            // The unit's draw method uses the current value of u.x and u.y (the PIXEL position)
            u.draw(g2d, tileSize, offsetX, offsetY);
        }
    }
}