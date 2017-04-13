package com.dpg.fastrealestate.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dpg.fastrealestate.FastRealEstate;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.additional.ButtonComponent;
import com.uwsoft.editor.renderer.components.label.LabelComponent;
import com.uwsoft.editor.renderer.resources.ResourceManager;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

/**
 * Created by Fred on 4/12/2017.
 */
public class MenuStage extends Stage {

    // Overlap2D  provides this easy asset manager that loads things as they are
    // provided by default when exporting from overlap
    private ResourceManager assetManager;

    public SceneLoader sl;

    public MenuStage(final FastRealEstate game){
        super(game.viewport);
        sl = new SceneLoader();

        sl.loadScene("MenuScreen");

        sl.addComponentsByTagName("button", ButtonComponent.class);


        ItemWrapper rootItem = new ItemWrapper(sl.getRoot());
        // Configure menu buttons
        ButtonComponent playBtnComponent = rootItem.getChild("play_btn").getEntity().getComponent(ButtonComponent.class);
        if(playBtnComponent != null){
            playBtnComponent.addListener(new ButtonComponent.ButtonListener() {
                @Override
                public void touchUp() {

                }

                @Override
                public void touchDown() {

                }

                @Override
                public void clicked() {
                    game.getScreen().dispose();
                    game.initialize();
                    game.setScreen(game.mainScreen);

                }
            });
        } else {
            System.err.println("Cannot find Play Button Component");
        }

        if(game.highScore > 0){
            LabelComponent currHighScoreComponent = rootItem.getChild("curr_high_score").getEntity().getComponent(LabelComponent.class);
            currHighScoreComponent.text.setLength(0);
            currHighScoreComponent.text.append(game.highScore);
        }

        assetManager = new ResourceManager();
        assetManager.initAllResources();

    }
}
