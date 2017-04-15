package com.dpg.fastrealestate.components.test;

import com.dpg.fastrealestate.components.PropertyComponent;
import com.dpg.fastrealestate.components.StateComponent;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by darkp on 4/7/2017.
 */
public class PropertyComponentTest {
    PropertyComponent pc;
    StateComponent sc;

    @Before
    public void initialize(){
        sc = new StateComponent();
        sc.time = 0;

        pc = new PropertyComponent();
        pc.lifeSpan = 1;
        pc.minValue = 1000;
        pc.maxValue = 10000;
    }

    @Test
    public void getCurrentValue_begin(){
        sc.time = 0;
        assertEquals(pc.minValue, pc.getCurrentValue(sc), 0.0001f);
    }

    @Test
    public void getCurrentValue_halflife(){
        sc.time = 0.5f;
        assertEquals(pc.maxValue, pc.getCurrentValue(sc), 0.0001f);
    }

    @Test
    public void getCurrentValue_endOfLife(){
        sc.time = 1f;
        assertEquals(pc.minValue, pc.getCurrentValue(sc), 0.0001f);
    }
}
