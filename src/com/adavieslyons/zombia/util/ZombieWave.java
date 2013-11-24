package com.adavieslyons.zombia.util;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.adavieslyons.zombia.Game;
import com.adavieslyons.zombia.entity.EntityManager;
import com.adavieslyons.zombia.entity.Player;
import com.adavieslyons.zombia.entity.Zombie;
import com.adavieslyons.zombia.gamestate.states.StateGame;

public class ZombieWave {
	private final int waveNumber;
	private final EntityManager eManager;
	private final Player player;
	private final StateGame parent;
	private ArrayList<Zombie> zombies;
	
	public ZombieWave(int waveNumber, EntityManager eManager, Player player, StateGame parent) {
		this.waveNumber = waveNumber;
		this.eManager = eManager;
		this.player = player;
		this.parent = parent;
	}
	
	public void initialise(GameContainer gc) throws SlickException {
		zombies = new ArrayList<Zombie>();
		
		// Hard code some things for the first few waves
		switch (waveNumber) {
			case 0:
				for (int i = 0; i < 25; i++) {
					generateZombie(gc, Game.rnd.nextInt(1000) + 500);
				}
				break;
			case 1:
				for (int i = 0; i < 50; i++) {
					generateZombie(gc, Game.rnd.nextInt(1500) + 500);
				}
		}
	}
	
	public void update(GameContainer gc, int delta) {
		// Don't need to worry about anything like updating entities... All handled by StateGame -> EntityManager
		if (zombies.size() == 0) {
			parent.waveCompleted();
		}
	}
	
	public void zombieDied(Zombie zombie) {
		zombies.remove(zombie);
	}
	
	public void generateZombie(GameContainer gc, int distance) throws SlickException {
		float degrees = Game.rnd.nextInt(360);
		
		float x = (float) (player.getWorldPos().getX() + distance * Math.sin(degrees));
		float y = (float) (player.getWorldPos().getY() + distance * Math.cos(degrees));
		
		x += gc.getWidth() / 2 - 24;
		y += gc.getHeight() / 2 - 24;
		
		Zombie z = new Zombie(eManager, player, new Vector2f(x, y), this);
		z.init(gc);
		
		zombies.add(z);
		eManager.addEntity(z);
	}
}
