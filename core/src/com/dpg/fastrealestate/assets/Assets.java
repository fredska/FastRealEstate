package com.dpg.fastrealestate.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Fred on 3/27/2017.
 */
public class Assets {

    public static Texture tileSet;
    public static TextureRegion house_01;
    public static TextureRegion house_02;


    public static void load(){
        tileSet = loadTexture("tilesets/public_tileset_by_orohnpokemon-d4vpcfu.png");
        house_01 = new TextureRegion(tileSet,384 ,176,80,128);
        house_02 = new TextureRegion(tileSet,464,176,80,128);
    }

    private static Texture loadTexture(String filePath){
        return new Texture(Gdx.files.internal(filePath));
    }
}
