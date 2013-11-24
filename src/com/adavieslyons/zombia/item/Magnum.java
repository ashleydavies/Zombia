package com.adavieslyons.zombia.item;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.adavieslyons.zombia.entity.Bullet;
import com.adavieslyons.zombia.entity.EntityManager;
import com.adavieslyons.zombia.entity.MagnumBullet;
import com.adavieslyons.zombia.entity.PistolBullet;

public class Magnum extends Gun {

	public Magnum(EntityManager eManager) throws SlickException {
			super("magnum", ".44 Magnum", eManager, 256, 8, 1250f, 530f, 150, 60);	
	}

	@Override
	public Bullet getNewBullet(Vector2f origin, float angle) throws SlickException {
		return new MagnumBullet(this, origin, angle, eManager);
	}
}
