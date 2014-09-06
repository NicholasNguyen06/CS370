package com.icescape.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetLoader {
	
	public static TextureAtlas playerMoveRight;
	private static String player_textureatlas_name = "anim/skiier/skiier_anim.atlas";
	
	public static Sprite player;
	private static String player_texture_name = "img/cropped_player.png";
	
	public static Texture icicle;
	private static String icicle_texture_name = "img/icicle.png";
	
	public static Texture background;
	private static String background_texture_name = "img/background.png";
	
	public static void load() {
		playerMoveRight = new TextureAtlas(Gdx.files.internal(player_textureatlas_name));
		
		player = new Sprite();
		player.setRegion(new Texture(Gdx.files.internal(player_texture_name)));
			
		icicle = new Texture(Gdx.files.internal(icicle_texture_name));
		background = new Texture(Gdx.files.internal(background_texture_name));
	}
}
