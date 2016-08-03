package br.com.battista.arcadiacaller.cache;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.common.base.Strings;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.model.Card;
import br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum;
import br.com.battista.arcadiacaller.repository.CardRepository;

public class CacheManagerService extends Service {

    private static final String TAG = CacheManagerService.class.getSimpleName();
    public static final Boolean CACHED = Boolean.FALSE;

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
        final String token = MainApplication.instance().getToken();
        if (action != null && !Strings.isNullOrEmpty(token)) {
            Log.i(TAG, MessageFormat.format("onActionCache: Process to action: {0}.", action));
            if (ActionCacheEnum.LOAD_CARD_DATA.equals(action)) {
                loadAllDataCardAddToCache(token);
            }
        } else {
            Log.i(TAG, MessageFormat.format("onActionCache: No process to action: {0}.", action));
        }

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

    @Override
    public void onDestroy() {
        Log.i(TAG, "onCreate: Unregister event bus to Action!");
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
