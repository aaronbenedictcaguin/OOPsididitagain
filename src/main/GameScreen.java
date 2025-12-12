package main;

import units.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameScreen extends JPanel {

    private Board board;
    private ActionPanel actionPanel;

    public GameScreen(JFrame frame, ArrayList<Unit> team) {
        setLayout(new BorderLayout());

        actionPanel = new ActionPanel();
        board = new Board(team, actionPanel);

        Input inputHandler = new Input(board);
        board.addMouseListener(inputHandler);
        board.addMouseMotionListener(inputHandler);

        add(board, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
    }

    public Board getBoard() {
        return board;
    }
}
