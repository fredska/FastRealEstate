package com.dpg.fastrealestate.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dpg.fastrealestate.FastRealEstate;
import com.dpg.fastrealestate.components.LabelComponent;
import com.dpg.fastrealestate.components.TransformComponent;
import com.uwsoft.editor.renderer.data.LabelVO;

import java.awt.*;

/**
 * Created by Fred on 4/6/2017.
 */
public class UISystem extends IteratingSystem{
    BitmapFont font = null;

    ComponentMapper<LabelComponent> lcm;

    public UISystem(SpriteBatch batch){
        super(Family.all(LabelComponent.class).get());
        font = new BitmapFont();
        this.batch = batch;
        lcm = ComponentMapper.getFor(LabelComponent.class);
    }
    SpriteBatch batch;
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        LabelComponent lc = lcm.get(entity);
        TransformComponent tc = (ComponentMapper.getFor(TransformComponent.class)).get(entity);
        batch.begin();
        font.draw(batch, lc.text, tc.pos.x, tc.pos.y);
batch.flush();
batch.end();
    }
}
