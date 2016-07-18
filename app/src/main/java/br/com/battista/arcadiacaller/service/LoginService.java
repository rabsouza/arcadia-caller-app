package br.com.battista.arcadiacaller.service;


import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

import br.com.battista.arcadiacaller.constants.HttpStatus;
import br.com.battista.arcadiacaller.constants.RestConstant;
import br.com.battista.arcadiacaller.listener.LoginListener;
import retrofit2.Response;

public class LoginService extends BaseService {

    public static final String TAG_CLASSNAME = LoginService.class.getSimpleName();
    public static final String LOGIN_TOKEN = "token";

    public String login(@NonNull String username) {
        Log.i(TAG_CLASSNAME, MessageFormat.format("Login username: {0} in app server url:[{1}]!",
                username, RestConstant.REST_API_ENDPOINT));

        String token = null;
        LoginListener listener = builder.create(LoginListener.class);
        try {
            Response<Map<String, String>> response = listener.login(username.trim()).execute();
            if (response != null && response.code() == HttpStatus.OK.value() && response.body() != null) {
                Log.i(TAG, "Success in login the username!");
                return response.body().get(LOGIN_TOKEN);
            } else {
                Log.e(TAG, "Failed in login the username!");
            }
        } catch (IOException e) {
            Log.e(TAG_CLASSNAME, e.getLocalizedMessage(), e);
        }

        return token;
    }

}
