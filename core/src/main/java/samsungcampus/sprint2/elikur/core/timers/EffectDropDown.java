package samsungcampus.sprint2.elikur.core.timers;

import samsungcampus.sprint2.elikur.core.model.Box;

public class EffectDropDown {
    private Box box;

    private float elapsedTime = 0, time;
    public EffectDropDown(Box box, float time) {
        this.time = time;
        this.box = box;
    }

    public void check(float delta) {
        elapsedTime += delta;

        if (elapsedTime >= time) {
            box.dropEffect();
            elapsedTime = 0;
        }
    }

}
