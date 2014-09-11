package com.icescape.game;

import com.icescape.entities.Icicle;
import com.icescape.entities.Snowball;
import com.icescape.helpers.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class SnowballGenerator {
	
	public static Snowball spawnSnowball() {
		// Create a new snowball around the bottom left corner of the screen
		int x = (int)Constants.SCREEN_WIDTH - Snowball.radius * 2;
		int y = (int)MathUtils.random(0, Constants.SCREEN_HEIGHT/2);
		
		return new Snowball(x, y);
	}
}
