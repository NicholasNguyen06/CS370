package com.icescape.game;

import com.icescape.entities.Icicle;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameRenderer {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private GameWorld world;
	
	public GameRenderer(GameWorld world) {
		this.world = world;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		batch = new SpriteBatch();
		//batch.setProjectionMatrix(camera.combined);
		
	}
	
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		
		batch.begin();
		batch.draw(world.getPlayer().image, world.getPlayer().getX(), world.getPlayer().getY(), world.getPlayer().width, world.getPlayer().height);
		batch.end();
		
		batch.begin();
		Iterator<Icicle> iter = world.getIcicleFactory().icicles.iterator();
		while (iter.hasNext()) {
			Icicle icicle = iter.next();
			batch.draw(icicle.image, icicle.getX(), icicle.getY(),icicle.width, icicle.height);
		}
		batch.end();
	}
}
