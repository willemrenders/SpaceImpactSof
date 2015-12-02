package handlers;

import java.awt.event.KeyEvent;

import engine.*;
import objects.*;

public class KeyboardHandler {
	GameEngine gameEngine;
	public KeyboardHandler(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}
	
	public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                gameEngine.moveSpaceShip(false);
                break;
            case KeyEvent.VK_UP:
            	gameEngine.moveSpaceShip(true);
                break;
            case KeyEvent.VK_SPACE:
            	gameEngine.shoot();
                break;
        }
    }

}
