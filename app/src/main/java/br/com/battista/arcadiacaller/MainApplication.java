package br.com.battista.arcadiacaller;

import static br.com.battista.arcadiacaller.constants.FontsConstant.DEFAULT;
import static br.com.battista.arcadiacaller.constants.FontsConstant.DEFAULT_FONT;
import static br.com.battista.arcadiacaller.constants.FontsConstant.MONOSPACE;
import static br.com.battista.arcadiacaller.constants.FontsConstant.SANS_SERIF;
import static br.com.battista.arcadiacaller.constants.FontsConstant.SANS_SERIF_FONT;
import static br.com.battista.arcadiacaller.constants.FontsConstant.SERIF;

import android.app.Application;
import android.util.Log;

import br.com.battista.arcadiacaller.adapter.FontsAdapter;

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

        changeSystemFont();

        instance = this;
    }

    private void changeSystemFont() {
        FontsAdapter.setDefaultFont(this, DEFAULT, DEFAULT_FONT);
        FontsAdapter.setDefaultFont(this, MONOSPACE, DEFAULT_FONT);
        FontsAdapter.setDefaultFont(this, SERIF, DEFAULT_FONT);
        FontsAdapter.setDefaultFont(this, SANS_SERIF, SANS_SERIF_FONT);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate: MainApplication!");
    }

}
