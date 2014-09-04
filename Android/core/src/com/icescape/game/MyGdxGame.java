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

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new GameScreen());
	}
	
}