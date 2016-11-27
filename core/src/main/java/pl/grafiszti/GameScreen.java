package pl.grafiszti;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen extends AbstractScreen {
	private ShapeRenderer shapeRenderer;

	private Board board;

	private float timeCounter = 0.0f;

	public GameScreen(DirectedGame game) {
		super(game);
		shapeRenderer = new ShapeRenderer();
		board = new Board(720);
	}

	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		if (timeCounter > 0.0) {
			timeCounter = 0;
			board.update();
		}

		timeCounter += delta;
		renderFrame(board, shapeRenderer);
		shapeRenderer.end();
	}

	private void renderFrame(Board frame, ShapeRenderer shapeRenderer) {
		for (int i = 0; i < frame.getSize(); i++) {
			for (int j = 0; j < frame.getSize(); j++) {
				if (frame.isSet(i, j)) {
					shapeRenderer.rect(i, j, 1, 1);
				}
			}
		}
	}

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
