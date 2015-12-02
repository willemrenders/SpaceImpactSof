package objects;

import java.awt.Graphics;
import javax.swing.ImageIcon;

import GUI.GUI;

public class Pauze {
    
    private ImageIcon pauze;
    private Boolean actief;
    
    public Pauze() {
        actief = false;
        pauze = new ImageIcon(GUI.class.getResource("/images/pauze.png"));
    }
    
    public void teken(Graphics g) {
        pauze.paintIcon(null, g, 0, 0);
    }
    
    public boolean isActief() {
        return actief;
    }

    public void setActief(boolean actief) {
        this.actief = actief;
    }
    
}