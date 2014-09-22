
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
	
	private static int[] icicleWidths  = {16, 32, 64};
	private static int[] icicleHeights = {32, 32, 64};
	public static Icicle spawnIcicle() {

		// Determine height and width
		int random = MathUtils.random(0, 2);
		int width  = icicleWidths[random];
		int height = icicleHeights[random];
		
		
		// Create a new icicle, positioned somewhere between (0, screen_height) and (screen_width, screen_height)
		int x = MathUtils.random(0, (int)Constants.SCREEN_WIDTH - width);
		int y = (int)Constants.SCREEN_HEIGHT - height;
		
		return new Icicle(x, y, width, height);
	}
}
