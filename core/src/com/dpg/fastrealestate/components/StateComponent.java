package com.dpg.fastrealestate.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Fred on 3/27/2017.
 */
public class StateComponent implements Component {
    private int state = 0;
    public float time = 0.0f;

    public int get() {
        return state;
    }

    public void set(int newState) {
        state = newState;
        time = 0.0f;
    }
}
