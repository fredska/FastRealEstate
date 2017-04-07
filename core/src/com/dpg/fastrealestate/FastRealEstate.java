package com.dpg.fastrealestate;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dpg.fastrealestate.assets.Assets;
import com.dpg.fastrealestate.assets.GameState;
import com.dpg.fastrealestate.screens.GameStage;
import com.dpg.fastrealestate.screens.MainScreen;
import com.uwsoft.editor.renderer.SceneLoader;

public class FastRealEstate extends Game {
	public SpriteBatch batch;
	public GameState gameState;

	public GameStage gameStage;

	public SceneLoader sl;
	public Viewport viewport;

	public int score = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameState = GameState.LOADING;

		viewport = new FitViewport(480,800);

		sl = new SceneLoader();
		sl.loadScene("MainScene");
		gameStage = new GameStage(sl, viewport);

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
