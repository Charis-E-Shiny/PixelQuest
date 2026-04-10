package entity;
import game.GameState;
import game.GamePanel;
import game.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Player {

    GamePanel gp;
    public int worldX = 50, worldY = 50;
    int speed = 4;

    public int health = 100;

    KeyHandler keyH;

    BufferedImage[][] sprites;
    int direction = 0;
    int frame = 0;
    int counter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        loadSprites();
    }

    void loadSprites() {
        try {
            BufferedImage sheet = ImageIO.read(getClass().getResource("/res/Run.png"));

            int w = sheet.getWidth() / 12;
            int h = sheet.getHeight() / 4;

            sprites = new BufferedImage[4][12];

            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 12; c++) {
                    sprites[r][c] = sheet.getSubimage(c*w, r*h, w, h);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public Rectangle getBounds() {
        return new Rectangle(worldX, worldY, gp.tileSize, gp.tileSize);
    }

    public void update() {

        if (gp.gameState == GameState.DIALOGUE) {
            return;
        }

        int nextX = worldX;
        int nextY = worldY;

        if (keyH.up) { nextY -= speed; direction = 1; }
        if (keyH.down) { nextY += speed; direction = 0; }
        if (keyH.left) { nextX -= speed; direction = 2; }
        if (keyH.right) { nextX += speed; direction = 3; }

        if (!gp.cChecker.checkTile(this, nextX, nextY)) {
            worldX = nextX;
            worldY = nextY;
        }

        if (keyH.attack) gp.checkAttack();

        counter++;
        if (counter > 5) {
            frame = (frame + 1) % 12;
            counter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        int x = gp.screenWidth / 2;
        int y = gp.screenHeight / 2;

        g2.drawImage(sprites[direction][frame],
                x, y, gp.tileSize, gp.tileSize, null);
    }
}