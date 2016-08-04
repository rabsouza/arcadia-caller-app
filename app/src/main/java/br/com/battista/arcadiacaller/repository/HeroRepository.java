package br.com.battista.arcadiacaller.repository;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.util.Log;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.model.Hero;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.HeroEntry;

public class HeroRepository implements BaseRepository<Hero> {

    public static final String TAG = HeroRepository.class.getSimpleName();

    @Override
    public void save(Hero entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Save to hero with id: {0}.", entity.getPk()));
            ActiveAndroid.beginTransaction();
            try {
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
    public void saveAll(List<Hero> entities) {
        if (entities != null) {
            Log.i(TAG, MessageFormat.format("Save {0} heroes.", entities.size()));
            ActiveAndroid.beginTransaction();
            try {
                for (Hero entity : entities) {
                    if (entity != null) {
                        Log.i(TAG, MessageFormat.format("Save to hero with id: {0}.", entity.getPk()));
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
    public Hero findById(Long id) {
        Log.i(TAG, MessageFormat.format("Find the hero by id: {0}.", id));
        return new Select()
                .from(Hero.class)
                .where("id = ?", id)
                .executeSingle();
    }

    @Override
    public void update(Hero entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Update the hero with id: {0}.", entity.getPk()));
            ActiveAndroid.beginTransaction();
            try {
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
        Log.i(TAG, MessageFormat.format("Delete the hero with id: {0}.", id));
        ActiveAndroid.beginTransaction();
        try {
            new Delete()
                    .from(Hero.class)
                    .where("id = ?", id)
                    .execute();
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

    }

    @Override
    public List<Hero> findAll() {
        Log.i(TAG, "Find all heroes.");
        return new Select()
                .from(Hero.class)
                .orderBy(MessageFormat.format("{0} ASC", HeroEntry.COLUMN_NAME_NAME))
                .execute();
    }

    @Override
    public void deleteAll() {
        Log.i(TAG, "Delete all heroes.");
        ActiveAndroid.beginTransaction();
        try {
            new Delete()
                    .from(Hero.class)
                    .execute();
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

    }
}
