package objects;

import javax.swing.ImageIcon;

public class SpaceShip extends MovingObject {
	private int dy;
	public SpaceShip(int x, int y, int width, int height, int dy, ImageIcon sprite) {
		super(x, y, width, height, sprite);
		this.dy = dy;
	}
	public void goUp() {
		if (y > 0) {
			y -= dy;
		}
		
	}
	public void goDown() {
		if (y + height < 675)
		y += dy;
	}
}
