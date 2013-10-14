package com.adavieslyons.zombia.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Bullet {
	Image image;
	private Vector2f position;
	private Vector2f velocity;
	private final float speed;
	
	public Bullet(float a_speed, Vector2f origin, float angle, String imageName) throws SlickException {
		this.speed = a_speed;
		image = new Image(imageName);
		
		position = origin.copy();
		velocity = new Vector2f(1, 0);
		
		angle -= 180;
		
		velocity.setTheta(angle);
		image.setRotation(angle - 90);
	}
	
	public void update(GameContainer gc, int delta) {
		position.add(
				velocity.copy().scale(speed)
				);
	}
	
	public void render(GameContainer gc, Graphics graphics) {
		graphics.drawImage(image, position.getX(), position.getY());
	}
}
