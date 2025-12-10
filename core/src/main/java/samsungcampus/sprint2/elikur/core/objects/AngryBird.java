package samsungcampus.sprint2.elikur.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;

import samsungcampus.sprint2.elikur.core.addative.Animator;
import samsungcampus.sprint2.elikur.core.addative.Transform;
import samsungcampus.sprint2.elikur.core.model.Entity;

public class AngryBird extends Entity {
    private Animator animator;
    private boolean isJumping = false, isUp = false;
    private float jumpHeight;

    public AngryBird(Transform transform) {
        super(transform, new Texture("bird/bird0.png"), 70, 58, (float) (200 + Math.random() * 400));

        animator = new Animator(new ArrayList<>(Arrays.asList(
            new Texture("bird/angryBird0.png"),
            new Texture("bird/angryBird1.png"),
            new Texture("bird/angryBird2.png"),
            new Texture("bird/angryBird1.png")
        )));

        float padding = 3;
        bound.x      += padding;
        bound.y      += padding;
        bound.width  -= padding * 2;
        bound.height -= padding * 2;
    }

    void fly(float delta) {
        transform.x -= speed * delta;

        if (transform.x <= -100) {
            transform.x = Gdx.graphics.getWidth() + 400;
            speed = (float) (200 + Math.random() * 500);
        }

        if (Math.random() < 0.005 && !isJumping) {
            isJumping = true;

            if (Math.random() <= 0.5) isJumping = false;
            else isJumping = true;

            if (isUp) jumpHeight = transform.y + 150;
            else jumpHeight = transform.y - 150;
        }

        if (isJumping) {
            if (isUp) transform.y += speed * delta;
            else transform.y -= speed * delta;

            if (isUp && transform.y >= jumpHeight) isJumping = false;
            if (!isUp && transform.y <= jumpHeight) isJumping = false;
        }
    }

    @Override
    public void move(float delta) {
        texture = animator.getState(delta);
        fly(delta);

        updateBound();
    }

    public static Transform formulaPosition(int step) {
        return new Transform((float) (Gdx.graphics.getWidth() + (400 * step)), (float) (Math.random() * Gdx.graphics.getHeight()));
    }
}
