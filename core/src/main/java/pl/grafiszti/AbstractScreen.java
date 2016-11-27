package pl.grafiszti;

import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {
	protected DirectedGame game;

	public AbstractScreen(DirectedGame game) {
		this.game = game;
	}

	public abstract void render(float deltaTime);

	@Override
	public void show() {}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}
}
