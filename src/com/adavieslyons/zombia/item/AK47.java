package com.adavieslyons.zombia.item;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.adavieslyons.zombia.entity.AK47Bullet;
import com.adavieslyons.zombia.entity.Bullet;
import com.adavieslyons.zombia.entity.EntityManager;
import com.adavieslyons.zombia.entity.MagnumBullet;
import com.adavieslyons.zombia.entity.PistolBullet;

public class AK47 extends Gun {

	public AK47(EntityManager eManager) throws SlickException {
			super("magnum", "AK47", eManager, 256, 32, 2000f, 100f, 1250, 250);	
	}

	@Override
	public Bullet getNewBullet(Vector2f origin, float angle) throws SlickException {
		return new AK47Bullet(this, origin, angle, eManager);
	}
}
