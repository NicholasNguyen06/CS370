package com.icescape.game;

import java.util.Iterator;

import com.icescape.entities.Icicle;

import com.badlogic.gdx.Gdx;

import com.icescape.entities.*;

public class GameWorld {

	private Player player;
	private IcicleFactory icicleFactory;
	
	public GameWorld() {
		player = new Player(0, 0);
		icicleFactory = new IcicleFactory();
	}
	
	public void update(float delta) {
		//Gdx.app.log("GameWorld delta", delta + "");
		
		if (Gdx.input.justTouched()) {
			player.accelerateRight();
			//Gdx.app.log("Input", "justTouched");
		}
		
		icicleFactory.update(delta);
		player.update(delta);
		
		Iterator<Icicle> iter = icicleFactory.icicles.iterator();
		while (iter.hasNext()) {
			Icicle icicle = iter.next();
			if (icicle.getRect().overlaps(player.getRect())) {
				iter.remove();
			}
			
			if (icicle.getX() <= Icicle.height) {
				iter.remove();
			}
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public IcicleFactory getIcicleFactory() {
		return icicleFactory;
	}
}
