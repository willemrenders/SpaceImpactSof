package GUI;

import javax.swing.*;

import engine.GameEngine;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.util.Collections;
import javax.sound.sampled.*;

import objects.*;
import handlers.*;

class Paneel extends JPanel {
    private GameEngine gameEngine;
	private ImageIcon imgBackground;
	//private TimerHandler timerHandler;
	//private KeyboardHandler keyboardHandler;
	
	private Timer timer;
	
    
    public Paneel() {
    	gameEngine = new GameEngine();
    	imgBackground = new ImageIcon(GUI.class.getResource("/images/Background.jpg"));
    	//timerHandler = new TimerHandler(gameEngine);
    	//keyboardHandler = new KeyboardHandler(gameEngine);
    	
    	timer = new Timer(10, new TimerHandler());
    	timer.start();
    	
    	addKeyListener(new ToetsenbordHandler());
        
    }

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	imgBackground.paintIcon(this, g, 0, 0);
        
        gameEngine.drawAllObjects(g);
    	
        this.requestFocusInWindow();
        
    }
    
    class ToetsenbordHandler extends KeyAdapter {

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
    
    class TimerHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) { 
        	gameEngine.moveObjects();
        	gameEngine.cleanAllObjectsOutOfScreen();
			gameEngine.checkCollisions();
			gameEngine.checkIfAnyEnemyHasToShoot();
			gameEngine.checkIfNewEnemyHasToBeMade();
			repaint();
        }
    }
}