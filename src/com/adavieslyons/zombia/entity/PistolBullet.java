package com.adavieslyons.zombia.entity;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.adavieslyons.zombia.item.Gun;

public class PistolBullet extends Bullet {

	public PistolBullet(Gun parent, Vector2f origin, float angle, EntityManager eManager) throws SlickException {
		super(parent, 15, origin, angle, "resource/img/pistol_bullet.png", eManager);
	}
	
}
