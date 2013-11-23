package com.adavieslyons.zombia.util;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.adavieslyons.zombia.entity.EntityManager;
import com.adavieslyons.zombia.entity.Player;
import com.adavieslyons.zombia.item.Gun;
import com.adavieslyons.zombia.item.Pistol;

public class Shop {
	public ArrayList<Gun> guns;
	private Image shopBackground;
	private Image shopHoverBackground;
	private Player player;
	
	public Shop(EntityManager eManager, Player player) throws SlickException {
		guns = new ArrayList<Gun>();
		
		guns.add(new Pistol(eManager));
		
		shopBackground = new Image("resource/img/shop.png");
		shopHoverBackground = new Image("resource/img/shop_hover.png");
		
		this.player = player;
	}
	
	public void update(GameContainer gc, int delta) {
		
	}
	
	public void render(GameContainer gc, Graphics graphics) {
		graphics.drawImage(shopBackground, getLocalX(gc), getLocalY(gc));
		
		for (Gun gun : guns) {
			int tX = getLocalX(gc) + 6;
			int tY = getLocalY(gc) + 5;
			int bX = tX + shopHoverBackground.getWidth();
			int bY = tY + shopHoverBackground.getHeight();
			
			if (gc.getInput().getMouseX() > tX && gc.getInput().getMouseX() < bX && gc.getInput().getMouseY() > tY && gc.getInput().getMouseY() < bY) {
				graphics.drawImage(shopHoverBackground, tX, tY);
			}
			
			
			graphics.drawImage(gun.getThumbnail(), tX, tY, tX + 82 + 6,  tY + 62, 0, 0, gun.getThumbnail().getWidth(), gun.getThumbnail().getHeight(), Color.white);
			
			graphics.setColor(Color.yellow);
			graphics.drawString(gun.getName(), getLocalX(gc) + 82 + 5 + 6, getLocalY(gc) + 5 + 5);
			graphics.drawString("$" + gun.getPrice(), getLocalX(gc) + 82 + 5 + 6, getLocalY(gc) + 18 + 5 + 5);
			
			
		}
	}
	
	public int getLocalX(GameContainer gc) {
		return gc.getWidth() / 2 - shopBackground.getWidth() / 2;
	}
	
	public int getLocalY(GameContainer gc) {
		return gc.getHeight() / 2 - shopBackground.getHeight() / 2;
	}
}
