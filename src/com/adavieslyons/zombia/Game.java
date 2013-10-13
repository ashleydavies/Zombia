package com.adavieslyons.zombia;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {
	
	// Entry method
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game());
		app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), true);
		//app.setTargetFrameRate(60);
		app.start();
	}
	
	public Game() {
		super("Zombia");
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, Graphics graphics) throws SlickException {
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
	}
	
}
