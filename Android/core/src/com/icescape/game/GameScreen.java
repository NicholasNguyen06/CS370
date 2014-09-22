/*
 * GameScreen implements libgdx's Screen interface. It contains everything necessary to play the game,
 * a GameRenderer and a GameWorld.
 */

package com.icescape.game;

import com.badlogic.gdx.Gdx;
import com.icescape.game.AbstractScreen;
import com.icescape.helpers.Constants;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameScreen extends AbstractScreen {

	private GameRenderer renderer;
	private GameWorld world;
	
	private Stage stage;
	private Button pauseButton;
	
	private Texture pauseTex;
	private Texture playTex;
	private Sprite pauseSprite;
	private Sprite playSprite;
	
	
	private Skin pauseSkin;
	private ButtonStyle pauseButtonStyle;
	
	private float runtime = 0;
	private boolean isPaused = false;
	
	public GameScreen() {
		world = new GameWorld();
		renderer = new GameRenderer(world);
		
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);	
		
		pauseSkin = new Skin();
		
		pauseTex = new Texture(Gdx.files.internal("UI/Buttons/pause_button.png"));
		playTex  = new Texture(Gdx.files.internal("UI/Buttons/play_button.png"));
		
		pauseSprite = new Sprite(pauseTex);
		playSprite = new Sprite(playTex);
		
		pauseSprite.setPosition(0, 0);
		pauseSprite.setSize(64, 64);
		playSprite.setPosition(0, 0);
		playSprite.setSize(64, 64);
		
		pauseSkin.add("pause", pauseSprite);
		pauseSkin.add("play", playSprite);
		
		pauseButtonStyle = new ButtonStyle();
		pauseButtonStyle.up = pauseSkin.getDrawable("pause");
		pauseButtonStyle.checked = pauseSkin.getDrawable("play");
		
		pauseButton = new Button(pauseButtonStyle);
		pauseButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("Pause Button", "Pressed");
				togglePause();
			}
		});
		
		stage.addActor(pauseButton);
	}
	
	public boolean gameIsOver() {
		return world.gameIsOver();
	}
	
	@Override
	public void render(float delta) {
		if(isPaused == false) {
			runtime += delta;
			world.update(delta);
		}

		renderer.render(runtime);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
	public void togglePause() {
		if (isPaused) {
			resume();
			pauseButton.setChecked(false);
		}
		else {
			pause();
			pauseButton.setChecked(true);
		}
	}

	@Override
	public void pause() {
		isPaused = true;
		world.pause();
	}

	@Override
	public void resume() {
		isPaused = false;
		world.resume();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
