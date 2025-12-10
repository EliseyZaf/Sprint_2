package samsungcampus.sprint2.elikur.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import samsungcampus.sprint2.elikur.Starter;
import samsungcampus.sprint2.elikur.core.addative.Transform;
import samsungcampus.sprint2.elikur.core.model.Entity;

public class TubeSystem {
    public static final boolean PROGRESSIVE_DIFFICULT_ENABLED = true;
    private Tube tubeUp, tubeDown;
    private int gapHeight = 300;
    private float baseSpeed = 300;
    private boolean wasAddedPoint = false;

    public TubeSystem(int centerY) {
        tubeUp   = new Tube(new Transform(Starter.screenSize.getWidth() + 500, centerY + (float) gapHeight / 2), true);
        tubeDown = new Tube(new Transform(Starter.screenSize.getWidth() + 500, centerY - (float) gapHeight / 2), false);
    }

    public TubeSystem(int centerY, int step) {
        tubeUp   = new Tube(new Transform(Starter.screenSize.getWidth() + 500 + (800 * step), centerY + (float) gapHeight / 2), true);
        tubeDown = new Tube(new Transform(Starter.screenSize.getWidth() + 500 + (800 * step), centerY - (float) gapHeight / 2), false);
    }

    public void update(float delta) {
        tubeDown.move(delta);
        tubeUp  .move(delta);

        if (tubeUp.getTransform().x <= -400) {
            if (PROGRESSIVE_DIFFICULT_ENABLED) gapHeight -= 2;
            replaceTube(TubeSystem.formulaCenterYTube());
        }
    }

    public void draw(SpriteBatch batch) {
        tubeUp  .draw(batch);
        tubeDown.draw(batch);
    }

    public void dispose() {
        tubeDown.dispose();
        tubeUp  .dispose();
    }

    public void replaceTube(float centerY) {
        tubeUp  .setTransform(new Transform(Starter.screenSize.getWidth() + 500, (int) (centerY + (float) gapHeight / 2)));
        tubeDown.setTransform(new Transform(Starter.screenSize.getWidth() + 500, (int) ((centerY - (float) gapHeight / 2) - 1148)));
        wasAddedPoint = false;
    }

    public void setSpeed(float speed) {
        speed += baseSpeed;
        tubeUp  .setSpeed(speed);
        tubeDown.setSpeed(speed);
    }

    public boolean needAddPoint(Bird bird) {
        if (bird.getTransform().x >= tubeDown.getTransform().x + tubeDown.getSize()[0] && ! wasAddedPoint) {
            wasAddedPoint = true;
            return true;
        }
        return false;
    }

    class Tube extends Entity {
        private TextureRegion flipped;

        public Tube(Transform transform, boolean flip) {
            super(transform, new Texture("tube/tube.png"), 200, 1148, 300);

            if (!flip) this.transform.y -= 1148;
            else flipped = new TextureRegion(texture);

            if (flipped != null) flipped.flip(false, true);

            float padding = 5;
            bound.x += padding;
            bound.y += padding;
            bound.width  -= padding * 2;
            bound.height -= padding * 2;
        }

        @Override
        public void draw(SpriteBatch batch) {
            if (flipped != null) batch.draw(flipped, transform.x, transform.y, size[0], size[1]);
            else super.draw(batch);
        }

        @Override
        public void move(float delta) {
            transform.x -= speed * delta;
            updateBound();
        }

        @Override
        public void dispose() {
            super.dispose();
        }
    }

    public Tube getTubeUp() {
        return tubeUp;
    }

    public Tube getTubeDown() {
        return tubeDown;
    }

    public static float formulaCenterYTube() {
        return (float) ((double) Starter.screenSize.getHeight() / 2 - (Math.random() * Starter.screenSize.getHeight() / 4));
    }
}


