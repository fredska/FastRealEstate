package com.dpg.fastrealestate.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;


/**
 * Created by Fred on 3/27/2017.
 */
public class CameraComponent implements Component{
    public OrthographicCamera camera;
    public float dragSpeed = 0.1f;
}
