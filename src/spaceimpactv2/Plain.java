package spaceimpactv2;

import java.awt.Graphics;
import javax.swing.ImageIcon;

class Plain {

    private int x, y, grootte;
    private ImageIcon vliegtuig01, vliegtuig02, vliegtuig03, vliegtuig04;
    private ImageIcon ontploffing;
    private int dy;
    private int type;

    public Plain(int x, int y) {
        this.x = x;
        this.y = y;
        type = 1;
        dy = 30;
        grootte = 90;
        vliegtuig01 = new ImageIcon(SpaceImpactV2.class.getResource("/images/plain01.png"));
        vliegtuig02 = new ImageIcon(SpaceImpactV2.class.getResource("/images/plain02.png"));
        vliegtuig03 = new ImageIcon(SpaceImpactV2.class.getResource("/images/plain03.png"));
        vliegtuig04 = new ImageIcon(SpaceImpactV2.class.getResource("/images/plain04.png"));
        ontploffing = new ImageIcon(SpaceImpactV2.class.getResource("/images/ontploffing.png"));
    }

    public void teken(Graphics g) {
        if (type == 1) {
            vliegtuig01.paintIcon(null, g, x, y);
        }
        if (type == 2) {
            vliegtuig02.paintIcon(null, g, x, y);
        }
        if (type == 3) {
            vliegtuig03.paintIcon(null, g, x, y);
        }
        if (type == 4) {
            vliegtuig04.paintIcon(null, g, x, y);
        }
        
    }
    
    public void tekenDood(Graphics g) {
        ontploffing.paintIcon(null, g, x, y);
    }

    public void omhoog() {
        if (y > 0) {
            y -= dy;
        }
    }

    public void omlaag() {
        if (y < 600) {
            y += dy;
        }
    }
    
    public int getGrootte() {
        return grootte;
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
    
    public void setType(int type) {
        this.type = type;
    }

}