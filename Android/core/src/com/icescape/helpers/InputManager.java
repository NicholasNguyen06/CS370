package com.icescape.helpers;

import com.icescape.game.MyGdxGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Orientation;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Input.Keys;

public class InputManager implements InputProcessor {
	MyGdxGame game;
	
	public InputManager(MyGdxGame g) {
		this.game = g;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT) {
			
		}
		
		else if (keycode == Keys.RIGHT) {
			
		}
		
		return true; // Input was processed
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getDeviceRotation() {
		if (Gdx.input.isPeripheralAvailable(Peripheral.Compass)) {
			float azmuth = Gdx.input.getAzimuth();
			float pitch = Gdx.input.getPitch();
			float roll = Gdx.input.getRoll();
			
			return "Azmuth: " + Float.toString(azmuth) + "\nPitch: " + Float.toString(pitch) + "\nRoll: " + Float.toString(roll);			
		}
		else return "No compass available";
	}

}
