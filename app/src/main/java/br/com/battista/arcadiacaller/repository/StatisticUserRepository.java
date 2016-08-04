package br.com.battista.arcadiacaller.repository;

import android.util.Log;

import com.orm.query.Select;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.model.BaseEntity;
import br.com.battista.arcadiacaller.model.StatisticUser;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.StatisticUserEntry;

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

    private void saveEntity(BaseEntity entity) {
        entity.synchronize();
        entity.save();
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
    public StatisticUser findById(Long id) {
        Log.i(TAG, MessageFormat.format("Find the statisticUser by id: {0}.", id));
        return StatisticUser.findById(StatisticUser.class, id);
    }

    @Override
    public void update(StatisticUser entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Update to statisticUser to user: {0}.", entity.getUsername()));
            saveEntity(entity);
        } else {
            Log.i(TAG, "Entity can not be null!");
        }
    }

    @Override
    public void deleteById(Long id) {
        Log.i(TAG, MessageFormat.format("Delete the statisticUser with id: {0}.", id));
        StatisticUser entity = findById(id);
        if (entity != null) {
            entity.delete();
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
