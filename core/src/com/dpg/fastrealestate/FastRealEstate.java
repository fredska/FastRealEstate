package com.dpg.fastrealestate;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dpg.fastrealestate.assets.Assets;
import com.dpg.fastrealestate.assets.GameState;
import com.dpg.fastrealestate.screens.MainScreen;

public class FastRealEstate extends Game {
	public GameState gameState;
	
	@Override
	public void create () {
		gameState = GameState.LOADING;

		//TODO: Do loading things, like Assets.load() here
		Assets.load();
		batch = new SpriteBatch();
		bitmapFont = new BitmapFont();
		setScreen(new MainScreen(this));
		gameState = GameState.READY;

	}

	public SpriteBatch batch;
	public BitmapFont bitmapFont;

	@Override
	public void render(){
		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(Assets.house_01,20,20);
		bitmapFont.draw(batch, "1234", 20, 108);
		batch.end();

		super.render();
	}
}
