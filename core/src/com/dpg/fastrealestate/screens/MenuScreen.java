package com.dpg.fastrealestate.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.dpg.fastrealestate.FastRealEstate;
import com.uwsoft.editor.renderer.resources.ResourceManager;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

/**
 * Created by Fred on 4/12/2017.
 */
public class MenuScreen extends ScreenAdapter {

    FastRealEstate game;


    private ResourceManager rm;
    private ItemWrapper rootItem;

    private MenuStage screenStage;

    public MenuScreen(final FastRealEstate game) {
        this.game = game;


        screenStage = new MenuStage(game);

        rm = new ResourceManager();
        rm.initAllResources();
        resumeSystems();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        screenStage.sl.getEngine().update(delta);
        screenStage.act();
        screenStage.draw();

    }

    public void resumeSystems(){
//        for(EntitySystem es : engine.getSystems()){
//            es.setProcessing(true);
//        }
    }

    public void pauseSystems(){
//        for(EntitySystem es : engine.getSystems()){
//            es.setProcessing(false);
//        }
    }
}
