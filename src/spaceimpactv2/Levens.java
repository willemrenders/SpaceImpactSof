package spaceimpactv2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;

class Levens {
    private int aantal;
    private String gedaan;
    private ImageIcon hartje;
    
    public Levens () {
        aantal = 5;
        gedaan = "Game Over!";
        hartje = new ImageIcon(SpaceImpactV2.class.getResource("/images/hartje.png"));
    }
    
    public void teken(Graphics g) {
        for (int i=aantal; i>0; i--)
        {
            hartje.paintIcon(null, g, 930 - ((i - 1) * 55), 20);
        }
    }
    
    public void gedaan(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.BOLD, 100));
        g.drawString(gedaan, 215, 375);
    }
    
    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
    
    public int getAantal() {
        return aantal;
    }
}