package samsungcampus.sprint2.elikur.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import samsungcampus.sprint2.elikur.core.addative.Transform;
import samsungcampus.sprint2.elikur.core.model.Box;

public class SheildBox extends Box {
    public SheildBox(Bird bird) {
        super(Box.spawnPos(), new Texture("buffBox/sheildBox.png"), bird, 64, 64, Box.randomSpeed());
    }

    @Override
    public void effect() {
        bird.onSheild();
        isPickup = true;

        transform.y = -100;
    }

    @Override
    public void dropEffect() {
        isPickup = false;
    }

    @Override
    public void move(float delta) {
        if (isGo) {
            transform.x -= speed * delta * 2;
            transform.y -= speed * delta * boxGravity;

            if (transform.y <= -100) {
                transform = Box.spawnPos();
                speed = Box.randomSpeed();
                isGo = false;
            }

            updateBound();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (!isPickup) super.draw(batch);
    }
}
