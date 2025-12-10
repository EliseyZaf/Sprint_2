package samsungcampus.sprint2.elikur.core.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PointCounter {
    public static final int[] margins = new int[] {50, 50}; // Right, Up
    private int x, y;
    private static BitmapFont font;
    private String string;

    static {
        font = new BitmapFont();
        font.getData().setScale(4f);
        font.setColor(Color.WHITE);
    }

    public PointCounter(String string, int x, int y) {
        this.x = x;
        this.y = y;
        this.string = string;
    }

    public void draw(SpriteBatch batch, int countOfPoints) {
        font.draw(batch, string + countOfPoints, x, y);
    }

    public void dispose() {
        font.dispose();
    }
}
