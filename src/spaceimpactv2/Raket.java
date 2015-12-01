package spaceimpactv2;

import java.awt.Graphics;
import javax.swing.ImageIcon;

class Raket {

    private int x, y, breedte, hoogte;
    private ImageIcon raket;
    private int dx;
    private boolean actief = true;

    public Raket(int x, int y) {
        this.x = x;
        this.y = y;
        breedte = 62;
        hoogte = 16;
        dx = 5;
        raket = new ImageIcon(SpaceImpactV2.class.getResource("/images/raket.png"));
    }

    public boolean isActief() {
        return actief;
    }

    public void setActief(boolean actief) {
        this.actief = actief;
    }

    public int getBreedte() {
        return breedte;
    }

    public int getHoogte() {
        return hoogte;
    }

    public void teken(Graphics g) {
        raket.paintIcon(null, g, x, y);
    }

    public void beweeg() {
        x += dx;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}