package com.icescape.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;

public class Player {

	public int width = 100, height = 100;
	public Texture image;
	
	private Rectangle rect;
	private Vector2 position, acceleration, velocity;
	
	public Player(int posX, int posY) {
		position = new Vector2(posX, posY);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(-400, 0);
		
		rect = new Rectangle(posX, posY, width, height);
		
		image = new Texture(Gdx.files.internal("img/cropped_player.png"));
	}
	
	public void update(float delta) {
		position.add(velocity.cpy().scl(delta));
		velocity.add(acceleration.cpy().scl(delta));
		
		fixPositionIfNeeded();

		
		//Gdx.app.log("acceleration", acceleration.x + "");
	}
	
	public void accelerateRight() {
		velocity.add(150.0f, 0.0f);
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public Rectangle getRect() {
		rect.x = position.x;
		rect.y = position.y;
		return rect;
	}
	
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
