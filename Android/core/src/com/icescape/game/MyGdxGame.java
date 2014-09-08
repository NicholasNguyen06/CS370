/*
 * Entry point for the game.
 * Responsible for:
 * 		- Screen creation
 * 		- Screen switching
 * 		- Calling AssetLoader.load()
 */


package com.icescape.game;

import com.icescape.helpers.AssetLoader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MyGdxGame extends Game {

	private GameScreen gameScreen;
	
	@Override
	public void create() {
		gameScreen = new GameScreen();
		AssetLoader.load();
		setScreen(gameScreen);
		
	}
	
	@Override
	public void render() {
		if (gameScreen.gameIsOver() == false) {
			super.render();
		} else {
			pause();
		}
	}	
}