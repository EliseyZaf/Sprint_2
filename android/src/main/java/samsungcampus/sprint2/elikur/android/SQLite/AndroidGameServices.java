package samsungcampus.sprint2.elikur.android.SQLite;

import samsungcampus.sprint2.elikur.core.android.IGameServices;

public class AndroidGameServices implements IGameServices {
    @Override
    public int getBestScore() {
        return Base.getBest();
    }

    @Override
    public void saveBestScore(int score) {
        Base.saveBest(score);
    }
}

