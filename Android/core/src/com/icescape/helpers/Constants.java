package com.icescape.helpers;

import com.badlogic.gdx.Gdx;

public class Constants {
	
	// Screen height * (lake height in background * background height)
	public static final float BACKGROUND_LAKE_HEIGHT = (float)Gdx.graphics.getHeight() * (111.0f / 745.0f);
	public static final int   BACKGROUND_SPEED_MULT  = 100;
	
	public static final float SCREEN_WIDTH  = Gdx.graphics.getWidth();
	public static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
	
}
