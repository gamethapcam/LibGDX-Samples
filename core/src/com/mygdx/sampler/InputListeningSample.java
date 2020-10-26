package com.mygdx.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.sampler.common.SampleBase;
import com.mygdx.sampler.common.SampleInfo;
import com.mygdx.sampler.utils.GdxUtils;

public class InputListeningSample extends SampleBase {

	private static final Logger LOG = new Logger(InputListeningSample.class.getName(), Logger.DEBUG);
	public static final SampleInfo SAMPLE_INFO = new SampleInfo(InputListeningSample.class);
	private static final int MAX_MESSAGE_COUNT = 15;

	private final Array<String> messages = new Array<>();

	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private BitmapFont font;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		camera = new OrthographicCamera();
		viewport = new FitViewport(1080, 720, camera);
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

		InputMultiplexer inputMultiplexer = new InputMultiplexer();

		InputAdapter firstProcessor = new InputAdapter(){
			@Override
			public boolean keyDown(int keycode) {
				LOG.debug("first - keyDown keycode= " + keycode);
				return true;
			}

			@Override
			public boolean keyUp(int keycode) {
				LOG.debug("first - keyUp keycode= " + keycode);
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				LOG.debug("first - keyTyped character= " + character);
				return false;
			}
		};

		InputAdapter secondProcessor = new InputAdapter(){
			@Override
			public boolean keyDown(int keycode) {
				LOG.debug("second - keyDown keycode= " + keycode);
				return true;
			}

			@Override
			public boolean keyUp(int keycode) {
				LOG.debug("second - Up keycode= " + keycode);
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				LOG.debug("second - keyTyped character= " + character);
				return false;
			}
		};

		inputMultiplexer.addProcessor(firstProcessor);
		inputMultiplexer.addProcessor(secondProcessor);

		Gdx.input.setInputProcessor(inputMultiplexer);
		//		Gdx.input.setInputProcessor(this);

//		Gdx.input.setInputProcessor(new InputAdapter(){
//			@Override
//			public boolean keyDown(int keycode) {
//				LOG.debug("keyDown keycode = " + keycode);
//				return true;
//			}
//		});
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
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
		for(int i = 0; i < messages.size; i++){
			font.draw(batch,messages.get(i),
					20,
					720 - 40 * (i + 1)
			);
		}
	}

	private void addMessage(String message){
		messages.add(message);

		if (messages.size > MAX_MESSAGE_COUNT)
			messages.removeIndex(0);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		String message = "KeyDown keycode= " + keycode;
		LOG.debug(message);
		addMessage(message);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		String message = "KeyUp keycode= " + keycode;
		LOG.debug(message);
		addMessage(message);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		String message = "KeyTyped keycode= " + character;
		LOG.debug(message);
		addMessage(message);
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		String message = "touchDown screenX= " + screenX + " screenY= " + screenY;
		LOG.debug(message);
		addMessage(message);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		String message = "touchUp screenX= " + screenX + " screenY= " + screenY;
		LOG.debug(message);
		addMessage(message);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		String message = "touchDragged screenX= " + screenX + " screenY= " + screenY;
		LOG.debug(message);
		addMessage(message);
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		String message = "mouseMoved screenX= " + screenX + " screenY= " + screenY;
		LOG.debug(message);
		addMessage(message);
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		String message = "scrolled amount= " + amount;
		LOG.debug(message);
		addMessage(message);
		return true;
	}
}

