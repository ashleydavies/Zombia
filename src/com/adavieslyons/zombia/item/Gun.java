package com.adavieslyons.zombia.item;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;

import com.adavieslyons.zombia.entity.Bullet;
import com.adavieslyons.zombia.entity.EntityManager;

public abstract class Gun {
	String name;
	Image gunImage;
	Image muzzleImage;
	Sound fireSound;
	ArrayList<Bullet> bullets;
	ArrayList<Bullet> bulletsToBeDeleted;
	Vector2f position;
	float angle;
	EntityManager eManager;
	
	int ammo;
	int ammoClip;
	final int ammoClipMax;
	
	boolean firing = false;	
	boolean muzzleFlash = false;
	boolean recoiling = false;
	boolean reloading = false;
	float reloadTime;
	float recoilTime;
	float reloadTimer;
	float recoilTimer;
	
	public Gun(String a_name, EntityManager eManager, int ammo, int ammoClipMax, float reloadTime, float recoilTime) throws SlickException {
		gunImage = new Image("resource/img/" + a_name + ".png");
		muzzleImage = new Image("resource/img/" + a_name + "_muzzleflash.png");
		fireSound = new Sound("resource/snd/" + a_name + ".ogg");
		name = a_name;
		bullets = new ArrayList<Bullet>();
		bulletsToBeDeleted = new ArrayList<Bullet>();
		this.eManager = eManager;
		
		this.ammo = ammo;
		this.ammoClipMax = ammoClipMax;
		this.ammoClip = ammoClipMax;
		this.reloadTime = reloadTime;
		this.recoilTime = recoilTime;
	}
	
	public void render(GameContainer gc, Graphics graphics) {		
		graphics.drawImage(gunImage, position.getX(), position.getY());
		
		if (muzzleFlash) {
			graphics.drawImage(muzzleImage, position.getX(), position.getY());
		}
		
		for (Bullet bullet : bullets) {
			bullet.render(gc, graphics);
		}
		
		muzzleFlash = false;
	}
	
	public void update(GameContainer gc, int delta) throws SlickException {
		bulletsToBeDeleted.clear();
		
		if (recoiling)	{
			recoilTimer += delta;
			
			if (recoilTimer > recoilTime) {
				recoiling = false;
				recoilTimer = 0.0f;
			}
		}
		
		if (reloading) {
			reloadTimer += delta;
			
			if (reloadTimer > reloadTime) {
				reloading = false;
				reloadTimer = 0.0f;
			}
		}
		
		if (!recoiling && !reloading && firing) {
			firing = false;
		}
		
		if (gc.getInput().isMouseButtonDown(0) && !firing && !recoiling && !reloading && ammoClip > 0) {
			firing = true;
			
			ammoClip--;
			if (ammoClip <= 0) {
				ammo -= ammoClipMax;
				ammoClip += ammoClipMax;
				
				if (ammo < 0) {
					ammoClip -= Math.abs(ammo);
					ammo = 0;
				}
				
				reloading = true;
			}
			
			recoiling = true;
			
			fire();
		}
		
		for (Bullet bullet : bullets) {
			bullet.update(gc, delta);
		}
		
		for (Bullet bullet : bulletsToBeDeleted) {
			bullets.remove(bullet);
		}
	}
	
	public void fire() throws SlickException {
		muzzleFlash = true;
		Vector2f bulletPosition = position.copy();
		
		bulletPosition.x += 24;
		bulletPosition.y += 24;
		
		angle -= 270;
		bulletPosition.x = (float) (bulletPosition.x - (20 * Math.cos(Math.toRadians(angle)) + 8 * Math.sin(Math.toRadians(angle))));
		bulletPosition.y = (float) (bulletPosition.y - (20 * Math.sin(Math.toRadians(angle)) - 8 * Math.cos(Math.toRadians(angle))));
		
		bullets.add(getNewBullet(bulletPosition, angle));
		fireSound.play();
	}
	
	public void setPosition(Vector2f newPosition) {
		position = newPosition;
	}
	
	public void setAngle(float newAngle) {
		angle = newAngle;
		
		gunImage.setRotation(angle);
		muzzleImage.setRotation(angle);
	}
	
	public void removeBullet(Bullet bullet) {
		bulletsToBeDeleted.add(bullet);
	}
	
	public abstract Bullet getNewBullet(Vector2f origin, float angle) throws SlickException;
}
