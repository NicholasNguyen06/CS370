package com.icescape.entities;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Snowball extends GameObject {

	public static int radius = 7;
	
	private final int X_VEL = -200;
	private final int Y_VEL = 150;
	
	private final int Y_ACC = -25;
	private final int X_ACC = 25;
	
	private Circle boundingCircle;

	public Snowball(int posX, int posY) {
		position = new Vector2(posX, posY);
		velocity = new Vector2(X_VEL, Y_VEL);
		acceleration = new Vector2(X_ACC, Y_ACC);
		
		boundingCircle = new Circle(position, radius);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		boundingCircle.x = position.x;
		boundingCircle.y = position.y;
	}
	
	public Circle getBoundingCircle() {
		return boundingCircle;
	}
}
