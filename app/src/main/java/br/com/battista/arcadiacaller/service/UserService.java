package br.com.battista.arcadiacaller.service;


import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.text.MessageFormat;

import br.com.battista.arcadiacaller.constants.HttpStatus;
import br.com.battista.arcadiacaller.constants.RestConstant;
import br.com.battista.arcadiacaller.listener.UserListener;
import br.com.battista.arcadiacaller.model.User;
import retrofit2.Response;

public class UserService extends BaseService {

    public static final String TAG_CLASSNAME = UserService.class.getSimpleName();

    @Nullable
    public User findByUsername(@NonNull String token, @NonNull String username) {
        Log.i(TAG_CLASSNAME, MessageFormat.format("Find user by username: {0} in app server url:[{1}]!",
                username, RestConstant.REST_API_ENDPOINT));

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
            Log.e(TAG_CLASSNAME, e.getLocalizedMessage(), e);
        }

        return null;
    }

}
