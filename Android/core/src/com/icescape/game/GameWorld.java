/*
 * GameWorld contains all game objects (icicles, player, penguin, etc) inside a GameScreen.
 * Handles:
 * 		- Updating all game objects
 * 		- Creating new icicles from an IcicleFactory based on elapsed time
 * 		-
 * TODO:
 * 		Only allow a certain number of icicles on screen at a time
 * 		Generate icicles more quickly as the game goes on
 * 		Scale icicle acceleration as the game goes on
 */

package com.icescape.game;

import java.util.Iterator;

import com.icescape.entities.*;
import com.icescape.helpers.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.Intersector;

public class GameWorld {

	private Player player;
	
	private Array<Snowball> snowballs;
	private long lastSnowballTime;
	private long snowballSpawnSpacing = 999999999;

	private Array<Icicle> icicles;
	private long lastIcicleTime;
	private long icicleSpawnSpacing = 500000000;

	private int iciclesDodged = 0;
	
	private boolean gameOver = false;

	public GameWorld() {
		player = new Player(0, Constants.BACKGROUND_LAKE_HEIGHT);
		
		icicles = new Array<Icicle>();
		snowballs = new Array<Snowball>();

		lastIcicleTime = lastSnowballTime = TimeUtils.nanoTime();
	}

	public void update(float delta) {
		
		// Generate a new icicle if necessary
		if (TimeUtils.nanoTime() - lastIcicleTime > icicleSpawnSpacing) {
			icicles.add(IcicleGenerator.spawnIcicle());
			lastIcicleTime = TimeUtils.nanoTime();
		}

		// Throw a new snowball if necessary
		if (TimeUtils.nanoTime() - lastSnowballTime > snowballSpawnSpacing) {
			snowballs.add(SnowballGenerator.spawnSnowball());
			lastSnowballTime = TimeUtils.nanoTime();
		}
		
		// If the screen was tapped, update the player
		if (Gdx.input.justTouched()) {
			player.moveRight();
		}

		player.update(delta);
		if (player.isAlive == false) {
			Gdx.app.exit();
		}
		updateIcicles(delta);
		updateSnowballs(delta);
	}
	
	public boolean gameIsOver() {
		return gameOver;
	}

	public Player getPlayer() {
		return player;
	}

	public Array<Icicle> getIcicles() {
		return icicles;
	}
	
	public Array<Snowball> getSnowballs() {
		return snowballs;
	}
	
	public int getIciclesDodged() {
		return iciclesDodged;
	}
	
	private void updateIcicles(float delta) {
		Iterator<Icicle> iter = icicles.iterator();
		while (iter.hasNext()) {
			Icicle icicle = iter.next();
			icicle.update(delta);

			// If icicle has touched the top of the lake
			if (icicle.getY() <= Constants.BACKGROUND_LAKE_HEIGHT) {
				iter.remove();
				++iciclesDodged;
			}

			// Icicle has collided with player
			else if (icicle.getRect().overlaps(player.getRect())) {
				iter.remove();
				player.isDying = true;
				gameOver = true;
			}
		}
	}
	
	private void updateSnowballs(float delta) {
		Iterator<Snowball> iter = snowballs.iterator();
		while (iter.hasNext()) {
			Snowball snowball = iter.next();
			snowball.update(delta);
			
			// If snowball has touched the top of the lake
			if (snowball.getY() <= Constants.BACKGROUND_LAKE_HEIGHT) {
				iter.remove();
			}
			
			else if ( Intersector.overlaps(snowball.getBoundingCircle(), player.getRect()) ) {
				iter.remove();
			}
		}
	}
}
