package samsungcampus.sprint2.elikur.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import samsungcampus.sprint2.elikur.Starter;
import samsungcampus.sprint2.elikur.core.ui.PointCounter;
import samsungcampus.sprint2.elikur.core.ui.TextButton;

public class ScreenRestart implements Screen {
    private Starter starter;
    private TextButton restartButton;
    private PointCounter pointCounter;
    private int gamePoints;

    public ScreenRestart(Starter starter, int gamePoints) {
        this.starter = starter;
        this.gamePoints = gamePoints;

        if (starter.iGameServices != null) {
            if (starter.iGameServices.getBestScore() < gamePoints)
                starter.iGameServices.saveBestScore(gamePoints);
        }
    }

    @Override
    public void show() {
        restartButton = new TextButton(Starter.screenSize.getWidth() / 2 - 280, Starter.screenSize.getHeight() / 2 - 200, "Restart");
        pointCounter = new PointCounter("Your score: ", Starter.screenSize.getWidth() / 2 - 220, Starter.screenSize.getHeight() - Starter.screenSize.getHeight() / 4);

        starter.lobbyMusic.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        starter.camera.update();
        starter.batch.setProjectionMatrix(starter.camera.combined);

        Vector3 touch = starter.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if (restartButton.isHit((int) touch.x, (int) touch.y)) {
            starter.lobbyMusic.stop();

            starter.gameScreen = new GameScreen(starter);
            starter.setScreen(starter.gameScreen);
        }

        // Обновление
        starter.background.move(delta);

        // Отрисовка
        starter.batch.begin();
        starter.background.draw(starter.batch, true);
        restartButton.draw(starter.batch);
        pointCounter.draw(starter.batch, gamePoints);
        starter.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
