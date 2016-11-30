package br.com.battista.arcadiacaller.service;

import com.google.common.base.Strings;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.model.Card;
import br.com.battista.arcadiacaller.model.Hero;
import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.StatisticUser;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum;
import br.com.battista.arcadiacaller.repository.CardRepository;
import br.com.battista.arcadiacaller.repository.HeroRepository;
import br.com.battista.arcadiacaller.repository.SceneryRepository;
import br.com.battista.arcadiacaller.repository.StatisticUserRepository;

public class CacheManageService extends Service {

    public static final Boolean CACHED = Boolean.FALSE;
    private static final String TAG = CacheManageService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: Register event bus to Action!");
        EventBus.getDefault().register(this);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onActionCache(ActionCacheEnum action) {
        MainApplication instance = MainApplication.instance();
        final String token = instance.getToken();
        if (action != null && !Strings.isNullOrEmpty(token)) {
            Log.i(TAG, MessageFormat.format("onActionCache: Process to action: {0}.", action));
            if (ActionCacheEnum.LOAD_CARD_DATA.equals(action)) {
                loadAllDataCardAddToCache(token);
            } else if (ActionCacheEnum.LOAD_HERO_DATA.equals(action)) {
                loadAllDataHeroAddToCache(token);
            } else if (ActionCacheEnum.LOAD_SCENERY_DATA.equals(action)) {
                loadAllDataSceneryAddToCache(token);
            } else if (ActionCacheEnum.LOAD_STATISTIC_USER_DATA.equals(action)) {
                loadAllDataStatisticUserAddToCache(token);
            }
        } else {
            Log.i(TAG, MessageFormat.format("onActionCache: No process to action: {0}.", action));
        }

    }

    private void loadAllDataHeroAddToCache(String token) {
        Log.i(TAG, "loadAllDataHeroAddToCache: Find all in server!");
        List<Hero> heroes = Inject.provideHeroService(CACHED).findAll(token);

        final HeroRepository heroRepository = new HeroRepository();
        Log.i(TAG, "loadAllDataHeroAddToCache: Clear table Hero!");
        heroRepository.deleteAll();
        Log.i(TAG, "loadAllDataHeroAddToCache: Update table Hero!");
        heroRepository.saveAll(heroes);
    }

    private void loadAllDataCardAddToCache(String token) {
        Log.i(TAG, "loadAllDataCardAddToCache: Find all in server!");
        List<Card> cards = Inject.provideCardService(CACHED).findAll(token);

        final CardRepository cardRepository = new CardRepository();
        Log.i(TAG, "loadAllDataCardAddToCache: Clear table card!");
        cardRepository.deleteAll();
        Log.i(TAG, "loadAllDataCardAddToCache: Update table card!");
        cardRepository.saveAll(cards);
    }

    private void loadAllDataSceneryAddToCache(String token) {
        Log.i(TAG, "loadAllDataSceneryAddToCache: Find all in server!");
        List<Scenery> sceneries = Inject.provideSceneryService(CACHED).findAll(token);

        final SceneryRepository sceneryRepository = new SceneryRepository();
        Log.i(TAG, "loadAllDataSceneryAddToCache: Clear table scenery!");
        sceneryRepository.deleteAll();
        Log.i(TAG, "loadAllDataSceneryAddToCache: Update table scenery!");
        sceneryRepository.saveAll(sceneries);
    }

    private void loadAllDataStatisticUserAddToCache(String token) {
        User user = MainApplication.instance().getUser();
        Log.i(TAG, "loadAllDataStatisticUserAddToCache: Find all in server!");
        StatisticUser statisticUser = Inject.provideStatisticUserService(CACHED).findByUser(token, user.getUsername());

        final StatisticUserRepository statisticUserRepository = new StatisticUserRepository();
        Log.i(TAG, "loadAllDataStatisticUserAddToCache: Update table StatisticUser!");
        statisticUserRepository.save(statisticUser);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onCreate: Unregister event bus to Action!");
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
