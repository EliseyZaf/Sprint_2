package samsungcampus.sprint2.elikur.core.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private Texture backgroundInGame = new Texture("background/game_bg.png");
    private Texture backgroundInMenu = new Texture("background/restart_bg.png");
    private float posX = 0, posX2;
    private float speed, startSpeed = 90;

    public Background() {
        posX2 = Gdx.graphics.getWidth();
    }

    public void setSpeed(int add) {
        speed = startSpeed + add;
    }

    public void move(float delta) {
        posX  -= speed * delta;
        posX2 -= speed * delta;

        if (posX <= -Gdx.graphics.getWidth())  posX  = Gdx.graphics.getWidth();
        if (posX2 <= -Gdx.graphics.getWidth()) posX2 = Gdx.graphics.getWidth();
    }

    public void draw(SpriteBatch batch, boolean isGameOver) {
        batch.draw(isGameOver ? backgroundInMenu : backgroundInGame, posX, 0, Gdx.graphics.getWidth() + 6, Gdx.graphics.getHeight());
        batch.draw(isGameOver ? backgroundInMenu : backgroundInGame, posX2, 0, Gdx.graphics.getWidth() + 6, Gdx.graphics.getHeight());
    }

    public void dispose() {
        backgroundInGame.dispose();
        backgroundInMenu.dispose();
    }
}
