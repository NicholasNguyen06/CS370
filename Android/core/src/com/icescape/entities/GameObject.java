package com.icescape.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

	protected Vector2 position, acceleration, velocity;

	// update() will be called by all subclasses of GameObject
	public void update(float delta) {
		// Add current velocity, scaled to the elapsed time since the last update, to the object's position
		position.add( velocity.cpy().scl(delta) );
		
		// Do the same for velocity, adding acceleration
		velocity.add(acceleration.cpy().scl(delta));	
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
}
