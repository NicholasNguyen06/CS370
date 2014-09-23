package com.icescape.helpers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;

public class SoundManager {
	
	private static HashMap<String, Sound> sounds;
	
	private static Sound icicle_hit_player;
	
	
	public static void play(String name) {
		sounds.get("icicle_hit_player").play();
	}
	
	public static void load() {
		sounds = new HashMap<String, Sound>();
		
		icicle_hit_player = Gdx.audio.newSound(Gdx.files.internal("audio/effects/icicle_hit_player.mp3"));
		sounds.put("icicle_hit_player", icicle_hit_player);
	}
}
