package com.adavieslyons.zombia;

import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.adavieslyons.zombia.gamestate.GameStateManager;
import com.adavieslyons.zombia.gamestate.states.StateGame;

public class Game extends BasicGame {
	public static Random rnd = new Random();
	
	Image cursor;
	GameStateManager gsm;
	
	// Entry method
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game());
		app.setDisplayMode(820, 820, false);
		app.setTargetFrameRate(180);
		app.start();
	}
	
	public Game() {
		super("Zombia");
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		cursor = new Image("resource/img/cursor.png");
		gc.setMouseCursor(cursor, 6, 6);
		
		gsm = new GameStateManager();
		
		StateGame sg = new StateGame(gsm);
		sg.init(gc);
		
		gsm.setState(sg);
	}

	@Override
	public void render(GameContainer gc, Graphics graphics) throws SlickException {
		graphics.setBackground(Color.gray);
		gsm.render(gc, graphics);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		gsm.update(gc, delta);
	}
}
