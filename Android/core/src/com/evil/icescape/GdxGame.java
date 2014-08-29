package com.evil.icescape;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.MathUtils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GdxGame extends ApplicationAdapter {
	private Pixmap iciclePixMap;
	private Pixmap playerPixMap;
	private Texture icicleTex;
	private Texture playerTex;
	
	private Rectangle player;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private int screenWidth, screenHeight;
	
	private Array<Rectangle> icicles;
	private long lastDropTime;
	
	private BitmapFont font;
	private boolean gameOver;
	
	private void spawnIcicle() {
		Rectangle icicle = new Rectangle();
		icicle.x = MathUtils.random(0, screenWidth - 16);
		icicle.y = screenHeight;
		icicle.width = 16;
		icicle.height = 16;
		icicles.add(icicle);
		lastDropTime = TimeUtils.nanoTime();
	}
	
	@Override
	public void create () {
		gameOver = false;
		
		font = new BitmapFont();
		font.setColor(Color.RED);
		font.setScale(5);
		
		screenWidth  = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		iciclePixMap   = new Pixmap(16, 16, Pixmap.Format.RGBA8888);
		playerPixMap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
		
		iciclePixMap.setColor(Color.BLUE);
		iciclePixMap.fill();
		
		playerPixMap.setColor(Color.GRAY);
		playerPixMap.fill();

		icicleTex = new Texture(iciclePixMap);
		playerTex = new Texture(playerPixMap);
		
		iciclePixMap.dispose();
		playerPixMap.dispose();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, screenWidth, screenHeight);
		
		batch = new SpriteBatch();
		
		player = new Rectangle();
		player.x = screenWidth / 2 - 64 / 2;
		player.y = 20;
		player.width = 64;
		player.height = 64;
		
		icicles = new Array<Rectangle>();
		spawnIcicle();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			player.x = touchPos.x - 64 / 2;
		}
		
		if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnIcicle();
		
		Iterator<Rectangle> iter = icicles.iterator();
		while (iter.hasNext()) {
			Rectangle icicle = iter.next();
			icicle.y -= 200 * Gdx.graphics.getDeltaTime(); // icicles fall at a rate of 200 px/s
			if (icicle.y + 16 < 0) iter.remove();		   // remove icicles below screen
			if (icicle.overlaps(player)) {
				iter.remove();
				gameOver = true;
			}
		}
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if (gameOver == false){
			batch.draw(playerTex, player.x, player.y);
			for (Rectangle icicle : icicles) {
				batch.draw(icicleTex, icicle.x, icicle.y);
			}
		} 
		else {
			font.draw(batch, "Game Over!", screenHeight / 2, screenWidth / 2);
		}
			
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		playerTex.dispose();
		icicleTex.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}
}
