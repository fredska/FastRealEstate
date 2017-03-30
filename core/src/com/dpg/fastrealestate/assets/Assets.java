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
        house_01 = loadTexture("images/Animals-Dog-House-icon.png");
    }

    private static Texture loadTexture(String filePath){
        return new Texture(Gdx.files.internal(filePath));
    }
}
