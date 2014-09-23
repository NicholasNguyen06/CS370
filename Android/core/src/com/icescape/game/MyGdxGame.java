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
		Gdx.app.log("Game", "Creating InputManager");
		inputManager = new InputManager(this);
		
		Gdx.app.log("Game", "Creating ScreenManager");
		screenManager = new ScreenManager();
		
		
		Gdx.app.log("Game", "Loading assets");
		AssetLoader.load();
		
		Gdx.app.log("Game", "Loading sounds");
		SoundManager.load();
		
		Gdx.app.log("Game", "Creating GameScreen");
		screenManager.screens.push(new GameScreen(this));
	
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