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

public class GameWorld {

	private Player player;
	private IcicleFactory icicleFactory;
	private Array<Icicle> icicles;
	private long lastIcicleTime;

	private long icicle_spawn_spacing = 500000000;

	public GameWorld() {
		player = new Player(0, Constants.BACKGROUND_LAKE_HEIGHT);
		icicleFactory = new IcicleFactory();
		icicles = new Array<Icicle>();

		lastIcicleTime = TimeUtils.nanoTime();
	}

	public void update(float delta) {

		// Generate a new icicle if necessary
		if (TimeUtils.nanoTime() - lastIcicleTime > icicle_spawn_spacing) {
			icicles.add(icicleFactory.spawnIcicle());
			lastIcicleTime = TimeUtils.nanoTime();
		}

		// If the screen was tapped, update the player
		if (Gdx.input.justTouched()) {
			player.moveRight();
		}

		player.update(delta);
		updateIcicles(delta);
	}

	public Player getPlayer() {
		return player;
	}

	public Array<Icicle> getIcicles() {
		return icicles;
	}

	private void updateIcicles(float delta) {
		Iterator<Icicle> iter = icicles.iterator();
		while (iter.hasNext()) {
			Icicle icicle = iter.next();
			icicle.update(delta);

			// If icicle has touched the top of the lake
			if (icicle.getY() <= Constants.BACKGROUND_LAKE_HEIGHT) {
				iter.remove();
			}

			// Icicle has collided with player
			else if (icicle.getRect().overlaps(player.getRect())) {
				iter.remove();
			}
		}
	}
}
