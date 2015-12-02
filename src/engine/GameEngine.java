package engine;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import GUI.GUI;
import objects.*;

public class GameEngine {
	private ImageIcon imgSpaceShip, imgEnemySpaceShip, imgRocket, imgEnemyRocket, imgLive;
    private SpaceShip spaceShip;
    private EnemySpaceShip enemySpaceShip;
    private Rocket rocket;
    private Lives lives;
    private Score score;
    private ArrayList<EnemySpaceShip> listOfEnemySpaceShips;
    private ArrayList<Rocket> listOfRockets;
    private Random rand = new Random();
	
	public GameEngine() {
    	imgSpaceShip = new ImageIcon(GUI.class.getResource("/images/SpaceShip.png"));
    	imgEnemySpaceShip = new ImageIcon(GUI.class.getResource("/images/EnemySpaceShip.png"));
    	imgRocket = new ImageIcon(GUI.class.getResource("/images/Rocket.png"));
    	imgEnemyRocket = new ImageIcon(GUI.class.getResource("/images/EnemyRocket.png"));
    	imgLive = new ImageIcon(GUI.class.getResource("/images/Live.png"));
    	
    	listOfEnemySpaceShips = new ArrayList<EnemySpaceShip>();
    	listOfRockets = new ArrayList<Rocket>();
    	spaceShip = new SpaceShip(0, 300, 90, 90, 20, imgSpaceShip);
    	
    	lives = new Lives(5, "Game over!", imgLive);
    	score = new Score();
	}
	
	public void checkCollisions() {
		for (int i = listOfRockets.size() - 1; i >= 0; i--) {
			// COLLISION ENEMY WITH FRIENDLY ROCKET
			if (listOfRockets.get(i).CheckIfFriendly()) {
				for (int j = listOfEnemySpaceShips.size() - 1; j >= 0; j--) {
					if ((listOfRockets.get(i).getX()) + listOfRockets.get(i).getWidth() >= listOfEnemySpaceShips.get(j).getX()
                            && listOfRockets.get(i).getX() + listOfRockets.get(i).getWidth() <= listOfEnemySpaceShips.get(j).getX() + listOfEnemySpaceShips.get(j).getWidth()
                            && listOfRockets.get(i).getY() + listOfRockets.get(i).getHeight() > listOfEnemySpaceShips.get(j).getY()
                            && listOfRockets.get(i).getY() < listOfEnemySpaceShips.get(j).getY() + listOfEnemySpaceShips.get(j).getHeight()) {
						listOfRockets.remove(i);
						listOfEnemySpaceShips.remove(j);
						score.increase();
					}
				}
			}
			// COLLISION SPACE SHIP WITH ENEMY ROCKET
			else {
				if (listOfRockets.get(i).getY() + listOfRockets.get(i).getHeight() >= spaceShip.getY()
                        && listOfRockets.get(i).getY() <= spaceShip.getY() + spaceShip.getHeight()
                        && listOfRockets.get(i).getX() <= spaceShip.getWidth()) {
					listOfRockets.remove(i);
                    lives.decrease();
                }
			}
		}
		// COLLISION SPACE SHIP WITH ENEMY SPACE SHIP
		for (int i = listOfEnemySpaceShips.size() - 1; i >= 0; i--) {
			if (listOfEnemySpaceShips.get(i).getY() + listOfEnemySpaceShips.get(i).getHeight() >= spaceShip.getY()
                    && listOfEnemySpaceShips.get(i).getY() <= spaceShip.getY() + spaceShip.getHeight()
                    && listOfEnemySpaceShips.get(i).getX() <= spaceShip.getWidth()) {
				listOfEnemySpaceShips.remove(i);
                lives.decrease();
            }
		}
	}
	
	public void cleanAllObjectsOutOfScreen() {
		for (int i = listOfRockets.size() - 1; i >= 0; i--) {
			if (listOfRockets.get(i).CheckIfFriendly() && listOfRockets.get(i).getX() > 1000) {
				listOfRockets.remove(i);
			}
			else if (!listOfRockets.get(i).CheckIfFriendly() && listOfRockets.get(i).getX() < -listOfRockets.get(i).getWidth()) {
				listOfRockets.remove(i);
			}
		}
		for (int i = listOfEnemySpaceShips.size() - 1; i >= 0; i--) {
			if (listOfEnemySpaceShips.get(i).getX() < -listOfEnemySpaceShips.get(i).getWidth()) {
				listOfEnemySpaceShips.remove(i);
			}
		}
	}
	
	// MOVE ALL TIME DEPENDING OBJECTS
	public void moveObjects() {
		for (EnemySpaceShip ess : listOfEnemySpaceShips) {
			ess.move();
		}
		for (Rocket r : listOfRockets) {
			r.move();
		}
	}
	
	// MOVE YOUR SPACE SHIP
	public void moveSpaceShip(boolean moveUp) {
		if (moveUp) {
			spaceShip.goUp();
		}
		else {
			spaceShip.goDown();
		}
	}
	
	// SHOOT A ROCKET
	public void shoot() {
		rocket = new Rocket(spaceShip.getX(), spaceShip.getY()+(spaceShip.getHeight() - 16)/2, 52, 16, 4, imgRocket, true);
		listOfRockets.add(rocket);
	}
	
	// CHECK EVERY ENEMY IF HIS LIFETIME EXCEEDS 500 AND SHOOT IF IT HAS
	public void checkIfAnyEnemyHasToShoot() {
		for (EnemySpaceShip ess : listOfEnemySpaceShips) {
			if (ess.checkIfHasToShoot()) {
				rocket = new Rocket(ess.getX(), ess.getY()+(ess.getHeight() - 16)/2, 52, 16, -2, imgEnemyRocket, false);
				listOfRockets.add(rocket);
			}
		}
	}
	
	// CHECK IF A NEW ENEMY NEEDS TO BE MADE
	public void checkIfNewEnemyHasToBeMade() {
		if (rand.nextInt(500) == 1) {
			enemySpaceShip = new EnemySpaceShip(1000, rand.nextInt(630), 90, 90, -1, imgEnemySpaceShip, 200);
			listOfEnemySpaceShips.add(enemySpaceShip);
		}
	}
	
	// DRAW ALL OBJECTS
	public void drawAllObjects(Graphics g) {
		lives.draw(g);
		score.draw(g);
		
		for (EnemySpaceShip ess : listOfEnemySpaceShips) {
			ess.draw(g);
		}
		for (Rocket r : listOfRockets) {
			r.draw(g);
		}
		spaceShip.draw(g);
	}
}
