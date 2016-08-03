package br.com.battista.arcadiacaller.service.server;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.text.MessageFormat;

import br.com.battista.arcadiacaller.constants.HttpStatus;
import br.com.battista.arcadiacaller.constants.RestConstant;
import br.com.battista.arcadiacaller.listener.UserListener;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.service.BaseService;
import br.com.battista.arcadiacaller.service.UserService;
import retrofit2.Response;

import static br.com.battista.arcadiacaller.listener.UserListener.URI_FIND_USERNAME;

public class UserServiceFromServer extends BaseService implements UserService {

    public static final String TAG = UserServiceFromServer.class.getSimpleName();

    @Override
    @Nullable
    public User findByUsername(@NonNull String token, @NonNull String username) {
        Log.i(TAG, MessageFormat.format("Find user by username: {0} in app server url:[{1}]!",
                username, RestConstant.REST_API_ENDPOINT.concat(URI_FIND_USERNAME)));

        UserListener listener = builder.create(UserListener.class);
        try {
            Response<User> response = listener.findByUsername(token, username.trim()).execute();
            if (response != null && response.code() == HttpStatus.OK.value() && response.body() != null) {
                Log.i(TAG, "Found user by username!");
                return response.body();
            } else {
                String errorMessage = MessageFormat.format(
                        "Not found user by username! Return the code status: {0}.",
                        HttpStatus.valueOf(response.code()));
                validateErrorResponse(response, errorMessage);
            }
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }

        return null;
    }

    @Override
    public Boolean existsUsername(@NonNull String token, @NonNull String username) {
        Log.i(TAG, MessageFormat.format("Exists user by username: {0} in app server url:[{1}]!",
                username, RestConstant.REST_API_ENDPOINT.concat(URI_FIND_USERNAME)));

        UserListener listener = builder.create(UserListener.class);
        try {
            Response<Void> response = listener.existsUsername(token, username.trim()).execute();
            boolean existsUser = response != null && response.code() == HttpStatus.OK.value();
            if (existsUser) {
                Log.i(TAG, "existsUsername: Found user!");
            } else {
                Log.i(TAG, "existsUsername: Not found user!");
            }
            return existsUser;
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }

        return Boolean.FALSE;
    }

}
