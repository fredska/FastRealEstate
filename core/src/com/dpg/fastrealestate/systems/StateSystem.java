package com.dpg.fastrealestate.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.dpg.fastrealestate.components.StateComponent;

/**
 * Created by Fred on 3/27/2017.
 */
public class StateSystem extends IteratingSystem {
    private ComponentMapper<StateComponent> sc;

    public StateSystem() {
        super(Family.all(StateComponent.class).get());
        this.sc = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        sc.get(entity).time += deltaTime;
    }
}
