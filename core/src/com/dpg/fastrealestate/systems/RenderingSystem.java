package com.dpg.fastrealestate.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dpg.fastrealestate.FastRealEstate;
import com.dpg.fastrealestate.components.PropertyComponent;
import com.dpg.fastrealestate.components.StateComponent;
import com.dpg.fastrealestate.components.TextureComponent;
import com.dpg.fastrealestate.components.TransformComponent;
import com.uwsoft.editor.renderer.SceneLoader;

import java.util.Comparator;

/**
 * Created by Fred on 3/27/2017.
 */
public class RenderingSystem extends IteratingSystem{

    static final float FRUSTUM_WIDTH = 480;
    static final float FRUSTUM_HEIGHT = 800;
    public static final float PIXELS_TO_METRES = 1.0f;

    public SceneLoader sl;
    public Viewport viewport;

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    private SpriteBatch batch;
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private OrthographicCamera cam;

    private BitmapFont font;

    ComponentMapper<TextureComponent> texCm;
    ComponentMapper<TransformComponent> transCm;

    FastRealEstate game;

    PropertyComponent pc;

    public RenderingSystem(FastRealEstate game){
        super(Family.all(TransformComponent.class, TextureComponent.class).get());

        this.game = game;

        texCm = ComponentMapper.getFor(TextureComponent.class);
        transCm = ComponentMapper.getFor(TransformComponent.class);

        renderQueue = new Array<Entity>();

        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity entityA, Entity entityB) {
                return (int) Math.signum(transCm.get(entityB).pos.z -
                        transCm.get(entityA).pos.z);
            }
        };

        this.batch = game.batch;

        cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);


        //FIXME:  The Rendering system should not control which tiled2d map is loaded
        tiledMap = new TmxMapLoader().load("tiledmap/testLevel.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        this.sl = game.sl;
        this.viewport = game.viewport;

        font = new BitmapFont();

        pc = null;
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        renderQueue.sort(comparator);

        cam.update();
        batch.setProjectionMatrix(cam.combined);

        //Render the world
        if(tiledMapRenderer != null){
            tiledMapRenderer.setView(cam);
            tiledMapRenderer.render();
        }

        batch.begin();

        for (Entity entity : renderQueue) {
            TextureComponent tex = texCm.get(entity);

            if (tex.region == null) {
                continue;
            }

            TransformComponent t = transCm.get(entity);

            float width = tex.region.getRegionWidth();
            float height = tex.region.getRegionHeight();
            float originX = width * 0.5f;
            float originY = height * 0.5f;

            batch.draw(tex.region,
                    t.pos.x, t.pos.y,
                    originX, originY,
                    width, height,
                    t.scale.x * PIXELS_TO_METRES, t.scale.y * PIXELS_TO_METRES,
                    MathUtils.radiansToDegrees * t.rotation);

            //Draw price above entities with PropertyComponent
            if((pc = entity.getComponent(PropertyComponent.class)) != null){
                StateComponent sc = entity.getComponent(StateComponent.class);
                font.setColor(Color.BLUE);
                font.draw(batch, (int)pc.getCurrentValue(sc) + "", t.pos.x, t.pos.y);

            }
        }

        batch.end();
        renderQueue.clear();
    }


    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    public OrthographicCamera getCamera() {
        return cam;
    }
}
