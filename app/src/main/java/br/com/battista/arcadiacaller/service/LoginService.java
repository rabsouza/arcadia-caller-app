package br.com.battista.arcadiacaller.service;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

import br.com.battista.arcadiacaller.constants.HttpStatus;
import br.com.battista.arcadiacaller.constants.RestConstant;
import br.com.battista.arcadiacaller.listener.LoginListener;
import br.com.battista.arcadiacaller.model.User;
import retrofit2.Response;

import static br.com.battista.arcadiacaller.listener.LoginListener.URI_CREATE;
import static br.com.battista.arcadiacaller.listener.LoginListener.URI_LOGIN;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class LoginService extends BaseService {

    public static final String TAG_CLASSNAME = LoginService.class.getSimpleName();
    public static final String LOGIN_TOKEN = "token";

    @Nullable
    public String login(@NonNull String username) {
        Log.i(TAG_CLASSNAME, MessageFormat.format("Login username: {0} in app server url:[{1}]!",
                username, RestConstant.REST_API_ENDPOINT.concat(URI_LOGIN)));

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

    @Nullable
    public User create(@NonNull User user) {
        Log.i(TAG_CLASSNAME, MessageFormat.format("Create user: {0} in app server url:[{1}]!",
                user, RestConstant.REST_API_ENDPOINT.concat(URI_CREATE)));

        LoginListener listener = builder.create(LoginListener.class);
        try {
            Response<User> response = listener.create(user).execute();

            if (response != null && response.code() == HttpStatus.CREATED.value() && response.body() != null) {
                Log.i(TAG, "Success in create the user!");
                return response.body();

            } else {
                String errorMessage = MessageFormat.format(
                        "Failed in create the user! Return the code status: {0}.",
                        HttpStatus.valueOf(response.code()));
                validateErrorResponse(response, errorMessage);
            }
        } catch (IOException e) {
            Log.e(TAG_CLASSNAME, e.getLocalizedMessage(), e);
        }

        return null;
    }

}
