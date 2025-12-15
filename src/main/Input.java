package main;

import units.Unit;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input extends MouseAdapter {

    private final Board board;

    public Input(Board board) {
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int col = (e.getX() - board.offsetX) / board.tileSize;
        int row = (e.getY() - board.offsetY) / board.tileSize;

        Unit clickedUnit = board.getUnit(col, row);

        // 🛑 stop animation of previously selected unit
        if (board.selectedUnit != null) {
            board.selectedUnit.isWalking = false;
            board.selectedUnit.walkFrameIndex = 0;
        }

        // === NORMAL SELECTION ===
        if (clickedUnit != null) {
            System.out.println("PRESSED: Unit selected at Grid (" + col + ", " + row + ")");

            board.onUnitSelected(clickedUnit);

            // 🔥 START WALK ANIMATION
            clickedUnit.isWalking = true;

        } else {
            if (board.currentMode == Board.ActionMode.NONE) {
                board.clearSelection();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        int targetCol = (e.getX() - board.offsetX) / board.tileSize;
        int targetRow = (e.getY() - board.offsetY) / board.tileSize;

        Unit u = board.selectedUnit;
        if (u == null) return;

        System.out.println(
            "DEBUG: hasActedThisTurn = " + u.hasActedThisTurn +
            ", mode = " + board.currentMode
        );

        // ================= MOVE =================
        if (board.currentMode == Board.ActionMode.MOVE) {

            if (u.hasActedThisTurn) {
                board.setActionMode(Board.ActionMode.NONE);
                return;
            }

            if (ValidateMove.isMoveValid(board, u, targetCol, targetRow)) {
                Move m = new Move(board, u, targetCol, targetRow);
                board.makeMove(m);

                u.hasActedThisTurn = true;
            }

            u.isWalking = false;
            u.walkFrameIndex = 0; // back to RK1

            board.setActionMode(Board.ActionMode.NONE);

            if (board.allAlliesActed()) {
                board.endTurn();
            }
            return;
        }

        // ================= ATTACK =================
        if (board.currentMode == Board.ActionMode.ATTACK) {

            if (u.hasActedThisTurn) {
                System.out.println("Unit already acted this turn.");
                board.setActionMode(Board.ActionMode.NONE);
                return;
            }

            if (board.selectedAction != null &&
                board.selectedAction.canTarget(board, u, targetCol, targetRow)) {

                int cost = board.selectedAction.getEnergyCost();

                if (u.hasEnoughEnergy(cost)) {
                    board.selectedAction.execute(board, u, targetCol, targetRow);
                    u.spendEnergy(cost);
                    u.hasActedThisTurn = true;
                }
            }

            u.isWalking = false;
            u.walkFrameIndex = 0; // RK1

            board.selectedAction = null;
            board.setActionMode(Board.ActionMode.NONE);

            if (board.allAlliesActed()) {
                board.endTurn();
            }
        }
    }
}
