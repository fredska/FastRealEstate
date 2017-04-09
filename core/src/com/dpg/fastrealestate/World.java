package com.dpg.fastrealestate;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.ComponentType;
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
    private MapLayer propertyLayer = null;
    private boolean[] isPropertyOccupied;

    public World(Engine engine){
        this.engine = engine;
        this.rand = new Random();
    }

    public void create(){
        createWorldMap();
        createCamera();

        propertyLayer = tiledMap.getLayers().get("PropertyLayout");
        int propertyId = 0;

        isPropertyOccupied = new boolean[propertyLayer.getObjects().getCount()];

        for(int c = 0; c < isPropertyOccupied.length / 2; c++){
            createHouse();
        }
    }

    private int getEmptyPropertyLot(int range){
        int propertyId = MathUtils.random(range-1);
        while(isPropertyOccupied[propertyId]){
            propertyId = MathUtils.random(range-1);
        }

        return propertyId;
    }

    public void createCamera(){
        Entity entity = new Entity();

        CameraComponent cc = new CameraComponent();
        TransformComponent tc = new TransformComponent();
        cc.camera = engine.getSystem(RenderingSystem.class).getCamera();
        cc.camera.position.set(new Vector3(720,600,0));
        tc.pos.set(new Vector3(0,0,0));

        entity.add(cc);
        entity.add(tc);

        engine.addEntity(entity);
    }

    public void createWorldMap(){
        //TODO: Load tiled world
        tiledMap = new TmxMapLoader().load("tiledmap/testLevel.tmx");
    }

    public void createHouse(){
        int newPropertyId = getEmptyPropertyLot(isPropertyOccupied.length);
        isPropertyOccupied[newPropertyId] = true;
        MapObject mapObject = propertyLayer.getObjects().get(newPropertyId);
        createHouse(newPropertyId, ((RectangleMapObject)mapObject).getRectangle());
    }

    public void destroyHouse(Entity entity){
        PropertyComponent pc = ComponentMapper.getFor(PropertyComponent.class).get(entity);
        isPropertyOccupied[pc.propId] = false;
        engine.removeEntity(entity);
    }

    private void createHouse(int propId, Rectangle rect){
        Entity entity = new Entity();

        BoundsComponent boundsComponent = new BoundsComponent();
        TransformComponent transC = new TransformComponent();
        TextureComponent texC = new TextureComponent();
        PropertyComponent pc = new PropertyComponent();
        StateComponent sc = new StateComponent();

        sc.set(0);

        pc.propId = propId;
        pc.minValue = rand.nextFloat() * 1000 + 5000;
        pc.maxValue = rand.nextFloat() * 4000 + 20000;
        pc.lifeSpan = rand.nextFloat() * 5f + 10;


        transC.pos.set(rect.x, rect.y, 1);
        boundsComponent.bounds.set(rect);
        System.out.println(rect);

        texC.region = new TextureRegion(Assets.house_01);


        entity.add(transC);
        entity.add(texC);
        entity.add(pc);
        entity.add(sc);
        entity.add(boundsComponent);

        engine.addEntity(entity);
    }
}
