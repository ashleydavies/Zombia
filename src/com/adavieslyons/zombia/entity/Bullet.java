package com.adavieslyons.zombia.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.adavieslyons.zombia.item.Gun;

public class Bullet {
	Image image;
	private Vector2f position;
	private Vector2f velocity;
	private final float speed;
	private Gun parent;
	private EntityManager eManager;
	private float time;
	
	public Bullet(Gun parent, float a_speed, Vector2f origin, float angle, String imageName, EntityManager eManager) throws SlickException {
		this.parent = parent;
		this.speed = a_speed;
		image = new Image(imageName);
		
		position = origin.copy();
		velocity = new Vector2f(1, 0);
		
		angle -= 180;
		
		velocity.setTheta(angle);
		image.setRotation(angle - 90);
		
		this.eManager = eManager;
	}
	
	public void update(GameContainer gc, int delta) {
		time += delta;
		
		if (time / 1000 > 30) {
			parent.removeBullet(this);
		}
		
		
		position.add(
				velocity.copy().scale(speed)
				);
		
		for (Entity e : eManager.getEntities()) {
			if (e.position.copy().add(new Vector2f(16, 16)).distance(position) < 15) {
				// No concurrent modification exceptions since this is pushed into a queue dealt with after updating all entities.
				if (!(e instanceof Player)) {
					eManager.removeEntity(e);
					parent.removeBullet(this);
					break;
				}
			}
		}
	}
	
	public void render(GameContainer gc, Graphics graphics) {
		graphics.drawImage(image, position.getX(), position.getY());
	}
}
