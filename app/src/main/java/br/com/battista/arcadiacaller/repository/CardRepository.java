package br.com.battista.arcadiacaller.repository;


import android.util.Log;

import com.orm.query.Select;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.model.BaseEntity;
import br.com.battista.arcadiacaller.model.Card;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.CardEntry;

public class CardRepository implements Repository<Card> {

    public static final String TAG = CardRepository.class.getSimpleName();

    @Override
    public void save(Card entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Save to card with id: {0}.", entity.getId()));
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
    public void saveAll(List<Card> entities) {
        if (entities != null) {
            Log.i(TAG, MessageFormat.format("Save {0} cards.", entities.size()));
            for (Card entity : entities) {
                if (entity != null) {
                    Log.i(TAG, MessageFormat.format("Save to card with id: {0}.", entity.getId()));
                    saveEntity(entity);
                }
            }
        } else {
            Log.w(TAG, "Entities can not be null!");
        }
    }

    @Override
    public Card findById(Long id) {
        Log.i(TAG, MessageFormat.format("Find the card by id: {0}.", id));
        return Card.findById(Card.class, id);
    }


    public Card findByKey(String key) {
        Log.i(TAG, MessageFormat.format("Find the card by key: {0}.", key));
        return Select
                .from(Card.class)
                .where(MessageFormat.format("{0} = ?", CardEntry.COLUMN_NAME_KEY), new String[]{key})
                .first();
    }

    @Override
    public void update(Card entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Update the card with id: {0}.", entity.getId()));
            saveEntity(entity);
        } else {
            Log.i(TAG, "Entity can not be null!");
        }
    }

    @Override
    public void deleteById(Long id) {
        Log.i(TAG, MessageFormat.format("Delete the card with id: {0}.", id));
        Card entity = findById(id);
        if (entity != null) {
            entity.delete();
        }
    }

    @Override
    public List<Card> findAll() {
        Log.i(TAG, "Find all cards.");
        return Select
                .from(Card.class)
                .orderBy(MessageFormat.format("{0} ASC, {1} ASC", CardEntry.COLUMN_NAME_GROUP, CardEntry.COLUMN_NAME_NAME))
                .list();
    }

    @Override
    public void deleteAll() {
        Log.i(TAG, "Delete all cards.");
        Card.deleteAll(Card.class);
    }
}
