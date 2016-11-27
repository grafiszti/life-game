package pl.grafiszti;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class DirectedGame implements ApplicationListener {
	private boolean init;
	private AbstractScreen currentScreen;
	private AbstractScreen nextScreen;
	private FrameBuffer currentFBufferObject; // frame buffer
	private FrameBuffer nextFBufferObject; // frame buffer
	private SpriteBatch batch;

	public void setScreen(AbstractScreen screen) {
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();

		if (!init) {
			currentFBufferObject = new FrameBuffer(Format.RGB888, w, h, false);
			nextFBufferObject = new FrameBuffer(Format.RGB888, w, h, false);
			batch = new SpriteBatch();
			init = true;
		}

		nextScreen = screen;
		nextScreen.show();
		nextScreen.resize(w, h);
		nextScreen.render(0); // screen update after reload

		if (currentScreen != null) {
			currentScreen.pause();
		}

		nextScreen.pause();
		// Gdx.input.setInputProcessor(null); // wylaczamy input
	}

	@Override
	public void create() {}

	@Override
	public void resize(int width, int height) {
		if (currentScreen != null) {
			currentScreen.resize(width, height);
		}

		if (nextScreen != null) {
			nextScreen.resize(width, height);
		}
	}

	@Override
	public void render() {
		float deltaTime = Math.min(Gdx.graphics.getDeltaTime(), 1.0f / 60.0f);

		if (nextScreen == null) {
			if (currentScreen != null) {
				currentScreen.render(deltaTime);
			}
		} else {
			if (currentScreen != null) {
				currentScreen.hide();
			}

			nextScreen.resume();
			currentScreen = nextScreen;
			nextScreen = null;
		}
	}

	@Override
	public void pause() {
		if (currentScreen != null)
			currentScreen.pause();
	}

	@Override
	public void resume() {
		if (currentScreen != null)
			currentScreen.resume();
	}

	@Override
	public void dispose() {
		if (currentScreen != null)
			currentScreen.hide();
		if (nextScreen != null)
			nextScreen.hide();
		if (init) {
			currentFBufferObject.dispose();
			currentScreen = null;
			nextFBufferObject.dispose();
			nextScreen = null;
			batch.dispose();
			init = false;
		}
	}
}
