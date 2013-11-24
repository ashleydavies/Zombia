package com.adavieslyons.zombia.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {
	protected EntityManager eManager;
	float health = 100.0f;
	float maxHealth = 100.0f;
	float healthRegen = 4.0f; // Per second
	
	Vector2f renderPosition = new Vector2f(0, 0);
	Vector2f position = new Vector2f();
	Vector2f velocity;
	
	public Entity(EntityManager eManager) {
		this.eManager = eManager;
	}
	
	public abstract void update(GameContainer gc, int delta) throws SlickException;
	public abstract void render(GameContainer gc, Graphics graphics);
	public abstract void renderUI(GameContainer gc, Graphics graphics);
	public abstract void init(GameContainer gc);
	public abstract void waveCleanup();
	
	public void coreEntityUpdate(GameContainer gc, int delta) {
		health += healthRegen * delta / 1000;
		if (health > maxHealth) {
			health = maxHealth;
		}
	}
	
	public void renderHPBar(GameContainer gc, Graphics graphics) {
		graphics.setColor(Color.red);
		graphics.fillRect(renderPosition.getX() + 8, renderPosition.getY() - 10, 32, 6);
		graphics.setColor(Color.yellow);
		graphics.fillRect(renderPosition.getX() + 8, renderPosition.getY() - 10, 32 * health / maxHealth, 6);
	}
	
	public void takeDamage(float damage) {
		health -= damage;
		
		if (health < 0) {
			die();
		}
	}
	
	public void die() {
		eManager.removeEntity(this);
	}
}
