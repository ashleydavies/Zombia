package com.adavieslyons.zombia.item;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;

import com.adavieslyons.zombia.entity.Bullet;

public abstract class Gun {
	String name;
	Image gunImage;
	Image muzzleImage;
	Sound fireSound;
	ArrayList<Bullet> bullets;
	Vector2f position;
	float angle;
	
	Boolean muzzleFlash = false;
	
	public Gun(String a_name) throws SlickException {
		gunImage = new Image("resource/img/" + a_name + ".png");
		muzzleImage = new Image("resource/img/" + a_name + "_muzzleflash.png");
		fireSound = new Sound("resource/snd/" + a_name + ".ogg");
		name = a_name;
		bullets = new ArrayList<Bullet>();
	}
	
	public void render(GameContainer gc, Graphics graphics) {		
		graphics.drawImage(gunImage, position.getX(), position.getY());
		
		if (muzzleFlash) {
			graphics.drawImage(muzzleImage, position.getX(), position.getY());
		}
		
		for (Bullet bullet : bullets) {
			bullet.render(gc, graphics);
		}
	}
	
	public void update(GameContainer gc, int delta) throws SlickException {
		if (gc.getInput().isMouseButtonDown(0) && !muzzleFlash) {
			muzzleFlash = true;
			
			bullets.add(getNewBullet(position, angle));
			fireSound.play();
		} else if (!gc.getInput().isMouseButtonDown(0)) {
			muzzleFlash = false;
		}
		
		for (Bullet bullet : bullets) {
			bullet.update(gc, delta);
		}
	}
	
	public void setPosition(Vector2f newPosition) {
		position = newPosition;
	}
	
	public void setAngle(float newAngle) {
		angle = newAngle;
		
		gunImage.setRotation(angle);
		muzzleImage.setRotation(angle);
	}
	
	public abstract Bullet getNewBullet(Vector2f origin, float angle) throws SlickException;
}
