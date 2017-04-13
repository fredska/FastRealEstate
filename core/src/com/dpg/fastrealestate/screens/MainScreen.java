package com.dpg.fastrealestate.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.dpg.fastrealestate.FastRealEstate;
import com.dpg.fastrealestate.World;
import com.dpg.fastrealestate.assets.GameState;
import com.dpg.fastrealestate.systems.*;
import com.uwsoft.editor.renderer.resources.ResourceManager;

/**
 * Created by Fred on 3/27/2017.
 */
public class MainScreen extends ScreenAdapter {

    FastRealEstate game;
    Engine engine;
    private TiledMapRenderer tiledMapRenderer;

    private World world;

    private ResourceManager rm;

    private GameStage uiStage;

    public MainScreen(FastRealEstate game) {
        this.game = game;

        engine = new Engine();
        world = new World(engine);

        engine.addSystem(new CameraSystem());
        engine.addSystem(new StateSystem());
        engine.addSystem(new RenderingSystem(game));
        engine.addSystem(new PropertySystem(engine.getSystem(RenderingSystem.class).getCamera(), game, world));

        //Create the UI stage
        uiStage = new GameStage(game);

        world.create();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(world.tiledMap);

//        gameStage = new GameStage(game.sl,game.viewport);
        rm = new ResourceManager();
        rm.initAllResources();
        resumeSystems();

        game.gameState = GameState.RUNNING;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
        pauseSystems();
    }

    @Override
    public void resume() {
        super.resume();
        resumeSystems();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void update(float delta){
        if(delta > 0.1f)
            delta = 0.1f;

        engine.update(delta);
        uiStage.update(delta);

        //End the game when the timer is up
        if(game.timeLeft <= 0f)
            game.gameState = GameState.LEVEL_END;

        switch(game.gameState){
            case READY:
                break;
            case RUNNING:
                break;
            case PAUSED:
                break;
            case LEVEL_END:
                levelEnd();
                break;
            case OVER:
                break;
            default:
        }
    }

    public void resumeSystems(){
        for(EntitySystem es : engine.getSystems()){
            es.setProcessing(true);
        }
    }

    public void pauseSystems(){
        for(EntitySystem es : engine.getSystems()){
            es.setProcessing(false);
        }
    }

    public void levelEnd(){
        if(game.highScore < game.funds)
            game.highScore = (int)game.funds;
        game.getScreen().dispose();
        game.initialize();
        game.setScreen(new MenuScreen(game));
    }
}
