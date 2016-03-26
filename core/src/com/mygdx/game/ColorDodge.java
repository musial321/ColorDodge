package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.Screens.PlayScreen;

public class ColorDodge extends Game {
	public static SpriteBatch batch;
	public static Texture spike;
	public static Texture spikeright;
	public static Texture bar;
	public static Texture leftbar;
	public static Texture rightbar;

	public static Sound jump;

	public static Sprite spikesprite;
	public static Sprite rightspikesprite;

	public static Texture topbar;
	public static Texture thing;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static int totalWidth;
	public static int totalHeight;
	public static final String TITLE = "Color Dodge";
	public static BitmapFont font60;
	public static BitmapFont font36;
	public static BitmapFont font36Black;
	public static Preferences prefs;
	public static OrthographicCamera camera;

	PlayScreen playScreen;

	@Override
	public void create () {
		initFonts();

		jump = Gdx.audio.newSound(Gdx.files.internal("jump2.wav"));

		bar = new Texture("barbot.png");
		leftbar = new Texture("bar.png");
		spike = new Texture("spike2.png");
		rightbar = new Texture("rightbar.png");
		spikeright = new Texture("spikeright.png");
		topbar = new Texture("bartop.png");
		thing = new Texture("thing.png");

		spikesprite = new Sprite(spike);
		rightspikesprite = new Sprite(spikeright);


		prefs = Gdx.app.getPreferences("Preferences.txt");// We store the value 10 with the key of "highScore"


		if(!prefs.contains("highscore"))
		{
			prefs.putInteger("highscore", 0);
			prefs.flush();// This saves the preferences file.
		}



		//System.out.println(prefs.getInteger("highscore"));

		totalWidth = WIDTH;
		totalHeight = HEIGHT;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);

		playScreen = new PlayScreen(this);

		this.setScreen(playScreen);
	}

	@Override
	public void render () {
		super.render();

	}

	private void initFonts()
	{
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("DAYPBL.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
		param.size = 36;
		param.color = Color.WHITE;
		generator.generateData(param);

		font36 = generator.generateFont(param);

		param.size = 60;
		generator.generateData(param);

		font60 = generator.generateFont(param);


		param.size = 36;
		param.color = Color.BLACK;
		generator.generateData(param);

		font36Black = generator.generateFont(param);
	}

	@Override
	public void dispose()
	{
		playScreen.dispose();
	}
}
