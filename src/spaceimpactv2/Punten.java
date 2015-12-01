package spaceimpactv2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class Punten {
    private int aantal;
    
    public Punten() {
        aantal = 0;
    }
    
    public void teken(Graphics g) {
        g.setColor(Color.yellow);
        g.setFont(new Font("TimesRoman", Font.BOLD, 50));
        g.drawString(Integer.toString(aantal), 10, 60);
    }
    
    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
    
    public int getAantal() {
        return aantal;
    }
}