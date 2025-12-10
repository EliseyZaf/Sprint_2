package samsungcampus.sprint2.elikur;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import samsungcampus.sprint2.elikur.core.addative.ScreenSize;
import samsungcampus.sprint2.elikur.core.android.IGameServices;
import samsungcampus.sprint2.elikur.core.ui.Background;
import samsungcampus.sprint2.elikur.screens.GameScreen;
import samsungcampus.sprint2.elikur.screens.ScreenRestart;
import samsungcampus.sprint2.elikur.screens.ScreenStart;

public class Starter extends Game {
    public Screen gameScreen, screenRestart;
    public static ScreenSize screenSize;
    public OrthographicCamera camera;
    public SpriteBatch batch;
    public Background background;
    public IGameServices iGameServices;

    public Music gameMusic, lobbyMusic;

    public Starter(IGameServices iGameServices) {
        this.iGameServices = iGameServices;
    }

    public Starter() {}

    @Override
    public void create() {
        screenSize    = new ScreenSize(1640, 720);
        camera        = new OrthographicCamera();
        batch         = new SpriteBatch();
        background    = new Background();

        camera.setToOrtho(false, screenSize.getWidth(), screenSize.getHeight());

        lobbyMusic = Gdx.audio.newMusic(Gdx.files.internal("media/night.mp3"));
        lobbyMusic.setLooping(true);

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("media/game.mp3"));
        gameMusic.setLooping(true);

        gameScreen = new ScreenStart(this);
        setScreen(gameScreen);
    }

    public ScreenSize getScreenSize() {
        return screenSize;
    }

    @Override
    public void dispose() {
        super.dispose();

        batch.dispose();
        background.dispose();
    }
}
