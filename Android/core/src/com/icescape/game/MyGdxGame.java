package com.icescape.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import com.icescape.entities.Player;
import com.badlogic.gdx.utils.TimeUtils;

public class MyGdxGame extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
	
}