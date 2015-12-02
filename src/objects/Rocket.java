package objects;

import javax.swing.ImageIcon;

public class Rocket extends MovingObject {
	private int dx;
	private boolean friendly;
	
	public Rocket(int x, int y, int width, int height, int dx, ImageIcon sprite, boolean friendly) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.friendly = friendly;
	}
	
	public void move() {
		x += dx;
	}
	
	public boolean CheckIfFriendly() {
		return friendly;
	}
}
