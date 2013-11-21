package com.adavieslyons.zombia.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Zombie extends Entity {
	Image head;
	Image arms;
	Player player;
	
	Vector2f renderPosition = new Vector2f(0, 0);
	float armAngle = 0.0f;
	float headAngle = 0.0f;
	Vector2f velocity;
	float speed;
	
	public Zombie(Player player, Vector2f spawnPosition) throws SlickException {
		head = new Image("resource/img/zombie_head.png");
		arms = new Image("resource/img/zombie_arms.png");
		this.player = player;
		this.renderPosition = spawnPosition;
		this.position = spawnPosition;
	}
	
	@Override
	public void init(GameContainer gc) {
		//renderPosition = new Vector2f(0, 0);
		velocity = new Vector2f(1, 0);
		speed = 0.18f;
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		float xDistance = player.getWorldPos().getX() - renderPosition.getX();
		float yDistance = player.getWorldPos().getY() - renderPosition.getY();
		double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance)) + 90;
		armAngle = (float) angleToTurn;
		headAngle = armAngle;
		
		velocity.setTheta(angleToTurn - 90);
		
		position.add(velocity.copy().scale(speed));
		renderPosition.add(velocity.copy().scale(speed));
		
		arms.setRotation(armAngle);
		head.setRotation(headAngle);
	}

	@Override
	public void render(GameContainer gc, Graphics graphics) {
		graphics.drawImage(arms, renderPosition.getX(), renderPosition.getY());
		graphics.drawImage(head, renderPosition.getX(), renderPosition.getY());
	}
}
