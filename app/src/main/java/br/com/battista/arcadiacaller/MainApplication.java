package br.com.battista.arcadiacaller;

import android.app.Application;
import android.util.Log;

import br.com.battista.arcadiacaller.override.FontsOverride;

/**
 * Created by rabsouza on 18/06/16.
 */
public class MainApplication extends Application {

    private static final String TAG = MainApplication.class.getSimpleName();

    private static MainApplication instance = null;

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: MainApplication!");

        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/Litterbox-ICG-Regular.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Litterbox-ICG-Regular.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/Litterbox-ICG-Regular.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/Litterbox-ICG-Regular.ttf");

        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate: MainApplication!");
    }

}
