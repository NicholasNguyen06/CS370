package com.icescape.entities;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Snowball extends GameObject {

	public static int radius = 25;
	
	private final int X_VEL = 100;
	private final int Y_ACC = -10;
	
	private Circle boundingCircle;

	public Snowball(int posX, int posY) {
		position = new Vector2(posX, posY);
		velocity = new Vector2(X_VEL, 0);
		acceleration = new Vector2(0, Y_ACC);
		
		boundingCircle = new Circle(position, radius);
	}
	
	public Circle getBoundingCircle() {
		return boundingCircle;
	}
}
