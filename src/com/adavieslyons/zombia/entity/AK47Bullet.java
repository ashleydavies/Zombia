package com.adavieslyons.zombia.entity;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.adavieslyons.zombia.item.Gun;

public class AK47Bullet extends Bullet {

	public AK47Bullet(Gun parent, Vector2f origin, float angle, EntityManager eManager) throws SlickException {
		super(parent, 1250, origin, angle, "resource/img/magnum_bullet.png", eManager, 45);
	}
	
}
