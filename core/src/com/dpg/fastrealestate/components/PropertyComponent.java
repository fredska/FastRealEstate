package com.dpg.fastrealestate.components;

/**
 * Created by Fred on 3/27/2017.
 */

import com.badlogic.ashley.core.Component;

/**
 * Base component for a 'Property'
 */
public class PropertyComponent implements Component {
    public int propId;

    public float minValue;
    public float maxValue;

    //Dictates how long a property 'lives'
    public float lifeSpan;

    public boolean isOwned;
}
