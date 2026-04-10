package game;

import java.awt.event.*;

public class KeyHandler implements KeyListener {

    public boolean up, down, left, right, enter, attack;

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) up = true;
        if (code == KeyEvent.VK_S) down = true;
        if (code == KeyEvent.VK_A) left = true;
        if (code == KeyEvent.VK_D) right = true;
        if (code == KeyEvent.VK_ENTER) enter = true;
        if (code == KeyEvent.VK_SPACE) attack = true;
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) up = false;
        if (code == KeyEvent.VK_S) down = false;
        if (code == KeyEvent.VK_A) left = false;
        if (code == KeyEvent.VK_D) right = false;
        if (code == KeyEvent.VK_ENTER) enter = false;
        if (code == KeyEvent.VK_SPACE) attack = false;
    }

    public void keyTyped(KeyEvent e) {}
}