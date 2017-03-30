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
	public SpriteBatch batch;
	public GameState gameState;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameState = GameState.LOADING;

		//TODO: Do loading things, like Assets.load() here
		Assets.load();
		setScreen(new MainScreen(this));
		gameState = GameState.READY;
	}


	@Override
	public void render(){
		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		super.render();
	}
}
