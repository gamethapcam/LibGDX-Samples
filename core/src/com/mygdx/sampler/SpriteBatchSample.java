package com.mygdx.sampler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.sampler.common.SampleBase;
import com.mygdx.sampler.common.SampleInfo;
import com.mygdx.sampler.utils.GdxUtils;

public class SpriteBatchSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(SpriteBatchSample.class);

    private static final float WORLD_WIDTH = 10.8f;
    private static final float WORLD_HEIGHT = 7.2f;

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    private Texture texture;
    private Color oldcolor;

    private int width = 1;
    private int height = 1;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        batch = new SpriteBatch();

        oldcolor = new Color();

        texture = new Texture(Gdx.files.internal("raw/character.png"));
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        draw();
        batch.end();
    }

    private void draw(){
        batch.draw(texture,
                0,0,
                width / 2f, height /2f,
                width,height,
                1.0f, 1.0f,
                0.0f,
                0,0,
                texture.getWidth(),texture.getHeight(),
                false,false
        );


        // scaled character
        batch.draw(texture,
                4,2,
                width / 2f, height /2f,
                width,height,
                2.0f,2.0f,
                0.0f,
                0,0,
                texture.getWidth(),texture.getHeight(),
                false,true
        );

        oldcolor.set(batch.getColor());

        batch.setColor(Color.GREEN);

        batch.draw(texture,
                8,1,
                width / 2f, height /2f,
                width,height,
                1.0f,1.0f,
                0.0f,
                0,0,
                texture.getWidth(),texture.getHeight(),
                false,false
        );

        batch.setColor(oldcolor);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height,true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}
