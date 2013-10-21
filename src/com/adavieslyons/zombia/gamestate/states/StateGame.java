package com.adavieslyons.zombia.gamestate.states;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.adavieslyons.zombia.entity.EntityManager;
import com.adavieslyons.zombia.entity.Player;
import com.adavieslyons.zombia.entity.Zombie;
import com.adavieslyons.zombia.gamestate.GameStateManager;

public class StateGame extends GameState {
	EntityManager eManager;
	Player player;
	
	public StateGame(GameStateManager gsm) throws SlickException {
		super(gsm);
		
		eManager = new EntityManager();
		player = new Player();
	}
	
	public void init(GameContainer gc) throws SlickException {
		eManager.addEntity(player);
		Random rnd = new Random();
		
		//Spawn in some zombies to fight
		for (int i = 0; i < 50; i++) {
			Vector2f spawnPosition = new Vector2f();
			do {
				spawnPosition.set(rnd.nextInt(gc.getWidth()), rnd.nextInt(gc.getHeight()));
			} while ((spawnPosition.getX() > gc.getWidth() / 2 - 60 && spawnPosition.getX() < gc.getWidth() / 2 + 60) 
					|| (spawnPosition.getY() > gc.getHeight() / 2 - 60 && spawnPosition.getY() < gc.getHeight() / 2 + 6));
			
			eManager.addEntity(new Zombie(player, spawnPosition));
		}
		
		eManager.initEntities(gc);
		//player.init(gc);
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
