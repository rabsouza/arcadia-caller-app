package br.com.battista.arcadiacaller.service;


import android.util.Log;

import java.text.MessageFormat;

import br.com.battista.arcadiacaller.constants.RestConstant;

public class FakeAppService extends AppService {

    public static final String TAG = FakeAppService.class.getSimpleName();

    public void ping() {
        Log.i(TAG, MessageFormat.format("[MOCK] Ping the app server url:[{0}]!",
                RestConstant.REST_API_ENDPOINT));
        Log.i(TAG, "[MOCK] Success to ping the app server!");
    }

    public void health() {
        Log.i(TAG, MessageFormat.format("[MOCK] Check health the app server url:[{0}]!",
                RestConstant.REST_API_ENDPOINT));
        Log.i(TAG, "[MOCK] App server is online!");
    }
}
