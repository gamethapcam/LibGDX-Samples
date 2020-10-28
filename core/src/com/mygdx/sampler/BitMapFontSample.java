package com.mygdx.sampler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.sampler.common.SampleBase;
import com.mygdx.sampler.common.SampleInfo;

public class BitMapFontSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(BitMapFontSample.class);

    private static final float WIDTH = 1080f;
    private static final float HEIGHT = 720f;


    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private BitmapFont uiFont;
    private GlyphLayout glyphLayout = new GlyphLayout();

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WIDTH,HEIGHT,camera);
        batch = new SpriteBatch();
        uiFont = new BitmapFont(Gdx.files.internal("fonts/ui_font_32.fnt"));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height,true);
    }

    @Override
    public void render() {

    batch.setProjectionMatrix(camera.combined);

    batch.begin();

    draw();
    batch.end();
    }

    private void draw(){
        String text1 = "Using bitmap font";
        uiFont.draw(batch,text1,0,HEIGHT);

        String text2 = "BITMAP font text 2";
        glyphLayout.setText(uiFont,text2);
        uiFont.draw(batch,text2,
                (WIDTH - glyphLayout.width) / 2f,
                (HEIGHT - glyphLayout.height) / 2f);
    }

    @Override
    public void dispose() {
        batch.dispose();
        uiFont.dispose();
    }
}
