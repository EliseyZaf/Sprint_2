package samsungcampus.sprint2.elikur.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector3;

import samsungcampus.sprint2.elikur.Starter;
import samsungcampus.sprint2.elikur.core.ui.PointCounter;
import samsungcampus.sprint2.elikur.core.ui.TextButton;

public class ScreenStart implements Screen {
    private TextButton startButton;
    private Starter starter;
    private PointCounter pointCounter;

    public ScreenStart(Starter starter) {
        this.starter = starter;
    }

    @Override
    public void show() {
        startButton = new TextButton(Starter.screenSize.getWidth() / 2 - 280, Starter.screenSize.getHeight() / 2 - 200, "Start");
        pointCounter = new PointCounter("Best: ", Starter.screenSize.getWidth() / 2 - 120, Starter.screenSize.getHeight() - Starter.screenSize.getHeight() / 4);

        starter.lobbyMusic.play();
    }

    @Override
    public void render(float delta) {
        starter.camera.update();
        starter.batch.setProjectionMatrix(starter.camera.combined);

        Vector3 touch = starter.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if (startButton.isHit((int) touch.x, (int) touch.y)) {
            starter.lobbyMusic.stop();

            starter.gameScreen = new GameScreen(starter);
            starter.setScreen(starter.gameScreen);
        }

        // Обновление
        starter.background.move(delta);

        // Отрисовка
        starter.batch.begin();
        starter.background.draw(starter.batch, true);
        startButton.draw(starter.batch);
        pointCounter.draw(starter.batch, starter.iGameServices != null ? starter.iGameServices.getBestScore() : 0);
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
