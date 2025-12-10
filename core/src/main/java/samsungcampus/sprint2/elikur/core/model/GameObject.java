package samsungcampus.sprint2.elikur.core.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import samsungcampus.sprint2.elikur.core.addative.Transform;

public abstract class GameObject {
    protected Transform transform;
    protected Texture texture;
    protected Rectangle bound;
    protected int[] size;

    public GameObject(Transform transform, Texture texture, int width, int height) {
        this.transform = transform;
        this.texture = texture;

        size = new int[] {width, height};
        bound = new Rectangle(transform.x, transform.y, width, height);
    }

    public Texture getTexture() {
        return texture;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public void updateBound() {
        bound.x = transform.x;
        bound.y = transform.y;
    }

    public void resizeBound(int width, int height) {
        bound.width = width;
        bound.height = height;
    }

    public Rectangle getBound() {
        return bound;
    }

    public int[] getSize() {
        return size;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, transform.x, transform.y, size[0], size[1]);
    }

    public void dispose() {
        texture.dispose();
    }
}
