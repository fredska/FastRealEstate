package com.dpg.fastrealestate.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Fred on 3/27/2017.
 */
public class Assets {

    public static Texture house_01;

    public static void load(){
        Pixmap house_01_pix = new Pixmap(64, 64, Pixmap.Format.RGB888);
        house_01_pix.setColor(Color.BLUE);
        house_01_pix.drawLine(0,0,0,63);
        house_01_pix.drawLine(0,63,63,63);
        house_01_pix.drawLine(63,63,63,0);
        house_01_pix.drawLine(63,0,0,0);
        house_01 = new Texture(house_01_pix);
        house_01_pix.dispose();
    }

    private static Texture loadTexture(String filePath){
        return new Texture(Gdx.files.internal(filePath));
    }
}
