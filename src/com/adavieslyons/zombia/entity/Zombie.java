package com.adavieslyons.zombia.entity;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.adavieslyons.zombia.util.ZombieWave;

public class Zombie extends Entity {
	ZombieWave wave;
	Image head;
	Image arms;
	Player player;
	
	float armAngle = 0.0f;
	float headAngle = 0.0f;
	int moneyEarned = 5;
	Vector2f velocity;
	float speed;
	
	public Zombie(EntityManager eManager, Player player, Vector2f spawnPosition, ZombieWave wave) throws SlickException {
		super(eManager);
		
		head = new Image("resource/img/zombie_head.png");
		arms = new Image("resource/img/zombie_arms.png");
		this.player = player;
		this.renderPosition = spawnPosition;
		this.position = spawnPosition;
		this.wave = wave;
	}
	
	@Override
	public void init(GameContainer gc) {
		//renderPosition = new Vector2f(0, 0);
		velocity = new Vector2f(1, 0);
		speed = 0.75f;
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if (player.getWorldPos().distance(position) > 25) {
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
		} else {
			
		}
		
		coreEntityUpdate(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Graphics graphics) {
		graphics.drawImage(arms, renderPosition.getX(), renderPosition.getY());
		graphics.drawImage(head, renderPosition.getX(), renderPosition.getY());
	}

	@Override
	public void renderUI(GameContainer gc, Graphics graphics) {
		if (new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY()).distance(this.renderPosition.copy().add(new Vector2f(24, 24))) < 16) {
			renderHPBar(gc, graphics);
		}
	}
	
	@Override
	public void die() {
		eManager.removeEntity(this);
		wave.zombieDied(this);
		player.giveMoney(moneyEarned);
	}
}
