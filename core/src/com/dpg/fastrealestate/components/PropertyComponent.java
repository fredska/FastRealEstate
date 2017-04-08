package com.dpg.fastrealestate.components;

/**
 * Created by Fred on 3/27/2017.
 */

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.MathUtils;

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

    public float getCurrentValue(StateComponent sc){
        if(sc.time >= (lifeSpan / 2f)){
            return MathUtils.lerp(minValue,maxValue,1 - ((sc.time - (lifeSpan / 2f)) / (lifeSpan / 2f)));
        } else
            return MathUtils.lerp(minValue,maxValue,sc.time / (lifeSpan / 2f));
    }
}
