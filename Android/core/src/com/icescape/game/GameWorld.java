/*
 * GameWorld contains all game objects (icicles, player, penguin, etc) inside a GameScreen.
 * Handles:
 * 		- Updating all game objects
 * 		- Creating new icicles from an IcicleFactory based on elapsed time
 * 		-
 * TODO:
 * 		Only allow a certain number of icicles on screen at a time
 * 		Generate icicles more quickly as the game goes on
 * 		Scale icicle acceleration as the game goes on
 */

package com.icescape.game;

import java.util.Iterator;

import com.icescape.entities.*;
import com.icescape.helpers.Constants;
import com.icescape.helpers.SoundManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameWorld {

	private Player player;
	
	private Array<Snowball> snowballs;
	private long lastSnowballTime;
	private long snowballSpawnSpacing = 999999999;

	private Array<Icicle> icicles;
	private long lastIcicleTime;
	private long icicleSpawnSpacing = 999999999; //500000000;

	private int iciclesDodged = 0;
	
	private boolean gameOver = false;
	private boolean isPaused = false;

	public GameWorld() {
		player = new Player(0, Constants.BACKGROUND_LAKE_HEIGHT);
		
		icicles = new Array<Icicle>();
		snowballs = new Array<Snowball>();
		
		lastIcicleTime = lastSnowballTime = TimeUtils.nanoTime();
	}
	
	public void pause() {
		isPaused = true;
	}
	
	public void resume() {
		isPaused = false;
	}
	
	public boolean isPaused() {
		return isPaused;
	}
	
	public void update(float delta) {
		if (isPaused == false) {

			// Generate a new icicle if necessary
			if (TimeUtils.nanoTime() - lastIcicleTime > icicleSpawnSpacing) {
				icicles.add(IcicleGenerator.spawnIcicle());
				lastIcicleTime = TimeUtils.nanoTime();
			}
	
			// Throw a new snowball if necessary
			if (TimeUtils.nanoTime() - lastSnowballTime > snowballSpawnSpacing) {
				snowballs.add(SnowballGenerator.spawnSnowball());
				lastSnowballTime = TimeUtils.nanoTime();
			}
			
			// If the screen was tapped, update the player
			if (Gdx.input.justTouched()) {
				player.moveRight();
				SoundManager.playEffect("player_pole_swoosh");
			}
	
			player.update(delta);
			if (player.isAlive == false) {
				Gdx.app.exit();
			}
			
			updateIcicles(delta);
			updateSnowballs(delta);
		}
	}
	
	public boolean gameIsOver() {
		return gameOver;
	}

	public Player getPlayer() {
		return player;
	}

	public Array<Icicle> getIcicles() {
		return icicles;
	}
	
	public Array<Snowball> getSnowballs() {
		return snowballs;
	}
	
	public int getIciclesDodged() {
		return iciclesDodged;
	}
	
	private void updateIcicles(float delta) {
		Iterator<Icicle> iter = icicles.iterator();
		while (iter.hasNext()) {
			Icicle icicle = iter.next();
			icicle.update(delta);

			// If icicle has touched the top of the lake
			if (icicle.getY() <= Constants.BACKGROUND_LAKE_HEIGHT) {
				iter.remove();
				++iciclesDodged;
			}

			// Icicle has collided with player
			else if (intersects(player.getRect(), icicle.getBoundingTriangle())) {
				iter.remove();
				SoundManager.playEffect("icicle_hit_player");
				player.HitByIcicle();
			}
		}
	}
	
	private void updateSnowballs(float delta) {
		Iterator<Snowball> iter = snowballs.iterator();
		while (iter.hasNext()) {
			Snowball snowball = iter.next();
			snowball.update(delta);
			
			// If snowball has touched the top of the lake
			if (snowball.getY() <= Constants.BACKGROUND_LAKE_HEIGHT) {
				iter.remove();
			}
			
			else if ( Intersector.overlaps(snowball.getBoundingCircle(), player.getRect()) ) {
				iter.remove();
			}
		}
	}
	
	/*
	 * Polygon must represent a 2-d triangle, such that:
	 * 		(p.verticies[0], p.verticies[1]) == (topleftX, topleftY)
	 * 		(p.verticies[2], p.verticies[3]) == (toprightX, toprightY)
	 * 		(p.verticies[4], p.verticies[5]) == (bottomMidX, bottomMidY)
	 * 
	 * 		Only applicable to triangles representing falling icicles.
	 *							_________
	 *							\		/
	 *							 \     /
	 *							  \   /
	 *							   \ /
	 *								*
	 */
	private boolean intersects(Rectangle r, Polygon p) {
		
		float[] verticies = p.getVertices();
		
		Vector2 triangleTopLeft  = makePoint(verticies[0], verticies[1]);
		Vector2 triangleTopRight = makePoint(verticies[2], verticies[3]);
		Vector2 triangleBottom   = makePoint(verticies[4], verticies[5]);
		
		//Vector2 rectBottomLeft  = makePoint(r.x, r.y);
		//Vector2 rectBottomRight = makePoint(r.x + r.width, r.y);
		Vector2 rectTopLeft		= makePoint(r.x, r.y + r.height);
		Vector2 rectTopRight    = makePoint(r.x + r.width, r.y + r.height);
		
		// Make sure triangle's bottom is touching or beneath rect's top edge,
		// triangle's left corner is touching or to the right of rect's left edge,
		// and triangle's right corner is touching or to the left or rect's right edge.
		if (triangleBottom.y   > rectTopLeft.y  || 
			triangleTopLeft.x  > rectTopRight.x ||
			triangleTopRight.x < rectTopLeft.x) return false;

		// Check if the triangle has directly intersected the top edge of rect
		if (triangleBottom.x < rectTopRight.x &&
			triangleBottom.x > rectTopLeft.x) return true;
		
		Vector2 garbage = new Vector2();
		
		// Check if triangle's left edge has crossed rect's top edge
		if ( Intersector.intersectLines(triangleTopLeft, triangleBottom, rectTopLeft, rectTopRight, garbage) )
			return true;
		
		// Check if triangle's right edge has croossed rect's top edge
		if ( Intersector.intersectLines(triangleTopRight, triangleBottom, rectTopLeft, rectTopRight, garbage) )
			return true;
		
		return false;
	}
	
	private Vector2 makePoint(float x, float y) {
		return new Vector2(x, y);
	}
}
