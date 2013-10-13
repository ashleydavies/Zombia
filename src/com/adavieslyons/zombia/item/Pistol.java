package com.adavieslyons.zombia.item;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.adavieslyons.zombia.entity.Bullet;
import com.adavieslyons.zombia.entity.PistolBullet;

public class Pistol extends Gun {
	public Pistol() throws SlickException {
		super("pistol");
	}

	@Override
	public Bullet getNewBullet(Vector2f origin, float angle) throws SlickException {
		return new PistolBullet(origin, angle);
	}
}
