
/*
 * IcicleFactory creates new Icicle objects.
 * Handles:
 * 		- Creating all new Icicle objects
 * 		- Random placement of Icicle objects
 * 
 */


package com.icescape.game;
import com.icescape.entities.Icicle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class IcicleFactory {
	
	public IcicleFactory() {
		// nothing needed, for now
	}
	
	public Icicle spawnIcicle() {

		// Create a new icicle, positioned somewhere between (0, screen_height) and (screen_width, screen_height)
		return new Icicle(MathUtils.random(0, Gdx.graphics.getWidth() - Icicle.width), Gdx.graphics.getHeight() - Icicle.height);
	}
}
