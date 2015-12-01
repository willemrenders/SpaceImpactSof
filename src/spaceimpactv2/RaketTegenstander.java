package spaceimpactv2;

import java.awt.Graphics;
import javax.swing.ImageIcon;

class RaketTegenstander {

    private int x, y, breedte, hoogte;
    private ImageIcon raketTegenstander;
    private int dx, dy;
    private int type;
    private boolean actief = true;

    public RaketTegenstander(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        breedte = 52;
        hoogte = 16;
        dx = 3;
        dy = 1;
        raketTegenstander = new ImageIcon(SpaceImpactV2.class.getResource("/images/raketTegenstander.png"));
    }

    public boolean isActief() {
        return actief;
    }

    public void setActief(boolean actief) {
        this.actief = actief;
    }
    
    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public int getBreedte() {
        return breedte;
    }

    public int getHoogte() {
        return hoogte;
    }

    public void teken(Graphics g) {
        raketTegenstander.paintIcon(null, g, x, y);
    }

    public void beweeg() {
        x -= dx;
    }
    
    public void beweegOmhoog() {
        y -= dy;
    }
    
    public void beweegOmlaag() {
        y += dy;
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