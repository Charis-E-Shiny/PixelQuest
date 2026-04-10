package entity;

import game.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Enemy {

    GamePanel gp;
    public int worldX, worldY;
    public int health = 50;

    BufferedImage[] frames;
    int frame = 0, counter = 0;

    public Enemy(GamePanel gp, int x, int y) {
        this.gp = gp;
        worldX = x;
        worldY = y;
        load();
    }

    void load() {
        try {
            BufferedImage sheet = ImageIO.read(getClass().getResource("/res/2.png"));

            int w = sheet.getWidth() / 12;
            int h = sheet.getHeight();

            frames = new BufferedImage[12];

            for (int i = 0; i < 12; i++) {
                frames[i] = sheet.getSubimage(i*w, 0, w, h);
            }
        } catch (Exception e) {}
    }

    public Rectangle getBounds() {
        return new Rectangle(worldX, worldY, gp.tileSize, gp.tileSize);
    }

    public void update(Player p) {

        if (p.worldX < worldX) worldX--;
        if (p.worldX > worldX) worldX++;
        if (p.worldY < worldY) worldY--;
        if (p.worldY > worldY) worldY++;

        counter++;
        if (counter > 8) {
            frame = (frame + 1) % 12;
            counter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        int x = worldX - gp.player.worldX + gp.screenWidth/2;
        int y = worldY - gp.player.worldY + gp.screenHeight/2;

        g2.drawImage(frames[frame], x, y, gp.tileSize, gp.tileSize, null);
    }
}