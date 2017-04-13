package com.dpg.fastrealestate.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dpg.fastrealestate.FastRealEstate;
import com.dpg.fastrealestate.systems.UISystem;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;

/**
 * Created by Fred on 4/5/2017.
 */
public class GameStage extends Stage {

    // Overlap2D  provides this easy asset manager that loads things as they are
    // provided by default when exporting from overlap
    private ResourceManager assetManager;

    FastRealEstate game;
    public SceneLoader sl;

    public GameStage(FastRealEstate game){
        super(game.viewport);
        this.game = game;
        sl = new SceneLoader();
        sl.loadScene("MainScene");

        assetManager = new ResourceManager();
        assetManager.initAllResources();

        sl.getEngine().addSystem(new UISystem(game));
    }

    public void update(float delta){
        sl.getEngine().update(delta);
        this.act();
        this.draw();
    }


}
