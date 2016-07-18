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

import android.app.Application;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

import br.com.battista.arcadiacaller.adapter.FontsAdapter;
import br.com.battista.arcadiacaller.model.Card;
import br.com.battista.arcadiacaller.model.Guild;
import br.com.battista.arcadiacaller.model.Hero;
import br.com.battista.arcadiacaller.model.HeroGuild;
import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.service.AppService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import lombok.Getter;
import lombok.Setter;

public class MainApplication extends Application {

    private static final String TAG = MainApplication.class.getSimpleName();

    private static MainApplication instance = null;

    public static MainApplication getInstance() {
        return instance;
    }

    private AppService appService = Inject.provideAppService();

    @Getter
    @Setter
    private transient String token;

    @Getter
    @Setter
    private String user;

    @Setter
    @Getter
    private Boolean onlineServer = Boolean.FALSE;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: MainApplication!");

        initializeDB();
        initializeSystemFont();

        instance = this;
        checkOnlineServer();
    }

    private void checkOnlineServer() {
        if (AndroidUtils.isOnline(this)) {
            Log.i(TAG, "isOnline: Clean-up database!");
            deleteDatabase(DEFAULT_DATABASE_NAME);
        }

        appService.ping();
        appService.health();
    }

    protected void initializeDB() {
        Log.i(TAG, "initializeDB: Initializa Database to App.");

        Configuration.Builder configurationBuilder = new Configuration.Builder(this);
        configurationBuilder.addModelClasses(Card.class);
        configurationBuilder.addModelClasses(Guild.class);
        configurationBuilder.addModelClasses(Hero.class);
        configurationBuilder.addModelClasses(HeroGuild.class);
        configurationBuilder.addModelClasses(Scenery.class);
        configurationBuilder.addModelClasses(User.class);

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
