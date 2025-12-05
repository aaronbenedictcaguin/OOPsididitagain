package main;

import javax.swing.*;

import units.SelectedTeam;

import java.awt.*;

public class Menu extends JPanel {

    public Menu(JFrame frame) {

        int tileSize = 70;
        int cols = 10;
        int rows = 12;
        int offsetX = 40;
        int offsetY = 40;

        int width = cols * tileSize + offsetX * 2;
        int height = rows * tileSize + offsetY * 2;

        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);

        this.setLayout(new GridBagLayout());

        JLabel title = new JLabel("STARLIGHT VOYAGERS");
        title.setFont(new Font("Serif", Font.BOLD, 36));
        title.setForeground(Color.WHITE);

        JButton startButton = new JButton("Start Game");
        JButton exitButton = new JButton("Exit");

        startButton.setPreferredSize(new Dimension(200, 40));
        exitButton.setPreferredSize(new Dimension(200, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 0, 15, 0);
        this.add(title, gbc);

        gbc.gridy = 1;
        this.add(startButton, gbc);

        gbc.gridy = 2;
        this.add(exitButton, gbc);

        startButton.addActionListener(e -> {

            if (SelectedTeam.team != null && SelectedTeam.team.size() == 4) {
                Board board = new Board(SelectedTeam.team);
                frame.setContentPane(board);
                frame.pack();
                frame.revalidate();
                frame.repaint();
            } 
            else {
                CharacterSelect cs = new CharacterSelect(frame);
                frame.setContentPane(cs);
                frame.pack();
                frame.revalidate();
                frame.repaint();
            }
        });

        exitButton.addActionListener(e -> System.exit(0));
    }
}