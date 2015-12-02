package objects;

import javax.swing.ImageIcon;

public class EnemySpaceShip extends MovingObject {
	private int dx;
	private int lifetime;
	
	public EnemySpaceShip(int x, int y, int width, int height, int dx, ImageIcon sprite, int lifetime) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.lifetime = lifetime;
	}
	
	public void move() {
		x += dx;
		lifetime += 1;
	}
	
	public boolean checkIfHasToShoot() {
		if (lifetime % 300 == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
