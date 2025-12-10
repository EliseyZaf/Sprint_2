package samsungcampus.sprint2.elikur.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import samsungcampus.sprint2.elikur.core.addative.Transform;
import samsungcampus.sprint2.elikur.core.model.Box;
import samsungcampus.sprint2.elikur.core.timers.EffectDropDown;

public class SmallBox extends Box {

    public SmallBox(Bird bird) {
        super(Box.spawnPos(), new Texture("buffBox/smallBox.png"), bird, 64, 64, Box.randomSpeed());

        effectDropDown = new EffectDropDown(this, 10);
    }

    @Override
    public void effect() {
        bird.birdToSmall(2);
        isPickup = true;

        transform.y = -100;
    }

    @Override
    public void dropEffect() {
        bird.birdToBig();
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

    public void update(float delta) {
        if (isPickup) {
            effectDropDown.check(delta);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (!isPickup) super.draw(batch);
    }
}
