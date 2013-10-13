package com.adavieslyons.zombia.gamestate;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.adavieslyons.zombia.gamestate.states.GameState;

public class GameStateManager {
	public GameState gameState;
	
	public GameStateManager() {
		
	}
	
	public void setState(GameState newState) {
		gameState = newState;
	}	
	
	public void update(GameContainer gc, int delta) throws SlickException {
		gameState.update(gc, delta);
	}
	
	public void render(GameContainer gc, Graphics graphics) {
		gameState.render(gc, graphics);
	}
}
