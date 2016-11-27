package pl.grafiszti;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

public class MainGdx extends DirectedGame {
	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_NONE);
		setScreen(new GameScreen(this));
	}
}