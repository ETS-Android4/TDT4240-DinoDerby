package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.views.MenuScreen;
import com.mygdx.game.views.PlayScreen;
import com.mygdx.game.views.SettingsScreen;
import com.mygdx.game.views.GameOverScreen;

public class MyGdxGame extends Game {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final int MENU = 0;
	public static final int PLAY = 1;
    public static final int GAMEOVER= 2;
	public static final int SETTINGS = 3;

	public static final String TITLE = "Dino Derby";

	private MenuScreen menuScreen;
	private PlayScreen playScreen;
	private GameOverScreen gameOverScreen;
	private SettingsScreen settings;

	FireBaseInterface FBIC;

	public Music music;

	protected OrthographicCamera camera;
	Viewport viewport;

	public void changeScreen(int screen) {
		switch (screen) {
			case MENU:
				menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				break;
			case PLAY:
				playScreen = new PlayScreen(this);
				this.setScreen(playScreen);
				break;
			case SETTINGS:
				settings = new SettingsScreen(this);
				this.setScreen(settings);
				break;
			case GAMEOVER:
				gameOverScreen= new GameOverScreen(this);
				this.setScreen(gameOverScreen);
				break;
		}

	}

	public MyGdxGame(FireBaseInterface FBIC) {
		this.FBIC = FBIC;
	}

	@Override
	public void create () {

		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);

		camera  = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		music = Gdx.audio.newMusic(Gdx.files.internal("kahoot.wav"));
		music.setLooping(true);
		music.setVolume(0.05f);
		music.play();
	}


	public SettingsScreen getSettings(){
		return this.settings;
	}
	/*
	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.setProjectionMatrix(camera.combined);
		gsm.update(Gdx.graphics.getDeltaTime());
		camera.update();
		gsm.render(batch);

	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	 */
}
