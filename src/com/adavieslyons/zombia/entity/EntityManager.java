package com.adavieslyons.zombia.entity;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class EntityManager {
	ArrayList<Entity> entities;
	ArrayList<Entity> entityRemovalQueue;
	
	public EntityManager() {
		entities = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		entityRemovalQueue.add(entity);
	}
	
	
	public void update(GameContainer gc, int delta) throws SlickException {
		entityRemovalQueue = new ArrayList<Entity>();
		
		for (Entity e : entities) {
			e.update(gc, delta);
		}
		
		for (Entity e : entityRemovalQueue) {
			entities.remove(e);
		}
	}
	
	public void render(GameContainer gc, Graphics graphics) {
		for (Entity e : entities) {
			e.render(gc, graphics);
		}
		
		for (Entity e : entities) {
			e.renderUI(gc, graphics);
		}
	}

	public void initEntities(GameContainer gc) {
		System.out.println("Initialising entities");
		for (Entity e : entities) {
			e.init(gc);
		}
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
}
