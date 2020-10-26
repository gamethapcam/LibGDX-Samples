package com.mygdx.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.sampler.common.SampleBase;
import com.mygdx.sampler.common.SampleInfo;

public class GdxModuleInfoSample extends SampleBase {
	private static final Logger log = new Logger(GdxModuleInfoSample.class.getName(), Logger.DEBUG);

	public static final SampleInfo SAMPLE_INFO = new SampleInfo(GdxModuleInfoSample.class);
	private boolean renderInterrupted = true;

	@Override
	public void create() {
		//Used to initialize game and load resources
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		log.debug("app= " + Gdx.app);
		log.debug("audio= " + Gdx.audio);
		log.debug("input= " + Gdx.input);
		log.debug("files= " + Gdx.files);
		log.debug("net= " + Gdx.net);
		log.debug("graphics= " + Gdx.graphics);
	}

	@Override
	public void resize(int width, int height) {
		//Used to handle setting a new screen size
		log.debug("resize() width= " + width + " height= " + height);
	}

	@Override
	public void render() {
		//Used to update and render the game elements called 60 times per second
		if (renderInterrupted) {
			log.debug("render()");
			renderInterrupted = false;
		}
	}

	@Override
	public void pause() {
		//Used to save game state when it loses focus, which does not involve the actual
		// gameplay being paused unless the developer wants it to pause
		log.debug("pause()");
		renderInterrupted = true;
	}

	@Override
	public void resume() {
		//Used to handle the game coming back from being paused and restores game state
	log.debug("resume() ");
	renderInterrupted = true;

	}

	@Override
	public void dispose() {
		//Used to free resources and clean up

	log.debug("dispose() ");
	}
}
