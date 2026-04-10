package map;

import game.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class TileManager {

    GamePanel gp;
    public int[][] map;
    Tile[] tiles;
    BufferedImage tileset;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        map = new int[gp.maxRow][gp.maxCol];
        tiles = new Tile[10];

        loadTiles();
        loadMap("/maps/level1.txt");
    }

    public void loadTiles() {
        try {
            tileset = ImageIO.read(getClass().getResource("/res/Tileset.png"));

            int size = 32;

            tiles[0] = new Tile();
            tiles[0].image = tileset.getSubimage(0, 0, size, size);

            tiles[1] = new Tile();
            tiles[1].image = tileset.getSubimage(32, 0, size, size);
            tiles[1].collision = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String path) {
        try {
            BufferedReader br = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream(path))
            );

            int row = 0;

            while (row < gp.maxRow) {
                String line = br.readLine();
                if (line == null) break;
                String[] numbers = line.split(" ");
                if (numbers.length != gp.maxCol) {
                    System.err.println("Invalid map line: " + line);
                    continue;
                }

                for (int col = 0; col < gp.maxCol; col++) {
                    map[row][col] = Integer.parseInt(numbers[col]);
                }
                row++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        for (int row = 0; row < gp.maxRow; row++) {
            for (int col = 0; col < gp.maxCol; col++) {

                int worldX = col * gp.tileSize;
                int worldY = row * gp.tileSize;

                int screenX = worldX - gp.player.worldX + gp.screenWidth / 2;
                int screenY = worldY - gp.player.worldY + gp.screenHeight / 2;

                if (tiles[map[row][col]] != null) {
                    g2.drawImage(tiles[map[row][col]].image,
                            screenX, screenY,
                            gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}