package com.icescape.game;

import java.util.Iterator;
import java.util.LinkedList;

import com.icescape.entities.Icicle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class IcicleFactory {
	public LinkedList<Icicle> icicles;
	
	private long lastIcicleTime;

	public IcicleFactory() {
		icicles = new LinkedList<Icicle>();
		spawnIcicle();
	}
	public void update(float delta) {
		if (TimeUtils.nanoTime() - lastIcicleTime > 1000000000) spawnIcicle();
		
		for (Icicle icicle : icicles) {
			icicle.update(delta);
		}
	}
	
	private void spawnIcicle() {
		Icicle icicle = new Icicle(MathUtils.random(0, Gdx.graphics.getWidth() - Icicle.width), Gdx.graphics.getHeight() - Icicle.height);
		icicles.add(icicle);
		lastIcicleTime = TimeUtils.nanoTime();
	}
}
