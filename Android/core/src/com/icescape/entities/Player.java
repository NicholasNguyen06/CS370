package com.icescape.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.icescape.helpers.AssetLoader;

public class Player extends GameObject {

	// Width and height of all player objects
	// Currently, player is just a rectangle.
	// Down the road, it could probably be represented with 
	// a circle and a rectangle
	public static int width = 100, height = 100;

	// Rate of constant leftward acceleration
	private final float LEFT_ACCELERATION = -400.0f;
	
	// Speed to add to player's velocity when screen is tapped
	private float moveSpeed = 150.0f;
	
	// Bounding box for collisions
	private Rectangle rect;
	
	// Current frame of animation
	private int currentFrame = 1;
	private boolean isAnimating = false;
	private boolean getNextFrame = false;
	
	public Player(float posX, float posY) {
		position = new Vector2(posX, posY);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(LEFT_ACCELERATION, 0);
		
		rect = new Rectangle(posX, posY, width, height);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		fixPositionIfNeeded();
		
		if (isAnimating && getNextFrame) {
			if (++currentFrame > 6) {
				currentFrame = 1;
				isAnimating = false;
			}
			getNextFrame = false;
			
		} else if (isAnimating && !getNextFrame) {
			getNextFrame = true;
		}
	}
	
	// Move the player right, on screen tap
	public void moveRight() {
		velocity.add(150.0f, 0.0f);
		isAnimating = true;
	}
	
	public Rectangle getRect() {
		rect.x = position.x;
		rect.y = position.y;
		return rect;
	}
	
	public Sprite getCurrentFrame() {
		if (isAnimating == true) {
			Gdx.app.log("currentFrame", currentFrame + "");
			return AssetLoader.playerMoveRight.createSprite("eskimo" + currentFrame);
		} else {
			return AssetLoader.playerMoveRight.createSprite("eskimo1");
		}
	}
	
	public float getMoveSpeed() {
		return moveSpeed;
	}
	
	public void setMoveSpeed(float newSpeed) {
		moveSpeed = newSpeed;
	}
	
	// Fix player's position if it moves off the screen
	private void fixPositionIfNeeded() {
		if (position.x < 0) {
			position.x = 0;
			velocity.x = 0;
		}
		
		if (position.x > Gdx.graphics.getWidth() - width) {
			position.x = Gdx.graphics.getWidth() - width;
			velocity.x = 0;
		}
	}
	
	private boolean isMovingRight() {
		return velocity.x > 0;
	}
	
	private boolean isMovingLeft() {
		return velocity.x < 0;
	}
	
}
