package br.com.battista.arcadiacaller.service.database;

import android.support.annotation.NonNull;
import android.util.Log;

import java.text.MessageFormat;

import br.com.battista.arcadiacaller.model.StatisticUser;
import br.com.battista.arcadiacaller.repository.StatisticUserRepository;
import br.com.battista.arcadiacaller.service.StatisticUserService;

public class StatisticUserServiceFromDatabase implements StatisticUserService {

    private static final String TAG = StatisticUserServiceFromDatabase.class.getSimpleName();

    private final StatisticUserRepository statisticUserRepository;

    public StatisticUserServiceFromDatabase() {
        statisticUserRepository = new StatisticUserRepository();
    }

    @NonNull
    @Override
    public StatisticUser findByUser(@NonNull String token, @NonNull String username) {
        Log.i(TAG, MessageFormat.format("Find statisticUsers by username: {0} in database!", username));

        return statisticUserRepository.findByUsername(username);
    }
}
