package com.adavieslyons.zombia.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {
	Vector2f position;
	Vector2f velocity;
	
	public abstract void update(GameContainer gc, int delta) throws SlickException;
	public abstract void render(GameContainer gc, Graphics graphics);
}
