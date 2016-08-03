package br.com.battista.arcadiacaller.repository;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.util.Log;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.model.StatisticUser;

import static android.R.attr.id;
import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.StatisticUserEntry;

public class StatisticUserRepository implements BaseRepository<StatisticUser> {

    public static final String TAG = StatisticUserRepository.class.getSimpleName();

    @Override
    public void save(StatisticUser entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Save to statisticUser with id: {0}.", entity.getId()));
            ActiveAndroid.beginTransaction();
            try {
                entity.initEntity();
                entity.synchronize();
                entity.save();
                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }
        } else {
            Log.w(TAG, "Entity can not be null!");
        }
    }

    @Override
    public void saveAll(List<StatisticUser> entities) {
        if (entities != null) {
            Log.i(TAG, MessageFormat.format("Save {0} statisticUsers.", entities.size()));
            ActiveAndroid.beginTransaction();
            try {
                for (StatisticUser entity : entities) {
                    if (entity != null) {
                        Log.i(TAG, MessageFormat.format("Save to statisticUser with id: {0}.", entity.getId()));
                        entity.initEntity();
                        entity.synchronize();
                        entity.save();
                    }
                }
                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }
        } else {
            Log.w(TAG, "Entities can not be null!");
        }
    }

    @Override
    public StatisticUser findById(Long id) {
        Log.i(TAG, MessageFormat.format("Find the statisticUser by id: {0}.", id));
        return new Select()
                .from(StatisticUser.class)
                .where("id = ?", id)
                .executeSingle();
    }

    public StatisticUser findByUsername(String username) {
        Log.i(TAG, MessageFormat.format("Find the statisticUser by id: {0}.", id));
        return new Select()
                .from(StatisticUser.class)
                .where(StatisticUserEntry.COLUMN_NAME_USERNAME.concat(" = ?"), username)
                .executeSingle();
    }

    @Override
    public void update(StatisticUser entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Update the statisticUser with id: {0}.", entity.getId()));
            ActiveAndroid.beginTransaction();
            try {
                entity.updateEntity();
                entity.synchronize();
                entity.save();
                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }
        } else {
            Log.i(TAG, "Entity can not be null!");
        }
    }

    @Override
    public void deleteById(Long id) {
        Log.i(TAG, MessageFormat.format("Delete the statisticUser with id: {0}.", id));
        ActiveAndroid.beginTransaction();
        try {
            new Delete()
                    .from(StatisticUser.class)
                    .where("id = ?", id)
                    .execute();
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

    }

    @Override
    public List<StatisticUser> findAll() {
        Log.i(TAG, "Find all statisticUsers.");
        return new Select()
                .from(StatisticUser.class)
                .orderBy(MessageFormat.format("{0} ASC", StatisticUserEntry.COLUMN_NAME_USERNAME))
                .execute();
    }

    @Override
    public void deleteAll() {
        Log.i(TAG, "Delete all statisticUsers.");
        ActiveAndroid.beginTransaction();
        try {
            new Delete()
                    .from(StatisticUser.class)
                    .execute();
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

    }
}
