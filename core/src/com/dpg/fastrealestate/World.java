package com.dpg.fastrealestate;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.dpg.fastrealestate.assets.Assets;
import com.dpg.fastrealestate.components.*;
import com.dpg.fastrealestate.systems.RenderingSystem;

import java.util.Random;

/**
 * Created by Fred on 3/29/2017.
 */
public class World {

    private Random rand;
    private Engine engine;

    public TiledMap tiledMap;


    public World(Engine engine){
        this.engine = engine;
        this.rand = new Random();
    }

    public void create(){
        createCamera();
        createWorldMap();

        //Add a ridiculous # of houses
        for(int i = 0; i < 2500; i++){
            createHouse(new Vector2(rand.nextInt(1400) + 100, rand.nextInt(1400) + 100));
        }
    }

    public void createCamera(){
        Entity entity = new Entity();

        CameraComponent cc = new CameraComponent();
        TransformComponent tc = new TransformComponent();
        cc.camera = engine.getSystem(RenderingSystem.class).getCamera();

        tc.pos.set(Vector3.Zero);

        entity.add(cc);
        entity.add(tc);

        engine.addEntity(entity);
    }

    public void createWorldMap(){
        //TODO: Load tiled world
        tiledMap = new TmxMapLoader().load("tiledmap/testLevel.tmx");
    }

    public void createHouse(Vector2 position){
        Entity entity = new Entity();

        TransformComponent transC = new TransformComponent();
        TextureComponent texC = new TextureComponent();
        PropertyComponent pc = new PropertyComponent();
        StateComponent sc = new StateComponent();

        sc.set(0);

        pc.minValue = rand.nextInt() * 1000;
        pc.maxValue = rand.nextInt() * 20000;
        pc.lifeSpan = rand.nextFloat() * 4f;

        transC.pos.set(position.x, position.y, 0);
        texC.region = new TextureRegion(Assets.house_01);

        entity.add(transC);
        entity.add(texC);
        entity.add(pc);
        entity.add(sc);

        engine.addEntity(entity);
    }
}
