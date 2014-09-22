package com.icescape.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Polygon;

public class Icicle extends GameObject {

	// Width and height of this icicle object.
	// Three different width/height combos defined in IcicleGenerator.java
	public int width, height;
	
	// Rate of falling acceleration
	private static int GRAV_ACCELERATION = -120;
	
	// Bounding triangle for collisions
	private Polygon boundingTriangle;
	
	// Initialize an icicle at (posX, posY)
	public Icicle(int posX, int posY, int width, int height) {
		position = new Vector2(posX, posY);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, GRAV_ACCELERATION);
		this.width = width;
		this.height = height;
		
		boundingTriangle = new Polygon(calculateVerticies());
		
		//Gdx.app.log("Icicle", "constructed a new icicle with origin at: " + posX + ", " + posY);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		boundingTriangle.setVertices(calculateVerticies());
	}
	
	public Polygon getBoundingTriangle() {
		return boundingTriangle;
	}
	
	private float[] calculateVerticies() {
		float topLeftX, topLeftY, topRightX, topRightY, bottomMiddleX, bottomMiddleY;
		topLeftX = position.x; topLeftY = position.y + height;
		topRightX = position.x + width; topRightY = position.y + height;
		bottomMiddleX = position.x + (width / 2); bottomMiddleY = position.y;
		
		float[] verticies = {topLeftX, topLeftY, topRightX, topRightY, bottomMiddleX, bottomMiddleY};
		return verticies;
	}
}
