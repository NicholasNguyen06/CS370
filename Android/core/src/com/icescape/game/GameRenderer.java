/*
 * GameRenderer is responsible for rendering all objects in a GameWorld
 * Handles:
 * 		- Grabbing the correct image from AssetLoader for a certain game object
 * 		- Rendering sprites, background, animations
 */

package com.icescape.game;

import com.icescape.entities.Icicle;
import com.icescape.entities.Player;

import com.icescape.helpers.AssetLoader;
import com.icescape.helpers.Constants;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;

public class GameRenderer {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private GameWorld world;
	
	public GameRenderer(GameWorld world) {
		this.world = world;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		
		batch = new SpriteBatch();		
	}
	
	public void render(float runtime) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		
		drawBackground(runtime);
		drawPlayer();
		drawIcicles();
	}
	
	private void drawBackground(float runtime) {
		float offset = runtime * Constants.BACKGROUND_SPEED_MULT % Constants.SCREEN_WIDTH;
		
		batch.begin();
		batch.draw(AssetLoader.background, -offset, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		batch.draw(AssetLoader.background, Constants.SCREEN_WIDTH - offset, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		batch.end();
	}
	
	private void drawPlayer() {
		batch.begin();
		batch.draw(AssetLoader.player, world.getPlayer().getX(), world.getPlayer().getY(), Player.width, Player.height);
		batch.end();
	}
	
	private void drawIcicles() {
		batch.begin();
		Iterator<Icicle> iter = world.getIcicles().iterator();
		while (iter.hasNext()) {
			Icicle icicle = iter.next();
			batch.draw(AssetLoader.icicle, icicle.getX(), icicle.getY(), Icicle.width, Icicle.height);
		}
		batch.end();
	}
}
