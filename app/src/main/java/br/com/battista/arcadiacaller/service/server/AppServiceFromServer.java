package br.com.battista.arcadiacaller.service.server;


import static br.com.battista.arcadiacaller.listener.AppListener.URI_HEALTH;
import static br.com.battista.arcadiacaller.listener.AppListener.URI_PING;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.constants.HttpStatus;
import br.com.battista.arcadiacaller.constants.RestConstant;
import br.com.battista.arcadiacaller.listener.AppListener;
import br.com.battista.arcadiacaller.service.AppService;
import br.com.battista.arcadiacaller.service.BaseService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppServiceFromServer extends BaseService implements AppService {

    private static final String TAG = AppServiceFromServer.class.getSimpleName();

    public static final String APP_SERVER_IS_OFFLINE = "App server is offline!";
    public static final String APP_SERVER_IS_ONLINE = "App server is online!";

    @Override
    public void ping() {
        Log.i(TAG, MessageFormat.format("Ping the app server url:[{0}]!",
                RestConstant.REST_API_ENDPOINT.concat(URI_PING)));
        AppListener listener = builder.create(AppListener.class);
        listener.ping().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                validationPingResponse(response);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Failed to ping the app server!!");
            }
        });
    }

    @Override
    public Boolean checkPing() {
        Log.i(TAG, MessageFormat.format("Ping the app server url:[{0}]!",
                RestConstant.REST_API_ENDPOINT.concat(URI_PING)));
        AppListener listener = builder.create(AppListener.class);
        try {
            Response<Void> response = listener.ping().execute();
            return validationPingResponse(response);
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return Boolean.FALSE;
    }

    @NonNull
    private Boolean validationPingResponse(Response<Void> response) {
        Boolean onlineServer = response != null && response.code() == HttpStatus.OK.value();
        if (onlineServer) {
            Log.i(TAG, "Success to ping the app server!");

        } else {
            Log.e(TAG, "Failed to ping the app server!");
        }
        return onlineServer;
    }

    @Override
    public void health() {
        Log.i(TAG, MessageFormat.format("Check health the app server url:[{0}]!",
                RestConstant.REST_API_ENDPOINT.concat(URI_HEALTH)));
        AppListener listener = builder.create(AppListener.class);
        listener.health().enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {

                Boolean statusCode = response != null && response.code() == HttpStatus.OK.value();
                if (statusCode && response.body() != null) {
                    String appStauts = String.valueOf(response.body().get(RestConstant.APP_STATUS));

                    if (RestConstant.APP_STATUS_UP.equalsIgnoreCase(appStauts)) {
                        Log.i(TAG, APP_SERVER_IS_ONLINE);
                        MainApplication.instance().setOnlineServer(Boolean.TRUE);

                    } else {
                        Log.i(TAG, APP_SERVER_IS_OFFLINE);
                        MainApplication.instance().setOnlineServer(Boolean.FALSE);
                    }
                } else {
                    Log.e(TAG, "Failed to check health the app server!");
                    Log.i(TAG, APP_SERVER_IS_OFFLINE);
                    MainApplication.instance().setOnlineServer(Boolean.FALSE);

                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                Log.e(TAG, "Failed to check health the app server!");
                Log.i(TAG, APP_SERVER_IS_OFFLINE);
                MainApplication.instance().setOnlineServer(Boolean.FALSE);
            }
        });
    }
}
