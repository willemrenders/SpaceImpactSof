package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Level {
    private int aantal;
    
    public Level() {
        aantal = 1;
    }
    
    public void teken(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 50));
        if (aantal == 1) {
            g.setColor(Color.yellow);
        }
        if (aantal == 2) {
            g.setColor(Color.blue);
        }
        if (aantal == 3) {
            g.setColor(Color.red);
        }
        if (aantal == 4) {
            g.setColor(Color.green);
        }
        if (aantal >= 5) {
            g.setColor(Color.white);
        }
        g.drawString("Level: " + Integer.toString(aantal), 410, 60);
    }
    
    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
    
    public int getAantal() {
        return aantal;
    }
}