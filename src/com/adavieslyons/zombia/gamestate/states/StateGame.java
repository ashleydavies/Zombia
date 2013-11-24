package com.adavieslyons.zombia.gamestate.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.adavieslyons.zombia.entity.EntityManager;
import com.adavieslyons.zombia.entity.Player;
import com.adavieslyons.zombia.gamestate.GameStateManager;
import com.adavieslyons.zombia.util.Shop;
import com.adavieslyons.zombia.util.ZombieWave;

public class StateGame extends GameState {
	EntityManager eManager;
	boolean inWave = true;
	ZombieWave wave;
	int waveNumber = 0;
	float zombieWaveIntermissionTimer;
	Player player;
	
	Shop shop;
	
	public StateGame(GameStateManager gsm) throws SlickException {
		super(gsm);
		
		eManager = new EntityManager();
		player = new Player(eManager);
		shop = new Shop(eManager, player);
	}
	
	public void init(GameContainer gc) throws SlickException {
		eManager.addEntity(player);
		
		wave = new ZombieWave(0, eManager, player, this);
		wave.initialise(gc);
		shop.buy(0);
		
		player.init(gc);
		//eManager.initEntities(gc);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if (inWave) {
			wave.update(gc, delta);
			eManager.update(gc, delta);
		}
		else {
			// Zombie wave intermission logic
			shop.update(gc, delta);
			
			zombieWaveIntermissionTimer -= delta;
			
			if (zombieWaveIntermissionTimer < 0) {
				zombieWaveIntermissionTimer = 0.0f;
				inWave = true;
				wave = new ZombieWave(++waveNumber, eManager, player, this);
				wave.initialise(gc);
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics graphics) {
		eManager.render(gc, graphics);
		
		if (inWave) {
		}
		else {
			// Intermission logic			
			graphics.setColor(Color.yellow);
			drawCenteredText(gc, graphics, "Wave Complete.", -10);
			drawCenteredText(gc, graphics, String.format("%.1f", zombieWaveIntermissionTimer / 1000) + " seconds remaining", 10);
			
			shop.render(gc, graphics);
		}
	}
	
	public void drawCenteredText(GameContainer gc, Graphics graphics, String text, float yOffset) {
		graphics.drawString(text, gc.getWidth() / 2 - graphics.getFont().getWidth(text) / 2, (gc.getHeight() / 2 - graphics.getFont().getHeight(text) / 2) + yOffset);
	}
	
	public void waveCompleted() {
		inWave = false;
		zombieWaveIntermissionTimer = 30000.0f;
		player.giveMoney((waveNumber + 1) * 25);
		
		eManager.callWaveCleanup();
	}
}
