package samsungcampus.sprint2.elikur.core.model;

import com.badlogic.gdx.graphics.Texture;

import samsungcampus.sprint2.elikur.core.addative.Transform;

public abstract class Entity extends GameObject {
    protected float speed;
    protected float baseSpeed;

    public Entity(Transform transform, Texture texture, int width, int height, float speed) {
        super(transform, texture, width, height);
        this.speed = speed;
        this.baseSpeed = speed;
    }

    public abstract void move(float delta);

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public float getBaseSpeed() {
        return baseSpeed;
    }
}
