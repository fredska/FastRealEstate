package com.dpg.fastrealestate.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;

/**
 * Created by Fred on 4/5/2017.
 */
public class GameStage extends Stage {

    // Overlap2D  provides this easy asset manager that loads things as they are
    // provided by default when exporting from overlap
    private ResourceManager assetManager;

    private Viewport viewPort;
    private SceneLoader sl;

    public GameStage(SceneLoader sl, Viewport viewport){
        super(new StretchViewport(480,800));
        Gdx.input.setInputProcessor(this);
        this.sl = sl;
        this.viewPort = viewport;

        assetManager = new ResourceManager();
        assetManager.initAllResources();

        sl.loadScene("MainScene");
    }
}
