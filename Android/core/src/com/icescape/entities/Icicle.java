package com.icescape.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;

public class Icicle {

	public static int width = 64, height = 64;
	public Texture image;
	
	private Rectangle rect;
	private Vector2 position, acceleration, velocity;
	
	public Icicle(int posX, int posY) {
		position = new Vector2(posX, posY);
		velocity = new Vector2(0, -1);
		acceleration = new Vector2(0, -120);
		
		rect = new Rectangle(posX, posY, width, height);
		image = new Texture(Gdx.files.internal("img/icicle.png"));
		
		Gdx.app.log("Icicle", "constructed a new icicle with origin at: " + posX + ", " + posY);
	}
	
	public void update(float delta) {
		position.add(velocity.cpy().scl(delta));
		velocity.add(acceleration.cpy().scl(delta));	
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
	
	
}
