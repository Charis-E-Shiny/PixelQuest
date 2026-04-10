package game;

import javax.swing.*;
import java.awt.*;
import entity.Player;
import entity.Enemy;
import map.TileManager;
import ui.Dialogue;

public class GamePanel extends JPanel implements Runnable {

    public int screenWidth = 800, screenHeight = 600;
    public int tileSize = 48;

    public int maxRow = 50, maxCol = 50;

    Thread gameThread;

    public KeyHandler keyH = new KeyHandler();
    public CollisionChecker cChecker = new CollisionChecker(this);

    public Player player = new Player(this, keyH);
    public Enemy enemy = new Enemy(this, 500, 500);
    public TileManager tileM = new TileManager(this);

    public GameState gameState = GameState.DIALOGUE;
    public Dialogue dialogue;

    int level = 1;
    int dialogueTimer = 0;

    int[] startX = {100, 200, 300, 400};
    int[] startY = {100, 150, 200, 250};
    int[] enemyX = {500, 600, 700, 800};
    int[] enemyY = {500, 550, 600, 650};
    int[] endX = {2000, 2100, 2200, 2300};

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.startStory();
        this.requestFocusInWindow();

        player.worldX = startX[level-1];
        player.worldY = startY[level-1];
        enemy.worldX = enemyX[level-1];
        enemy.worldY = enemyY[level-1];
    }

    void startStory() {
        dialogue = new Dialogue(new String[]{
                "The world is broken...",
                "Find the 4 crystals...",
                "Your journey begins..."
        });
    }

    public void nextDialogue() {
        if (!dialogue.next()) gameState = GameState.PLAYING;
    }

    public void checkAttack() {
        if (player.getBounds().intersects(enemy.getBounds())) {
            enemy.health -= 10;
        }
    }

    public void update() {

        if (gameState == GameState.DIALOGUE) {
            dialogueTimer++;
            if (dialogueTimer >= 120) {
                nextDialogue();
                dialogueTimer = 0;
            }
            return;
        }

        if (gameState == GameState.PLAYING) {

            player.update();
            enemy.update(player);

            if (player.getBounds().intersects(enemy.getBounds())) {
                player.health -= 1;
            }

            if (player.health <= 0) gameState = GameState.GAME_OVER;

            if (player.worldX > endX[level-1]) {
                level++;
                if (level > 4) {
                    gameState = GameState.WIN;
                } else {
                    tileM.loadMap("/maps/level" + level + ".txt");

                    player.worldX = startX[level-1];
                    player.worldY = startY[level-1];
                    enemy.worldX = enemyX[level-1];
                    enemy.worldY = enemyY[level-1];

                    dialogue = new Dialogue(new String[]{
                            "Level " + level,
                            "The journey continues..."
                    });

                    gameState = GameState.DIALOGUE;
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        if (player.worldY < enemy.worldY) {
            player.draw(g2);
            enemy.draw(g2);
        } else {
            enemy.draw(g2);
            player.draw(g2);
        }

        drawUI(g2);
    }

    void drawUI(Graphics2D g2) {

        g2.setColor(Color.white);
        g2.drawString("HP: " + player.health, 20, 20);

        if (gameState == GameState.DIALOGUE) {
            g2.setColor(Color.black);
            g2.fillRect(50, 400, 700, 150);

            g2.setColor(Color.white);
            g2.drawString(dialogue.getLine(), 70, 450);
        }

        if (gameState == GameState.GAME_OVER) {
            g2.setFont(new Font("Arial", Font.BOLD, 50));
            g2.drawString("GAME OVER", 200, 300);
        }

        if (gameState == GameState.WIN) {
            g2.setFont(new Font("Arial", Font.BOLD, 50));
            g2.drawString("YOU WIN!", 200, 300);
        }
    }

    public void run() {
        while (true) {
            update();
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
}