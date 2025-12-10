package samsungcampus.sprint2.elikur.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Arrays;

import samsungcampus.sprint2.elikur.Starter;
import samsungcampus.sprint2.elikur.core.addative.Transform;
import samsungcampus.sprint2.elikur.core.objects.AngryBird;
import samsungcampus.sprint2.elikur.core.objects.Bird;
import samsungcampus.sprint2.elikur.core.objects.SheildBox;
import samsungcampus.sprint2.elikur.core.objects.SmallBox;
import samsungcampus.sprint2.elikur.core.objects.TubeSystem;
import samsungcampus.sprint2.elikur.core.ui.Background;
import samsungcampus.sprint2.elikur.core.ui.PointCounter;

public class GameScreen implements Screen {
    private Starter starter;
    private Bird bird;
    private TubeSystem[] tubes;
    private ArrayList<AngryBird> angryBirds;
    private SmallBox smallBox;
    private SheildBox sheildBox;

    private PointCounter pointCounter;
    private PointCounter bestPointCounter;

    private Long allTime = 0L;
    private float elapsedAll = 0f;
    private boolean isGameOver = false, isAddedBird = true;
    private int point = 0, bestPoint = 0;

    public GameScreen(Starter starter) {
        this.starter = starter;
    }

    @Override
    public void show() {
        bird = new Bird(new Transform(100, 500));

        tubes = new TubeSystem[] {
            new TubeSystem((int) (TubeSystem.formulaCenterYTube()), 1),
            new TubeSystem((int) (TubeSystem.formulaCenterYTube()), 2),
            new TubeSystem((int) (TubeSystem.formulaCenterYTube()), 3),
        };

        angryBirds = new ArrayList<>(Arrays.asList(
            new AngryBird(AngryBird.formulaPosition(1))
        ));

        smallBox  = new SmallBox(bird);
        sheildBox = new SheildBox(bird);

        pointCounter = new PointCounter("Score: ", PointCounter.margins[0], Starter.screenSize.getHeight() - PointCounter.margins[1]);
        bestPointCounter = new PointCounter("Best: ", Starter.screenSize.getWidth() - 300, Starter.screenSize.getHeight() - PointCounter.margins[1]);
        if (starter.iGameServices != null) bestPoint = starter.iGameServices.getBestScore();

        starter.gameMusic.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        if (isGameOver) {
            starter.gameMusic.stop();

            starter.screenRestart = new ScreenRestart(starter, point);
            starter.setScreen(starter.screenRestart);
        }

        elapsedAll += delta;
        if (elapsedAll > 1) {
            elapsedAll--;
            allTime++;
            isAddedBird = false;
        }
        starter.background.setSpeed((int) (allTime / 2));

        if (allTime % 10 == 0 && !isAddedBird) {
            angryBirds.add(new AngryBird(AngryBird.formulaPosition(1)));
            isAddedBird = true;
        }

        // Обновление
        starter.background.move(delta);

        if (Gdx.input.justTouched()) bird.onClick();
        bird.move(delta);

        for (TubeSystem ts: tubes) {
            ts.update(delta);
            ts.setSpeed(allTime * 2);
            if (ts.needAddPoint(bird)) point++;
        }

        for (AngryBird ab: angryBirds) {
            ab.move(delta);
        }

        if (
            Arrays.stream(tubes).anyMatch(bird::checkCollision) || Arrays.stream(angryBirds.toArray()).anyMatch(bird::checkCollisionBird)
            || bird.getTransform().y < 5
            || bird.getTransform().y > Gdx.graphics.getHeight() - (72 + 10)
        ) {
            if (!bird.isNoHitFrame()) {
                if (bird.isActiveSheild()) {
                    bird.offSheild();
                    sheildBox.dropEffect();
                } else {
                    isGameOver = true;
                }
            }
        }

        if (bird.checkCollisionBox(smallBox) && !smallBox.isPickup()) smallBox.effect();
        if (bird.checkCollisionBox(sheildBox) && !sheildBox.isPickup()) sheildBox.effect();

        if (!smallBox.isPickup() && Math.random() < 0.001) {
            smallBox.dropBox();
        }
        if (!sheildBox.isPickup() && Math.random() < 0.001) {
            sheildBox.dropBox();
        }

        smallBox.move(delta);
        smallBox.update(delta);

        sheildBox.move(delta);

        starter.camera.update();
        starter.batch.setProjectionMatrix(starter.camera.combined);

        // Отрисовка
        starter.batch.begin();
        starter.background.draw(starter.batch, isGameOver);
        bird.draw(starter.batch);

        smallBox.draw(starter.batch);
        sheildBox.draw(starter.batch);
        for (AngryBird ab: angryBirds) {
            ab.draw(starter.batch);
        }
        for (TubeSystem ts: tubes) {
            ts.draw(starter.batch);
        }

        pointCounter.draw(starter.batch, point);
        bestPointCounter.draw(starter.batch, bestPoint);
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
        bird.dispose();
        pointCounter.dispose();

        for(TubeSystem ts: tubes) ts.dispose();
    }
}
