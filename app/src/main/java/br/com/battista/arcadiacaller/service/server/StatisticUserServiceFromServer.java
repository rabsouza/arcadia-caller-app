package br.com.battista.arcadiacaller.service.server;


import static br.com.battista.arcadiacaller.listener.StatisticUserListener.URI_FIND_BY_USER;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.text.MessageFormat;

import br.com.battista.arcadiacaller.constants.HttpStatus;
import br.com.battista.arcadiacaller.constants.RestConstant;
import br.com.battista.arcadiacaller.listener.StatisticUserListener;
import br.com.battista.arcadiacaller.model.StatisticUser;
import br.com.battista.arcadiacaller.service.BaseService;
import br.com.battista.arcadiacaller.service.StatisticUserService;
import retrofit2.Response;

public class StatisticUserServiceFromServer extends BaseService implements StatisticUserService {

    public static final String TAG = StatisticUserServiceFromServer.class.getSimpleName();

    @Override
    public StatisticUser findByUser(@NonNull String token, @NonNull String username) {
        Log.i(TAG, MessageFormat.format("Find Statistic User by user:{0} in app server url:[{1}]!",
                username, RestConstant.REST_API_ENDPOINT.concat(URI_FIND_BY_USER)));

        StatisticUserListener listener = builder.create(StatisticUserListener.class);
        StatisticUser statisticUser = new StatisticUser();
        try {
            Response<StatisticUser> response = listener.findByUser(token, username).execute();
            if (response != null && response.code() == HttpStatus.NOT_FOUND.value()) {
                statisticUser.initializeStatistic();
                Log.i(TAG, "Found 0 Statistic User!");
            } else if (response != null && response.code() == HttpStatus.OK.value() && response.body() != null) {
                statisticUser = response.body();
                Log.i(TAG, MessageFormat.format("Found {0} Statistic User!", statisticUser));
                return statisticUser;
            } else {
                String errorMessage = MessageFormat.format(
                        "Error in find Statistic User! Return the code status: {0}.",
                        HttpStatus.valueOf(response.code()));
                validateErrorResponse(response, errorMessage);
            }
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }

        return statisticUser;
    }

}
