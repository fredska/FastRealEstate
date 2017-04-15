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

         // See https://www.desmos.com/calculator/lac2i0bgum for formulas
        double A1 = -(Math.pow(0,2)) + Math.pow(lifeSpan/2,2);
        double B1 = lifeSpan/2d;
        double D1 = -minValue + maxValue;
        double A2 = -(Math.pow(lifeSpan/2d,2)) + Math.pow(lifeSpan,2);
        double B2 = lifeSpan/2d;
        double D2 = -maxValue + minValue;
        double Bmul = -(B2 / B1);
        double A3 = Bmul * A1 + A2;
        double D3 = Bmul * D1 + D2;

        double a = D3 / A3;
        double b = (D1 - A1 * a) / (B1);
        double c = minValue;

        return (float)(a * Math.pow(sc.time,2) + b * sc.time + c);

//        if(sc.time >= (lifeSpan / 2f)){
//            return MathUtils.lerp(minValue,maxValue,1 - ((sc.time - (lifeSpan / 2f)) / (lifeSpan / 2f)));
//        } else
//            return MathUtils.lerp(minValue,maxValue,sc.time / (lifeSpan / 2f));
    }
}
