package com.icescape.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

// TODO: cache each texture atlas frame created to avoid redundant createSprite calls
public class AssetLoader {
	
	public static Sprite playerMoveRightFrame(int index) {
		if (index < 1) {
			Gdx.app.log("AssetLoader: playerMoveRightFrame", "sprites index under range");
		} else if (index > playerMoveRight_frameC) {
			Gdx.app.log("AssetLoader: playerMoveRightFrame", "sprites index over range");
		}
		
		return playerMoveRightFrames.get(index);
	}
	
	public static int playerMoveRight_frameC = 20;
	private static Array<Sprite> playerMoveRightFrames;
	private static String playerMoveRight_atlasname = "anim/skiier/skiier_anim.atlas";
	
	public static Animation playerDeath;
	private static int playerDeath_frameC = 20;
	private static String playerDeath_atlasname = "anim/skiier/death.atlas";
		
	public static Sprite player;
	private static String player_texture_name = "img/cropped_player.png";
	
	public static Texture icicle;
	private static String icicle_texture_name = "img/icicle.png";
	
	public static Texture background;
	private static String background_texture_name = "img/background.png";
	
	public static void load() {
		playerMoveRightFrames = new TextureAtlas(Gdx.files.internal(playerMoveRight_atlasname)).createSprites();
		
		playerDeath = new Animation(0.5f, new TextureAtlas(Gdx.files.internal(playerDeath_atlasname)).createSprites(), Animation.PlayMode.NORMAL);
		
		player = new Sprite();
		player.setRegion(new Texture(Gdx.files.internal(player_texture_name)));
			
		icicle = new Texture(Gdx.files.internal(icicle_texture_name));
		background = new Texture(Gdx.files.internal(background_texture_name));
	}
}
