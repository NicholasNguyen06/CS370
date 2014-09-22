/*
 * Entry point for the game.
 * Responsible for:
 * 		- Screen creation
 * 		- Screen switching
 * 		- Calling AssetLoader.load()
 */


package com.icescape.game;

import com.icescape.helpers.AssetLoader;
import com.icescape.helpers.InputManager;
import com.icescape.helpers.ScreenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MyGdxGame extends Game {

	private InputManager  inputManager;
	private ScreenManager screenManager;
	
	@Override
	public void create() {
		inputManager = new InputManager(this);
		screenManager = new ScreenManager();
		
		screenManager.screens.push(new GameScreen());
		AssetLoader.load();
		updateScreen();
	}
	
	public void updateScreen() {
		setScreen(screenManager.screens.peek());
	}
	
	@Override
	public void render() {
		super.render();
	}	
}