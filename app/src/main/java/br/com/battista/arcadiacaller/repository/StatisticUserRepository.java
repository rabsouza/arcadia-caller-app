package br.com.battista.arcadiacaller.repository;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.StatisticUserEntry;

import android.util.Log;

import com.orm.query.Select;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.model.StatisticUser;

public class StatisticUserRepository implements Repository<StatisticUser> {

    public static final String TAG = StatisticUserRepository.class.getSimpleName();

    @Override
    public void save(StatisticUser entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Save to statisticUser to user: {0}.", entity.getUsername()));
            saveEntity(entity);
        } else {
            Log.w(TAG, "Entity can not be null!");
        }
    }

    private void saveEntity(StatisticUser entity) {
        entity.synchronize();

        StatisticUser statisticUser = findByUsername(entity.getUsername());
        if (statisticUser != null && statisticUser.getId() != null) {
            entity.setId(statisticUser.getId());
            entity.update();
        } else {
            entity.save();
        }
    }

    @Override
    public void saveAll(List<StatisticUser> entities) {
        if (entities != null) {
            Log.i(TAG, MessageFormat.format("Save {0} statisticUsers.", entities.size()));
            for (StatisticUser entity : entities) {
                if (entity != null) {
                    Log.i(TAG, MessageFormat.format("Save to statisticUser to user: {0}.", entity.getUsername()));
                    saveEntity(entity);
                }
            }
        } else {
            Log.w(TAG, "Entities can not be null!");
        }
    }

    @Override
    public List<StatisticUser> findAll() {
        Log.i(TAG, "Find all statisticUsers.");
        return Select
                .from(StatisticUser.class)
                .list();
    }

    @Override
    public void deleteAll() {
        Log.i(TAG, "Delete all statisticUsers.");
        StatisticUser.deleteAll(StatisticUser.class);
    }

    public StatisticUser findByUsername(String username) {
        Log.i(TAG, MessageFormat.format("Find the statisticUser by username: {0}.", username));

        return Select
                .from(StatisticUser.class)
                .where(MessageFormat.format("{0} = ?", StatisticUserEntry.COLUMN_NAME_USERNAME), new String[]{username})
                .first();
    }

}
