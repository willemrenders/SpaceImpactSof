package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {
    private int amount;
    
    public Score() {
    	amount = 0;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.setFont(new Font("TimesRoman", Font.BOLD, 50));
        g.drawString(Integer.toString(amount), 10, 60);
    }
    
    public void increase() {
        amount += 1;
    }
}