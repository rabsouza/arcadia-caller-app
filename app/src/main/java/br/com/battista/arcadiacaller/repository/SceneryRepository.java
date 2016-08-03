package br.com.battista.arcadiacaller.repository;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.SceneryEntry;

import android.support.annotation.NonNull;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.util.Log;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;

public class SceneryRepository implements BaseRepository<Scenery> {

    public static final String TAG = SceneryRepository.class.getSimpleName();

    @Override
    public void save(Scenery entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Save to scenery with id: {0}.", entity.getId()));
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
    public void saveAll(List<Scenery> entities) {
        if (entities != null) {
            Log.i(TAG, MessageFormat.format("Save {0} sceneries.", entities.size()));
            ActiveAndroid.beginTransaction();
            try {
                for (Scenery entity : entities) {
                    if (entity != null) {
                        Log.i(TAG, MessageFormat.format("Save to scenery with id: {0}.", entity.getId()));
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
    public Scenery findById(Long id) {
        Log.i(TAG, MessageFormat.format("Find the scenery by id: {0}.", id));
        return new Select()
                .from(Scenery.class)
                .where("id = ?", id)
                .executeSingle();
    }

    @Override
    public void update(Scenery entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Update the scenery with id: {0}.", entity.getId()));
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
        Log.i(TAG, MessageFormat.format("Delete the scenery with id: {0}.", id));
        ActiveAndroid.beginTransaction();
        try {
            new Delete()
                    .from(Scenery.class)
                    .where("id = ?", id)
                    .execute();
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

    }

    @Override
    public List<Scenery> findAll() {
        Log.i(TAG, "Find all sceneries.");
        return new Select()
                .from(Scenery.class)
                .orderBy(MessageFormat.format("{0} ASC, {1} ASC", SceneryEntry.COLUMN_NAME_LOCATION, SceneryEntry.COLUMN_NAME_NAME))
                .execute();
    }

    public List<Scenery> findByLocation(@NonNull LocationSceneryEnum location) {
        Log.i(TAG, MessageFormat.format("Find all sceneries by location: {0}.", location));
        return new Select()
                .from(Scenery.class)
                .where(SceneryEntry.COLUMN_NAME_LOCATION.concat(" = ?"), location)
                .orderBy(MessageFormat.format("{0} ASC, {1} ASC", SceneryEntry.COLUMN_NAME_LOCATION, SceneryEntry.COLUMN_NAME_NAME))
                .execute();
    }

    @Override
    public void deleteAll() {
        Log.i(TAG, "Delete all sceneries.");
        ActiveAndroid.beginTransaction();
        try {
            new Delete()
                    .from(Scenery.class)
                    .execute();
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

    }
}
