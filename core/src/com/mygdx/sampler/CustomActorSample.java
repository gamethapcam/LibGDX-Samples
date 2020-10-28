package com.mygdx.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.sampler.common.CustomActor;
import com.mygdx.sampler.common.SampleBase;
import com.mygdx.sampler.common.SampleInfo;
import com.mygdx.sampler.utils.GdxUtils;

public class CustomActorSample extends SampleBase {
    private static final Logger log = new Logger(CustomActorSample.class.getName(), Logger.DEBUG);
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(CustomActorSample.class);

    private static final float WORLD_WIDTH = 1080f;
    private static final float WORLD_HEIGHT = 720f;

    private Viewport viewport;

    private Stage stage;
    private Texture texture;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        viewport = new FitViewport(1080,720);
        stage = new Stage(viewport);

        texture = new Texture(Gdx.files.internal("raw/custom-actor.png"));

        CustomActor customActor = new CustomActor(new TextureRegion(texture));
        customActor.setSize(160,80);
        customActor.setPosition((WORLD_WIDTH - customActor.getWidth()) / 2f,
                (WORLD_HEIGHT - customActor.getHeight()) / 2f
        );

        customActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                log.debug("customActor clicked event = " + event + " x = " + x + "y = " + y);
            }
        });

        stage.addActor(customActor);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
