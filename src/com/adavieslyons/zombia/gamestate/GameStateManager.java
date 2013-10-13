package com.adavieslyons.zombia.gamestate;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.adavieslyons.zombia.gamestate.states.GameState;

public class GameStateManager {
	public GameState gameState;
	
	public GameStateManager(GameState beginState) {
		gameState = beginState;
	}
	
	
	public void update(GameContainer gc, int delta) {
		gameState.update(gc, delta);
	}
	
	public void render(GameContainer gc, Graphics graphics) {
		gameState.render(gc, graphics);
	}
}
