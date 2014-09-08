
/*
 * IcicleFactory creates new Icicle objects.
 * Handles:
 * 		- Creating all new Icicle objects
 * 		- Random placement of Icicle objects
 * 
 */


package com.icescape.game;
import com.icescape.entities.Icicle;
import com.icescape.helpers.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class IcicleGenerator {
	
	public static Icicle spawnIcicle() {

		// Create a new icicle, positioned somewhere between (0, screen_height) and (screen_width, screen_height)
		int x = MathUtils.random(0, (int)Constants.SCREEN_WIDTH - Icicle.width);
		int y = (int)Constants.SCREEN_HEIGHT - Icicle.height;
		return new Icicle(x, y);
	}
}
