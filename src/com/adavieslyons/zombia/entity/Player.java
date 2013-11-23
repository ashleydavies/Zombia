package com.adavieslyons.zombia.entity;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.adavieslyons.zombia.item.Gun;
import com.adavieslyons.zombia.item.Pistol;

public class Player extends Entity {
	Image head;
	Image arms;
	ArrayList<Gun> guns;
	Gun currentGun;
	
	private int money;
	float armAngle = 0.0f;
	float headAngle = 0.0f;
	
	public Player(EntityManager eManager) throws SlickException {
		super(eManager);
		
		head = new Image("resource/img/man_head.png");
		arms = new Image("resource/img/man_arms.png");
		
		currentGun = new Pistol(eManager);
	}
	
	@Override
	public void init(GameContainer gc) {
		int centerX = gc.getWidth() / 2 - head.getWidth() / 2;
		int centerY = gc.getHeight() / 2 - head.getHeight() / 2;
		
		renderPosition = new Vector2f(centerX, centerY);
		currentGun.setPosition(renderPosition);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		float xDistance = gc.getInput().getMouseX() - renderPosition.getX() - 24;
		float yDistance = gc.getInput().getMouseY() - renderPosition.getY() - 24;
		double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance)) + 90;
		
		armAngle = (float) angleToTurn;
		headAngle = armAngle;
		
		
		arms.setRotation(armAngle);
		head.setRotation(headAngle);
		
		currentGun.setAngle(armAngle);
		
		currentGun.update(gc, delta);
		
		coreEntityUpdate(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Graphics graphics) {
		graphics.drawImage(arms, renderPosition.getX(), renderPosition.getY());
		graphics.drawImage(head, renderPosition.getX(), renderPosition.getY());
		
		currentGun.render(gc, graphics);
	}
	
	@Override
	public void renderUI(GameContainer gc, Graphics graphics) {
		// TODO: HP bar at bottom! Override method!
		//renderHPBar(gc, graphics);
		currentGun.renderUI(gc, graphics);
		
		// Render gold
		graphics.setColor(Color.black);
		graphics.setAntiAlias(true);
		graphics.fillRect(gc.getWidth() - 96, 0, 96, 32);
		graphics.fillOval(gc.getWidth() - 128, -32, 64, 64);
		graphics.setColor(Color.yellow);
		graphics.drawString("$" + money, gc.getWidth() - 96, 6);
	}
	
	public Vector2f getWorldPos() {
		return renderPosition; // TODO: Patch
	}

	public void giveMoney(int moneyEarned) {
		money += moneyEarned;
	}

	int getMoney() {
		return money;
	}
}
