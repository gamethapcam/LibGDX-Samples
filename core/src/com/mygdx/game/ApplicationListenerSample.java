package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.common.SampleBase;
import com.mygdx.game.common.SampleInfo;

public class ApplicationListenerSample extends SampleBase {
	private static final Logger log = new Logger(ApplicationListenerSample.class.getName(), Logger.DEBUG);

	public static final SampleInfo SAMPLE_INFO = new SampleInfo(ApplicationListenerSample.class);
	private boolean renderInterrupted = true;

	@Override
	public void create() {
		//Used to initialize game and load resources
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		log.debug("create");
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
