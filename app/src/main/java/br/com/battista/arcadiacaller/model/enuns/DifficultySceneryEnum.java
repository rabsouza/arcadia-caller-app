package br.com.battista.arcadiacaller.model.enuns;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

import br.com.battista.arcadiacaller.R;
import lombok.Getter;

public enum DifficultySceneryEnum {
    NONE(R.string.difficulty_scenery_none),
    EASY(R.string.difficulty_scenery_easy),
    NORMAL(R.string.difficulty_scenery_normal),
    HARD(R.string.difficulty_scenery_hard);

    private static final Map<String, DifficultySceneryEnum> LOOK_UP = Maps.newHashMap();

    @Getter
    private int descRes;

    static {
        for (DifficultySceneryEnum groupCard :
                DifficultySceneryEnum.values()) {
            LOOK_UP.put(groupCard.name().toUpperCase(), groupCard);
        }
    }

    private DifficultySceneryEnum(int descRes) {
        this.descRes = descRes;
    }

    public static DifficultySceneryEnum get(String groupCard) {
        return LOOK_UP.get(MoreObjects.firstNonNull(groupCard, NONE.name()).toUpperCase());
    }

}
