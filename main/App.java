package main;

import javax.swing.JFrame;
import game.GamePanel;

public class App {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.requestFocus(); // 🔥 VERY IMPORTANT

        gamePanel.startGameThread();
    }
}