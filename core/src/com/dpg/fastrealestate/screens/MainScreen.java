package com.dpg.fastrealestate.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.dpg.fastrealestate.FastRealEstate;
import com.dpg.fastrealestate.World;
import com.dpg.fastrealestate.components.WorldMapComponent;
import com.dpg.fastrealestate.systems.CameraSystem;
import com.dpg.fastrealestate.systems.PropertySystem;
import com.dpg.fastrealestate.systems.RenderingSystem;
import com.dpg.fastrealestate.systems.StateSystem;

/**
 * Created by Fred on 3/27/2017.
 */
public class MainScreen extends ScreenAdapter {

    FastRealEstate game;
    Engine engine;
    private TiledMapRenderer tiledMapRenderer;

    private World world;

    public MainScreen(FastRealEstate game) {
        this.game = game;

        engine = new Engine();
        world = new World(engine);

        engine.addSystem(new CameraSystem());
        engine.addSystem(new StateSystem());
        engine.addSystem(new RenderingSystem(game.batch));
        engine.addSystem(new PropertySystem());

        world.create();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(world.tiledMap);

        resumeSystems();
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
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void update(float delta){
        if(delta > 0.1f)
            delta = 0.1f;

//        OrthographicCamera cam = engine.getSystem(RenderingSystem.class).getCamera();
//        cam.update();
//        tiledMapRenderer.setView(cam);
//        tiledMapRenderer.render();

        engine.update(delta);

        switch(game.gameState){
            case READY:
                break;
            case RUNNING:
                break;
            case PAUSED:
                break;
            case LEVEL_END:
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
}
