package com.adavieslyons.zombia.gamestate.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.adavieslyons.zombia.entity.EntityManager;
import com.adavieslyons.zombia.entity.Player;
import com.adavieslyons.zombia.gamestate.GameStateManager;

public class StateGame extends GameState {
	EntityManager eManager;
	Player player;
	
	public StateGame(GameStateManager gsm) throws SlickException {
		super(gsm);
		
		eManager = new EntityManager();
		player = new Player();
		
		eManager.addEntity(player);
	}
	
	public void init(GameContainer gc) {
		player.init(gc);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		eManager.update(gc, delta);
	}
	
	@Override
	public void render(GameContainer gc, Graphics graphics) {
		eManager.render(gc, graphics);
	}
}
