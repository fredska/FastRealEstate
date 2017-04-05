package com.dpg.fastrealestate;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
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

        MapLayer propertyLayer = tiledMap.getLayers().get("PropertyLayout");
        for(MapObject mapObject : propertyLayer.getObjects()){
            if(mapObject instanceof RectangleMapObject){
                createHouse(((RectangleMapObject)mapObject).getRectangle());
            }
//            createHouse(new Vector2((Float)mapObject.getProperties().get("x"), (Float)mapObject.getProperties().get("y")));
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

    public void createHouse(){
        MapLayer propertyLayer = tiledMap.getLayers().get("PropertyLayout");
        MapProperties props = propertyLayer.getObjects().get(0).getProperties();
//        createHouse(new Vector2((Float) props.get("x"), (Float)props.get("y")));
    }

    public void createHouse(Rectangle rect){
        Entity entity = new Entity();

        BoundsComponent boundsComponent = new BoundsComponent();
        TransformComponent transC = new TransformComponent();
        TextureComponent texC = new TextureComponent();
        PropertyComponent pc = new PropertyComponent();
        StateComponent sc = new StateComponent();

        sc.set(0);

        pc.minValue = rand.nextInt() * 1000;
        pc.maxValue = rand.nextInt() * 20000;
        pc.lifeSpan = rand.nextFloat() * 4f;

        transC.pos.set(rect.x, rect.y, 1);
        boundsComponent.bounds.set(rect);

        switch(MathUtils.random(2)){
            case 0:
                texC.region = new TextureRegion(Assets.house_01);
                break;
            case 1:
                texC.region = new TextureRegion(Assets.house_02);
                break;
            default:
                texC.region = new TextureRegion(Assets.house_01);
        }

        entity.add(transC);
        entity.add(texC);
        entity.add(pc);
        entity.add(sc);
        entity.add(boundsComponent);

        engine.addEntity(entity);
    }
}
