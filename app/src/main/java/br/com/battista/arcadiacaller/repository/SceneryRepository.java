package br.com.battista.arcadiacaller.repository;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.SceneryEntry;

import android.support.annotation.NonNull;
import android.util.Log;

import com.orm.query.Select;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.model.Card;
import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;

public class SceneryRepository implements Repository<Scenery> {

    public static final String TAG = SceneryRepository.class.getSimpleName();

    private CardRepository cardRepository = new CardRepository();

    @Override
    public void save(Scenery entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Save to scenery with id: {0}.", entity.getId()));
            saveEntity(entity);
        } else {
            Log.w(TAG, "Entity can not be null!");
        }
    }

    private void saveEntity(Scenery entity) {
        entity.synchronize();
        Card wonReward = entity.getWonReward();
        if (wonReward != null) {
            Card card = cardRepository.findByKey(wonReward.getKey());
            if (card != null) {
                entity.setWonReward(card);
            } else {
                wonReward.synchronize();
                cardRepository.save(wonReward);
            }
        }
        entity.save();
    }

    @Override
    public void saveAll(List<Scenery> entities) {
        if (entities != null) {
            Log.i(TAG, MessageFormat.format("Save {0} sceneries.", entities.size()));
            for (Scenery entity : entities) {
                if (entity != null) {
                    Log.i(TAG, MessageFormat.format("Save to scenery with id: {0}.", entity.getId()));
                    saveEntity(entity);
                }
            }
        } else {
            Log.w(TAG, "Entities can not be null!");
        }
    }

    @Override
    public Scenery findById(Long id) {
        Log.i(TAG, MessageFormat.format("Find the scenery by id: {0}.", id));
        return Scenery.findById(Scenery.class, id);
    }

    @Override
    public void update(Scenery entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Update the scenery with id: {0}.", entity.getId()));
            saveEntity(entity);
        } else {
            Log.i(TAG, "Entity can not be null!");
        }
    }

    @Override
    public void deleteById(Long id) {
        Log.i(TAG, MessageFormat.format("Delete the scenery with id: {0}.", id));
        Scenery entity = findById(id);
        if (entity != null) {
            entity.delete();
        }
    }

    @Override
    public List<Scenery> findAll() {
        Log.i(TAG, "Find all sceneries.");
        return Select
                .from(Scenery.class)
                .orderBy(MessageFormat.format("{0} ASC, {1} ASC", SceneryEntry.COLUMN_NAME_LOCATION, SceneryEntry.COLUMN_NAME_NAME))
                .list();
    }

    @Override
    public void deleteAll() {
        Log.i(TAG, "Delete all sceneries.");
        Scenery
                .deleteAll(Scenery.class);
    }

    public List<Scenery> findByLocation(@NonNull LocationSceneryEnum location) {
        Log.i(TAG, MessageFormat.format("Find all sceneries by location: {0}.", location));
        return Select
                .from(Scenery.class)
                .where(MessageFormat.format("{0} = ?", SceneryEntry.COLUMN_NAME_LOCATION), new String[]{location.name()})
                .orderBy(MessageFormat.format("{0} ASC, {1} ASC", SceneryEntry.COLUMN_NAME_LOCATION, SceneryEntry.COLUMN_NAME_NAME))
                .list();
    }

}
