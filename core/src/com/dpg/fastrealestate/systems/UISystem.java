package com.dpg.fastrealestate.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dpg.fastrealestate.FastRealEstate;
import com.uwsoft.editor.renderer.components.MainItemComponent;
import com.uwsoft.editor.renderer.components.label.LabelComponent;


/**
 * Created by Fred on 4/6/2017.
 */
public class UISystem extends IteratingSystem{
    BitmapFont font = null;
    FastRealEstate game;
    ComponentMapper<LabelComponent> lcm;

    public UISystem(FastRealEstate game){
        super(Family.all(LabelComponent.class).get());
        font = new BitmapFont();
        this.game = game;
        lcm = ComponentMapper.getFor(LabelComponent.class);
    }
    SpriteBatch batch;
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        LabelComponent lc = lcm.get(entity);
        if(entity.getComponent(MainItemComponent.class).tags.contains("score_label")) {
            lc.text.setLength(0);
            lc.text.append("Score: " + game.score);
        }
        if(entity.getComponent(MainItemComponent.class).tags.contains("funds_label")) {
            lc.text.setLength(0);
            lc.text.append("Funds: " + game.funds);
        }
    }
}
