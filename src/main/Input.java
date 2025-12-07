package main;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import units.Unit;

public class Input extends MouseAdapter {
    Board board;


    private int startCol;
    private int startRow;

    public Input(Board board) {
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // DECLARATION of col and row:
        int col = (e.getX() - board.offsetX) / board.tileSize;
        int row = (e.getY() - board.offsetY) / board.tileSize;

        Unit unitXY = board.getUnit(col, row);

        if (unitXY != null) {
            board.selectedUnit = unitXY;

            System.out.println("PRESSED: Unit selected at Grid (" + col + ", " + row + ")");

            this.startCol = unitXY.getX();
            this.startRow = unitXY.getY();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.selectedUnit != null) {

            System.out.println("DRAGGING: Unit position updated to Grid (" + board.selectedUnit.x + ", " + board.selectedUnit.y + ")"); // B
            board.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        int targetCol = (e.getX() - board.offsetX) / board.tileSize;
        int targetRow = (e.getY() - board.offsetY) / board.tileSize;

        if (board.selectedUnit != null) {

            System.out.println("RELEASED: Checking target Grid (" + targetCol + ", " + targetRow + ")");

            ValidateMove v = new ValidateMove();
            if (v.isMoveValid(board, board.selectedUnit, targetCol, targetRow)) {
            } else {
                board.selectedUnit.x = this.startCol;
                board.selectedUnit.y = this.startRow;
            }
        }
    }
}