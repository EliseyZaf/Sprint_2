package samsungcampus.sprint2.elikur.core.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import samsungcampus.sprint2.elikur.Starter;
import samsungcampus.sprint2.elikur.core.addative.Transform;
import samsungcampus.sprint2.elikur.core.objects.Bird;
import samsungcampus.sprint2.elikur.core.timers.EffectDropDown;

public abstract class Box extends Entity {
    protected float boxGravity = 1.1f;
    protected boolean isGo = false, isPickup = false;
    protected Bird bird;
    protected EffectDropDown effectDropDown;
    public Box(Transform transform, Texture texture, Bird bird, int width, int height, float speed) {
        super(transform, texture, width, height, speed);
        this.bird = bird;
    }

    public abstract void effect();
    public abstract void dropEffect();

    public static Transform spawnPos() {
        return new Transform((float)
            ((double) (Starter.screenSize.getWidth() - Starter.screenSize.getWidth() / 3) + (Math.random() * Starter.screenSize.getWidth() / 2)),
            Starter.screenSize.getHeight() + 200);
    }

    public static float randomSpeed() {
        return (float) (100 + Math.random() * 100);
    }

    public void dropBox() {
        isGo = true;

        transform = Box.spawnPos();
        speed = Box.randomSpeed();
    }

    public boolean isPickup() {
        return isPickup;
    }
}
