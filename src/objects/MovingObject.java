package objects;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class MovingObject {
	protected int x, y, width, height;
	private ImageIcon sprite;
	
	public MovingObject(int x, int y, int width, int height, ImageIcon sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void draw(Graphics g) {
        sprite.paintIcon(null, g, x, y);
    }
}
