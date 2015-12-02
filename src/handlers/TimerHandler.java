package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import engine.*;
import GUI.*;



public class TimerHandler {
	private Timer timer;
	private GameEngine gameEngine;
	
	public TimerHandler(GameEngine gameEngine) {
		timer = new Timer(10, taskPerformer);
		timer.start();
		this.gameEngine = gameEngine;
	}
	
	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			gameEngine.moveObjects();
			gameEngine.checkCollisions();
			gameEngine.checkIfAnyEnemyHasToShoot();
			gameEngine.checkIfNewEnemyHasToBeMade();
		}
	};
}
