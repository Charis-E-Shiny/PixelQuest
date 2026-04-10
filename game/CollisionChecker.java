package game;

import entity.Player;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public boolean checkTile(Player player, int nextX, int nextY) {

        int col = nextX / gp.tileSize;
        int row = nextY / gp.tileSize;

        if (row < 0 || col < 0 || row >= gp.maxRow || col >= gp.maxCol) {
            return true;
        }

        return gp.tileM.map[row][col] == 1;
    }
}
