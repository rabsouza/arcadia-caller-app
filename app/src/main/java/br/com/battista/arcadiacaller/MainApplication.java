package br.com.battista.arcadiacaller;

import static br.com.battista.arcadiacaller.constants.EntityConstant.DEFAULT_CACHE_SIZE;
import static br.com.battista.arcadiacaller.constants.EntityConstant.DEFAULT_DATABASE_NAME;
import static br.com.battista.arcadiacaller.constants.EntityConstant.DEFAULT_DATABASE_VERSION;
import static br.com.battista.arcadiacaller.constants.FontsConstant.DEFAULT;
import static br.com.battista.arcadiacaller.constants.FontsConstant.DEFAULT_FONT;
import static br.com.battista.arcadiacaller.constants.FontsConstant.MONOSPACE;
import static br.com.battista.arcadiacaller.constants.FontsConstant.SANS_SERIF;
import static br.com.battista.arcadiacaller.constants.FontsConstant.SANS_SERIF_FONT;
import static br.com.battista.arcadiacaller.constants.FontsConstant.SERIF;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.crashlytics.android.Crashlytics;

import br.com.battista.arcadiacaller.adapter.FontsAdapter;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.Card;
import br.com.battista.arcadiacaller.model.Guild;
import br.com.battista.arcadiacaller.model.Hero;
import br.com.battista.arcadiacaller.model.HeroGuild;
import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.SceneryCampaign;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.model.enuns.SharedPreferencesKeyEnum;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import io.fabric.sdk.android.Fabric;
import lombok.Getter;
import lombok.Setter;

public class MainApplication extends MultiDexApplication {

    private static final String TAG = MainApplication.class.getSimpleName();

    private static MainApplication instance = null;

    @Getter
    @Setter
    private transient String token;

    @Getter
    @Setter
    private User user;

    @Setter
    @Getter
    private Boolean onlineServer = Boolean.FALSE;

    private SharedPreferences preferences;

    public static MainApplication instance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Log.d(TAG, "onCreate: MainApplication!");

        initializeDB();
        initializeSystemFont();

        instance = this;
        checkOnlineServer();

        preferences = getSharedPreferences(getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
    }

    public String getPreferences(@NonNull SharedPreferencesKeyEnum key) {
        return getPreferences(key, null);
    }

    public String getPreferences(@NonNull SharedPreferencesKeyEnum key, String defaultValue) {
        return preferences.getString(key.name(), defaultValue);
    }

    public Boolean putPreferences(@NonNull SharedPreferencesKeyEnum key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key.name(), value);
        return editor.commit();
    }

    private void checkOnlineServer() {
        if (AndroidUtils.isOnline(this)) {
            Log.i(TAG, "isOnline: Clean-up database!");
            deleteDatabase(DEFAULT_DATABASE_NAME);
        }
    }

    protected void initializeDB() {
        Log.i(TAG, "initializeDB: Initialize Database to App.");

        Configuration.Builder configurationBuilder = new Configuration.Builder(this);
        configurationBuilder.addModelClasses(Campaign.class,
                Card.class,
                Guild.class,
                Hero.class,
                HeroGuild.class,
                Scenery.class,
                SceneryCampaign.class,
                User.class);

        configurationBuilder.setCacheSize(DEFAULT_CACHE_SIZE);
        configurationBuilder.setDatabaseName(DEFAULT_DATABASE_NAME);
        configurationBuilder.setDatabaseVersion(DEFAULT_DATABASE_VERSION);

        ActiveAndroid.initialize(configurationBuilder.create());
    }

    private void initializeSystemFont() {
        Log.d(TAG, "initializeSystemFont: Add custom fonts to App.");
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
