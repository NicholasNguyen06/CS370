package com.icescape.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;


public class Icicle extends GameObject {

	// Width and height of all icicle objects
	// Currently, icicles are rectangles for collisions,
	// we will need to create a Triangle class to represent them more accurately.
	public static int width = 64, height = 64;
	
	// Rate of falling acceleration
	private static int GRAV_ACCELERATION = -120;
	
	// Bounding box for collisions
	private Rectangle rect;
	
	// Initialize an icicle at (posX, posY)
	public Icicle(int posX, int posY) {
		position = new Vector2(posX, posY);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, GRAV_ACCELERATION);
		
		rect = new Rectangle(posX, posY, width, height);
		
		//Gdx.app.log("Icicle", "constructed a new icicle with origin at: " + posX + ", " + posY);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
	}
	
	public Rectangle getRect() {
		rect.x = position.x;
		rect.y = position.y;
		return rect;
	}
}
