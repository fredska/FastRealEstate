package com.dpg.fastrealestate;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dpg.fastrealestate.assets.Assets;
import com.dpg.fastrealestate.assets.GameState;
import com.dpg.fastrealestate.screens.GameStage;
import com.dpg.fastrealestate.screens.MainScreen;
import com.dpg.fastrealestate.screens.MenuScreen;
import com.dpg.fastrealestate.screens.MenuStage;
import com.uwsoft.editor.renderer.SceneLoader;

public class FastRealEstate extends Game {
	public SpriteBatch batch;
	public GameState gameState;


	/* All GameStage Objects */
	public GameStage gameStage;
	public MenuStage menuStage;

	/* All Screen Objects */
	public MainScreen mainScreen;
	public MenuScreen menuScreen;

	public SceneLoader sl;
	public Viewport viewport;

	public int score = 0;
	public int highScore = 0;

	public float timeLeft;

	//Starting 'currency'
	public float funds;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameState = GameState.LOADING;

		Assets.load();

		viewport = new StretchViewport(480,800);

		sl = new SceneLoader();
		sl.loadScene("MenuScreen");

		/* Load all stage objects */

		/* Load all screen objects */
		menuScreen = new MenuScreen(this);
		mainScreen = new MainScreen(this);

		/* Pause the mainScreen as we'll resume this later */
//		mainScreen.pause();

		//TODO: Do loading things, like Assets.load() here

		setScreen(menuScreen);


		//Initialize all the defaults
		initialize();
	}


	@Override
	public void render(){

		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	public void initialize(){
		funds = 50000;
		score = 0;
		timeLeft = 5;
		gameState = GameState.READY;
	}
}
