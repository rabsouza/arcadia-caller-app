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
            Log.i(TAG, MessageFormat.format("Save to hero with name: {0}.", entity.getName()));
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
                    Log.i(TAG, MessageFormat.format("Save to hero with name: {0}.", entity.getName()));
                    saveEntity(entity);
                }
            }
        } else {
            Log.w(TAG, "Entities can not be null!");
        }
    }

    public List<Hero> findByGroup(GroupHeroEnum... groupHeroes) {
        List<GroupHeroEnum> groups = Lists.newArrayList(groupHeroes);

        Log.i(TAG, MessageFormat.format("Find the hero by groups: {0}.", groups));
        if (!groups.isEmpty()) {
            List<String> nameGroups = Lists.newArrayList();
            for (GroupHeroEnum group : groups) {
                nameGroups.add(group.name());
            }

            return Select
                    .from(Hero.class)
                    .where(MessageFormat.format("{0} IN( {1} )", DatabaseContract.HeroEntry.COLUMN_NAME_GROUP, makePlaceholders(nameGroups.size())), nameGroups.toArray(new String[0]))
                    .orderBy(MessageFormat.format("{0} ASC", HeroEntry.COLUMN_NAME_NAME))
                    .list();
        } else {
            return Lists.newArrayList();
        }
    }

    String makePlaceholders(int len) {
        if (len < 1) {
            throw new RuntimeException("No placeholders");
        } else {
            StringBuilder sb = new StringBuilder(len * 2 - 1);
            sb.append("?");
            for (int i = 1; i < len; i++) {
                sb.append(",?");
            }
            return sb.toString();
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
