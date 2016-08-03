package br.com.battista.arcadiacaller.cache;

import android.support.annotation.NonNull;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum;

public class EventCache {

    private static final String TAG = EventCache.class.getSimpleName();

    public synchronized static void createEvent(@NonNull ActionCacheEnum actionCache) {
        Log.i(TAG, "createEvent: Create new event cache!");
        EventBus.getDefault().post(actionCache);
    }
}
