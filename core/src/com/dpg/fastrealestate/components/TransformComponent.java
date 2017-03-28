package com.dpg.fastrealestate.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Fred on 3/27/2017.
 */
public class TransformComponent implements Component {
    public final Vector3 pos = new Vector3();
    public final Vector2 scale = new Vector2(1.0f, 1.0f);

    //Likely no need for rotation
    public float rotation = 0.0f;
}
