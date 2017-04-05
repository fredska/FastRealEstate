package com.dpg.fastrealestate.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dpg.fastrealestate.components.CameraComponent;
import com.dpg.fastrealestate.components.TransformComponent;

/**
 * Created by Fred on 3/29/2017.
 */
public class CameraSystem extends IteratingSystem {
    private ComponentMapper<CameraComponent> cm;
    private ComponentMapper<TransformComponent> tm;

    public CameraSystem(){
        super(Family.all(CameraComponent.class, TransformComponent.class).get());

        cm = ComponentMapper.getFor(CameraComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent tc = entity.getComponent(TransformComponent.class);
        CameraComponent cc = entity.getComponent(CameraComponent.class);

        //Hard set min & max ranges for drag speed
        if(cc.dragSpeed < 0.01f){
            cc.dragSpeed = 0.01f;
        }
        if(cc.dragSpeed < 1.5f) {
            cc.dragSpeed = 1.5f;
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            cc.camera.position.x -= Gdx.input.getDeltaX() * cc.dragSpeed;
            cc.camera.position.y += Gdx.input.getDeltaY() * cc.dragSpeed;
//            System.out.println("Camera pos: " + cc.camera.position);
        }

    }
}
