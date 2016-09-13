package br.com.battista.arcadiacaller.repository;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.SceneryEntry;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.collect.Lists;
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
            Log.i(TAG, MessageFormat.format("Save to scenery with name: {0}.", entity.getName()));
            saveEntity(entity);
        } else {
            Log.w(TAG, "Entity can not be null!");
        }
    }

    private void saveEntity(Scenery entity) {
        entity.synchronize();
        if (entity.getBenefitTitles() == null) {
            entity.setBenefitTitles(Lists.newArrayList(new String[0]));
        } else {
            entity.createStrBenefitTitles();
        }

        Card wonReward = entity.getWonReward();
        if (wonReward != null) {
            Card card = cardRepository.findByKey(wonReward.getKey());
            if (card == null) {
                cardRepository.save(wonReward);
                card = wonReward;
            }
            entity.setWonReward(card);
            entity.setKeyWonReward(card.getKey());
            Log.d(TAG, MessageFormat.format("saveEntity: Set card wonReward with id: {0} and key: {1}.", wonReward.getId(), wonReward.getKey()));
        }
        entity.save();
    }


    private void loadWonReward(Scenery scenery) {
        String wonReward = scenery.getKeyWonReward();
        if (wonReward != null) {
            Card card = cardRepository.findByKey(wonReward);
            scenery.setWonReward(card);
            Log.d(TAG, MessageFormat.format("loadWonReward: Set card wonReward with id: {0} and key: {1}.", card.getId(), card.getKey()));
        }
    }

    @Override
    public void saveAll(List<Scenery> entities) {
        if (entities != null) {
            Log.i(TAG, MessageFormat.format("Save {0} sceneries.", entities.size()));
            for (Scenery entity : entities) {
                if (entity != null) {
                    Log.i(TAG, MessageFormat.format("Save to scenery with name: {0}.", entity.getName()));
                    saveEntity(entity);
                }
            }
        } else {
            Log.w(TAG, "Entities can not be null!");
        }
    }

    @Override
    public List<Scenery> findAll() {
        Log.i(TAG, "Find all sceneries.");
        final List<Scenery> sceneries = Select
                .from(Scenery.class)
                .orderBy(MessageFormat.format("{0} ASC, {1} ASC", SceneryEntry.COLUMN_NAME_LOCATION, SceneryEntry.COLUMN_NAME_NAME))
                .list();

        for (Scenery scenery : sceneries) {
            scenery.loadBenefitTitles();
            loadWonReward(scenery);
        }
        return sceneries;
    }

    @Override
    public void deleteAll() {
        Log.i(TAG, "Delete all sceneries.");
        Scenery
                .deleteAll(Scenery.class);
    }

    public List<Scenery> findByLocation(@NonNull LocationSceneryEnum location) {
        Log.i(TAG, MessageFormat.format("Find all sceneries by location: {0}.", location));
        final List<Scenery> sceneries = Select
                .from(Scenery.class)
                .where(MessageFormat.format("{0} = ?", SceneryEntry.COLUMN_NAME_LOCATION), new String[]{location.name()})
                .orderBy(MessageFormat.format("{0} ASC, {1} ASC", SceneryEntry.COLUMN_NAME_LOCATION, SceneryEntry.COLUMN_NAME_NAME))
                .list();
        for (Scenery scenery : sceneries) {
            loadWonReward(scenery);
        }
        return sceneries;
    }

}
