/*
 * GameScreen implements libgdx's Screen interface. It contains everything necessary to play the game,
 * a GameRenderer and a GameWorld.
 */

package com.icescape.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;


import com.icescape.entities.*;

public class GameScreen implements Screen {

	private GameRenderer renderer;
	private GameWorld world;
	private float runtime;
	
	public GameScreen() {
		world = new GameWorld();
		renderer = new GameRenderer(world);
		
		runtime = 0;
	}
	
	@Override
	public void render(float delta) {
		runtime += delta;
		
		world.update(delta);
		renderer.render(runtime);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
