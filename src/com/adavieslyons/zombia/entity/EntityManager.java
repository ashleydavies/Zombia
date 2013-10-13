package com.adavieslyons.zombia.entity;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class EntityManager {
	ArrayList<Entity> entities;
	
	public EntityManager() {
		entities = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}
	
	
	public void update(GameContainer gc, int delta) throws SlickException {
		for (Entity e : entities) {
			e.update(gc, delta);
		}
	}
	
	public void render(GameContainer gc, Graphics graphics) {
		for (Entity e : entities) {
			e.render(gc, graphics);
		}
	}
}
