package com.dpg.fastrealestate.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.dpg.fastrealestate.FastRealEstate;
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

    private Camera camera;
    private FastRealEstate game;


    private boolean isLeftTouched;

    public PropertySystem(Camera camera, FastRealEstate game){
        super(Family.all(TransformComponent.class, PropertyComponent.class, StateComponent.class, BoundsComponent.class).get());

        isLeftTouched = false;
        this.camera = camera;
        this.game = game;

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
        BoundsComponent bc = bcm.get(entity);

        if(sc.time >= pc.lifeSpan) {
            sc.time = 0f;
//            tc.pos.x = MathUtils.random(100, 1500);
//            tc.pos.y = MathUtils.random(100, 1500);
        }

        if(Gdx.input.justTouched()){
            Vector3 unprojectedCoord = camera.unproject(
                    new Vector3(Gdx.input.getX(),Gdx.input.getY(), 0));
            if(bc.bounds.contains(new Vector2(unprojectedCoord.x, unprojectedCoord.y))){
                if(!pc.isOwned){
                    if(game.funds >= pc.getCurrentValue(sc)){
                        game.funds -= pc.getCurrentValue(sc);
                        pc.isOwned = true;
                        System.out.println("Now own house id: " + pc.propId);
                        entity.getComponent(TextureComponent.class).region = Assets.house_02;
                    }
                } else {
                    game.funds += pc.getCurrentValue(sc);
                    pc.isOwned = false;
                    entity.getComponent(TextureComponent.class).region = Assets.house_01;
                }
            }
        }

    }
}
