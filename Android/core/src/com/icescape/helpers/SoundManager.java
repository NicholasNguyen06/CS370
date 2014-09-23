package com.icescape.helpers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.math.MathUtils;

public class SoundManager {
	
	private static HashMap<String, Sound> effects;
	
	private static Sound icicle_hit_player;
	private static Sound skiing_background;

	private static Sound[] player_pole_swooshes;
	private static int     player_pole_swoosh_count = 7;
	private static int     last_played_index = 0;
	private static float   player_pole_volume = 0.3f;

	
	public static void playEffect(String name) {
		assert(effects.containsKey(name));
		
		if (name == "player_pole_swoosh") {
			playPoleSwoosh();
		}
		else effects.get(name).play();
	}
	
	public static void pauseEffect(String name) {
		assert(effects.containsKey(name));
		
		effects.get(name).pause();
	}
	
	public static void stopEffect(String name) {
		assert(effects.containsKey(name));
		
		effects.get(name).stop();
	}
	
	public static void playBackground() {
		effects.get("skiing_background").loop(1.0f);
	}
	
	public static void load() {
		effects = new HashMap<String, Sound>();
		player_pole_swooshes = new Sound[player_pole_swoosh_count];
		loadEffects();
	}
	
	private static void loadEffects() {
		icicle_hit_player = Gdx.audio.newSound(Gdx.files.internal("audio/effects/icicle_hit_player.mp3"));
		effects.put("icicle_hit_player", icicle_hit_player);
		
		for(int i = 0; i < player_pole_swoosh_count; ++i) {
			player_pole_swooshes[i] = Gdx.audio.newSound(Gdx.files.internal("audio/effects/ski_pole_" + (i+1) + ".mp3"));
		}
		
		skiing_background = Gdx.audio.newSound(Gdx.files.internal("audio/effects/skiing.mp3"));
		effects.put("skiing_background", skiing_background);
	}
	
	private static void playPoleSwoosh() {
		int random = MathUtils.random(0, player_pole_swooshes.length-1);
		player_pole_swooshes[last_played_index].stop();
		player_pole_swooshes[random].play(player_pole_volume);
		
		last_played_index = random;
	}
}
