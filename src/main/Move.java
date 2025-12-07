package main;
import units.Unit;

public class Move {

    int og_col;
    int og_row;

    int new_col;
    int new_row;

    Unit u;
    Unit block;

    public Move(Board board, Unit u, int new_col, int new_row) {

        this.og_col = u.getX();
        this.og_row = u.getY();

        this.new_col = new_col;
        this.new_row = new_row;

        this.u = u;

        this.block = board.getUnit(new_col, new_row);
    }
}