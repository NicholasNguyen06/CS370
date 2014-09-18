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
	
	public static Sprite playerDeathFrame(int index) {
		if (index < 1) {
			Gdx.app.log("AssetLoader: playerDeathFrame", "sprites index under range");
		} else if (index > playerDeath_frameC) {
			Gdx.app.log("AssetLoader: playerDeathFrame", "sprites index over range");
		}
		
		return playerDeathFrames.get(index);
	}
	
	public static int playerMoveRight_frameC;
	private static Array<Sprite> playerMoveRightFrames;
	private static String playerMoveRight_atlasname = "anim/skiier/skiier_anim.atlas";
	
	public static int playerDeath_frameC; // Play each frame twice to slow death anim. down
	private static Array<Sprite> playerDeathFrames;
	private static String playerDeath_atlasname = "anim/skiier/death.atlas";
		
	public static Sprite player;
	private static String player_texture_name = "img/cropped_player.png";
	
	public static Texture icicle;
	private static String icicle_texture_name = "img/icicle.png";
	
	public static Texture background;
	private static String background_texture_name = "img/background.png";
	
	public static void load() {
		playerMoveRightFrames = new TextureAtlas(Gdx.files.internal(playerMoveRight_atlasname)).createSprites();
		playerMoveRight_frameC = playerMoveRightFrames.size;
		
		playerDeathFrames = new TextureAtlas(Gdx.files.internal(playerDeath_atlasname)).createSprites();
		playerDeath_frameC = playerDeathFrames.size;
		
		player = new Sprite();
		player.setRegion(new Texture(Gdx.files.internal(player_texture_name)));
			
		icicle = new Texture(Gdx.files.internal(icicle_texture_name));
		background = new Texture(Gdx.files.internal(background_texture_name));
	}
}
