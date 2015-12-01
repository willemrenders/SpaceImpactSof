package spaceimpactv2;

import java.awt.Graphics;
import javax.swing.ImageIcon;

class PlainTegenstander {

    private int x, y, grootte;
    private ImageIcon vliegtuigTegenstander, vliegtuigTegenstander02, vliegtuigTegenstander03, vliegtuigTegenstander04;
    private int dx;
    private int tijd;
    private int type;
    private boolean actief = true;

    public PlainTegenstander(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        grootte = 90;
        dx = 2;
        tijd = 100;
        vliegtuigTegenstander = new ImageIcon(SpaceImpactV2.class.getResource("/images/plainTegenstander.png"));
        vliegtuigTegenstander02 = new ImageIcon(SpaceImpactV2.class.getResource("/images/plainTegenstander02.png"));
        vliegtuigTegenstander03 = new ImageIcon(SpaceImpactV2.class.getResource("/images/plainTegenstander03.png"));
        vliegtuigTegenstander04 = new ImageIcon(SpaceImpactV2.class.getResource("/images/plainTegenstander04.png"));
    }
    
    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public boolean isActief() {
        return actief;
    }

    public void setActief(boolean actief) {
        this.actief = actief;
    }
    
    

    public int getGrootte() {
        return grootte;
    }

    public void teken(Graphics g) {
        if (type == 1) {
            vliegtuigTegenstander.paintIcon(null, g, x, y);
        }
        
        else if (type == 2) {
            vliegtuigTegenstander02.paintIcon(null, g, x, y);
        }
        
        else if (type == 3) {
            vliegtuigTegenstander03.paintIcon(null, g, x, y);
        }
        else if (type == 4) {
            vliegtuigTegenstander04.paintIcon(null, g, x, y);
        }
        
    }

    public void beweeg() {
        x -= dx;
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
    
    public int getTijd() {
        return tijd;
    }

    public void setTijd(int tijd) {
        this.tijd = tijd;
    }
}