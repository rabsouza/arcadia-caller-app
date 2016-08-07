package br.com.battista.arcadiacaller.repository;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.HeroEntry;

import android.util.Log;

import com.google.common.collect.Lists;
import com.orm.query.Select;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.model.BaseEntity;
import br.com.battista.arcadiacaller.model.Hero;
import br.com.battista.arcadiacaller.model.enuns.GroupHeroEnum;
import br.com.battista.arcadiacaller.repository.contract.DatabaseContract;

public class HeroRepository implements Repository<Hero> {

    public static final String TAG = HeroRepository.class.getSimpleName();

    @Override
    public void save(Hero entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Save to hero with id: {0}.", entity.getId()));
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
    public void saveAll(List<Hero> entities) {
        if (entities != null) {
            Log.i(TAG, MessageFormat.format("Save {0} heroes.", entities.size()));
            for (Hero entity : entities) {
                if (entity != null) {
                    Log.i(TAG, MessageFormat.format("Save to hero with id: {0}.", entity.getId()));
                    saveEntity(entity);
                }
            }
        } else {
            Log.w(TAG, "Entities can not be null!");
        }
    }

    @Override
    public Hero findById(Long id) {
        Log.i(TAG, MessageFormat.format("Find the hero by id: {0}.", id));
        return Hero.findById(Hero.class, id);
    }

    public List<Hero> findByGroup(GroupHeroEnum groupHero) {
        Log.i(TAG, MessageFormat.format("Find the hero by group: {0}.", groupHero));
        if (groupHero != null) {

            return Select
                    .from(Hero.class)
                    .where(MessageFormat.format("{0} = ?", DatabaseContract.HeroEntry.COLUMN_NAME_GROUP), new String[]{groupHero.name()})
                    .orderBy(MessageFormat.format("{0} ASC", HeroEntry.COLUMN_NAME_NAME))
                    .list();
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public void update(Hero entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Update the hero with id: {0}.", entity.getId()));
            saveEntity(entity);
        } else {
            Log.i(TAG, "Entity can not be null!");
        }
    }

    @Override
    public void deleteById(Long id) {
        Log.i(TAG, MessageFormat.format("Delete the hero with id: {0}.", id));
        Hero entity = findById(id);
        if (entity != null) {
            entity.delete();
        }
    }

    @Override
    public List<Hero> findAll() {
        Log.i(TAG, "Find all heroes.");
        return Select
                .from(Hero.class)
                .orderBy(MessageFormat.format("{0} ASC", HeroEntry.COLUMN_NAME_NAME))
                .list();
    }

    @Override
    public void deleteAll() {
        Log.i(TAG, "Delete all heroes.");
        Hero.deleteAll(Hero.class);
    }
}
