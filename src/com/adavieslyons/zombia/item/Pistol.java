package com.adavieslyons.zombia.item;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.adavieslyons.zombia.entity.Bullet;
import com.adavieslyons.zombia.entity.EntityManager;
import com.adavieslyons.zombia.entity.PistolBullet;

public class Pistol extends Gun {
	public Pistol(EntityManager eManager) throws SlickException {
		super("pistol", eManager, 64, 6, 800f, 300f);
	}

	@Override
	public Bullet getNewBullet(Vector2f origin, float angle) throws SlickException {
		return new PistolBullet(this, origin, angle, eManager);
	}
}
