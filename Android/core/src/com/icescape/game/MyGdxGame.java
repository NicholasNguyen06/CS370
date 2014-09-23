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
import com.icescape.helpers.SoundManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MyGdxGame extends Game {

	private InputManager  inputManager;
	private ScreenManager screenManager;
	
	@Override
	public void create() {
		inputManager = new InputManager(this);
		screenManager = new ScreenManager();
		
		screenManager.screens.push(new GameScreen(this));
		AssetLoader.load();
		SoundManager.load();
		updateScreen();
	}
	
	public void updateScreen() {
		setScreen(screenManager.screens.peek());
	}
	
	public void playGame() {
		
	}
	
	public void gameOver() {
		screenManager.screens.pop();
		// screenManager.screens.push(new GameOverScreen());
		// updateScreen();
	}
	
	@Override
	public void render() {
		super.render();
	}	
}