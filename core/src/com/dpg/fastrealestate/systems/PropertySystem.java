package com.dpg.fastrealestate.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.dpg.fastrealestate.assets.Assets;
import com.dpg.fastrealestate.components.*;

/**
 * Created by Fred on 3/29/2017.
 */
public class PropertySystem extends IteratingSystem {

    ComponentMapper<PropertyComponent> pcm;
    ComponentMapper<StateComponent> scm;
    ComponentMapper<TransformComponent> tcm;
    ComponentMapper<BoundsComponent> bcm;
    public PropertySystem(){
        super(Family.all(TransformComponent.class, PropertyComponent.class, StateComponent.class).get());

        pcm = ComponentMapper.getFor(PropertyComponent.class);
        scm = ComponentMapper.getFor(StateComponent.class);
        tcm = ComponentMapper.getFor(TransformComponent.class);
        bcm = ComponentMapper.getFor(BoundsComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PropertyComponent pc = pcm.get(entity);
        StateComponent sc = scm.get(entity);
        TransformComponent tc = tcm.get(entity);

        if(sc.time >= pc.lifeSpan) {
            sc.time = 0f;
//            tc.pos.x = MathUtils.random(100, 1500);
//            tc.pos.y = MathUtils.random(100, 1500);
        }
    }
}
