package br.com.battista.arcadiacaller.model.enuns;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

import br.com.battista.arcadiacaller.R;

public enum DifficultySceneryEnum {
    NONE(R.string.difficulty_scenery_none),
    EASY(R.string.difficulty_scenery_easy),
    NORMAL(R.string.difficulty_scenery_normal),
    HARD(R.string.difficulty_scenery_hard);

    private static final Map<String, DifficultySceneryEnum> LOOK_UP = Maps.newHashMap();

    static {
        for (DifficultySceneryEnum groupCard :
                DifficultySceneryEnum.values()) {
            LOOK_UP.put(groupCard.name().toUpperCase(), groupCard);
        }
    }

    private int descRes;

    DifficultySceneryEnum(int descRes) {
        this.descRes = descRes;
    }

    public static DifficultySceneryEnum get(String groupCard) {
        return LOOK_UP.get(MoreObjects.firstNonNull(groupCard, NONE.name()).toUpperCase());
    }

    public int getDescRes() {
        return descRes;
    }
}
