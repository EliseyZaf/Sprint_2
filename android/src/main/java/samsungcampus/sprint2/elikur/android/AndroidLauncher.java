package samsungcampus.sprint2.elikur.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import samsungcampus.sprint2.elikur.Starter;
import samsungcampus.sprint2.elikur.android.SQLite.AndroidGameServices;
import samsungcampus.sprint2.elikur.android.SQLite.Base;
import samsungcampus.sprint2.elikur.core.android.IGameServices;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.useImmersiveMode = true;

        Base.init(this);
        IGameServices gameServices = new AndroidGameServices();

        initialize(new Starter(gameServices), configuration);
    }
}
