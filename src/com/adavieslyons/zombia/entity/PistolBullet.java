package com.adavieslyons.zombia.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class PistolBullet extends Bullet {

	public PistolBullet(Vector2f origin, float angle) throws SlickException {
		super(15, origin, angle, "resource/img/pistol_bullet.png");
	}
	
}
