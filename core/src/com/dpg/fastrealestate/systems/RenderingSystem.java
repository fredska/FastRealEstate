package com.dpg.fastrealestate.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.dpg.fastrealestate.components.PropertyComponent;
import com.dpg.fastrealestate.components.StateComponent;
import com.dpg.fastrealestate.components.TransformComponent;

import javax.swing.plaf.nimbus.State;

/**
 * Created by Fred on 3/27/2017.
 */
public class RenderingSystem extends IteratingSystem{

    ComponentMapper<StateComponent> sc;
    ComponentMapper<TransformComponent> tc;
    ComponentMapper<PropertyComponent> pc;

    public RenderingSystem(){
        super(Family.all(TransformComponent.class, StateComponent.class, PropertyComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
