package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;

import GUI.GUI;

public class Lives {
    private int amount;
    private String note;
    private ImageIcon sprite;
    
    public Lives (int amount, String note, ImageIcon sprite) {
    	this.amount = amount;
    	this.note = note;
    	this.sprite = sprite;
    }
    
    public void draw(Graphics g) {
        for (int i=amount; i>0; i--)
        {
            sprite.paintIcon(null, g, 930 - ((i - 1) * 55), 20);
        }
    }
    
    public void gedaan(Graphics g) {
    	if (amount <= 0) {
    		g.setColor(Color.RED);
            g.setFont(new Font("TimesRoman", Font.BOLD, 100));
            g.drawString(note, 215, 375);
    	}
    }
    
    public void decrease() {
    	amount--;
    }
    
    public int getAmount() {
        return amount;
    }
}