package pl.grafiszti;

import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {
	protected DirectedGame game;

	public AbstractScreen(DirectedGame game) {
		this.game = game;
	}

	public abstract void render(float deltaTime);

	public abstract void resize(int width, int height);

	public abstract void show();

	public abstract void hide();

	public abstract void pause();

	public void resume() {}

	public void dispose() {}
}
