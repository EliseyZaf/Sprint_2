package samsungcampus.sprint2.elikur.core.addative;

import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class Animator {
    public List<Texture> textures;
    public float elapsedTime = 0;
    public float timeBetweenTiles;

    public Animator(List<Texture> textures, float time) {
        this.textures = textures;

        timeBetweenTiles = time / textures.size();
    }

    public Animator(List<Texture> textures) {
        this.textures = textures;

        timeBetweenTiles = (float) 1 / textures.size();
    }

    public Texture getState(float delta) {
        elapsedTime += delta;

        while (elapsedTime >= 1) elapsedTime -= 1;

        return textures.get((int) (elapsedTime / timeBetweenTiles));
    }
}
