package samsungcampus.sprint2.elikur.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;

import samsungcampus.sprint2.elikur.core.addative.Animator;
import samsungcampus.sprint2.elikur.core.addative.Transform;
import samsungcampus.sprint2.elikur.core.model.Box;
import samsungcampus.sprint2.elikur.core.model.Entity;

public class Bird extends Entity {
    private int jumpHeight;
    private final int maxHeightOfJump = 180;
    private boolean jump, isSmall = false, activeSheild, noHitFrame = false;
    private Animator animator;
    public float gravity = 1.05f, timeDrop = 0, speedDrop = 0, padding = 12;
    private int[] oldSize;
    private Texture sheild;
    private float elapsedTime = 0, time = 0.75f;

    public Bird(Transform transform) {
        super(transform, new Texture("bird/bird0.png"), 97, 72, 220);

        animator = new Animator(new ArrayList<>(Arrays.asList(
            new Texture("bird/bird0.png"),
            new Texture("bird/bird1.png"),
            new Texture("bird/bird2.png"),
            new Texture("bird/bird1.png")
        )));

        sheild = new Texture("buffBox/sheild.png");

        bound.x      += padding;
        bound.y      += padding;
        bound.width  -= padding * 2;
        bound.height -= padding * 2;

        oldSize = new int[] {size[0], size[1]};
    }

    public void onClick() {
        jump = true;

        if (!isSmall) {
            speedDrop = speed / 30;
            timeDrop = 0;
        } else {
            speedDrop = speed / 50;
            timeDrop = 0;
        }
    }

    void fly(float delta) {
        timeDrop += delta;
        speedDrop -= gravity * timeDrop;
        transform.y += speedDrop;
    }

    @Override
    public void move(float delta) {
        texture = animator.getState(delta);
        fly(delta);

        if (noHitFrame) {
            elapsedTime += delta;
            if (elapsedTime >= time) noHitFrame = false;
        }

        updateBound();
    }

    public boolean checkCollision(TubeSystem bound) {
        return this.bound.overlaps(bound.getTubeUp().getBound()) || this.bound.overlaps(bound.getTubeDown().getBound());
    }

    public boolean checkCollisionBird(Object bound) {
        return this.bound.overlaps(((AngryBird) bound).getBound());
    }

    public boolean checkCollisionBox(Box bound) {
        return this.bound.overlaps(bound.getBound());
    }

    public void birdToSmall(int scale) {
        size[0] /= scale;
        size[1] /= scale;

        bound.width  = size[0] - padding * 2;
        bound.height = size[1] - padding * 2;

        isSmall = true;
    }

    public void birdToBig() {
        size[0] = oldSize[0];
        size[1] = oldSize[1];

        bound.width  = oldSize[0] - padding * 2;
        bound.height = oldSize[1] - padding * 2;

        isSmall = false;
    }

    public void onSheild() {
        activeSheild = true;
    }

    public void offSheild() {
        activeSheild = false;
        noHitFrame = true;

        elapsedTime = 0;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(activeSheild) batch.draw(sheild, transform.x, transform.y - 5 - (size[1] / 5), size[0] + 5, size[0] + 5);
    }

    public boolean isActiveSheild() {
        return activeSheild;
    }

    public boolean isNoHitFrame() {
        return noHitFrame;
    }
}
