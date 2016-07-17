package br.com.battista.arcadiacaller.model.enuns;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

public enum GroupCardEnum {
    STARTING_EQUIPMENT,
    NONE,
    DEATH_CURSE,
    REWARD_CARDS,
    LEVEL_1,
    LEVEL_2,
    LEVEL_3,
    LEVEL_4,
    LEVEL_5;

    private static final Map<String, GroupCardEnum> LOOK_UP = Maps.newHashMap();

    static {
        for (GroupCardEnum groupCard :
                GroupCardEnum.values()) {
            LOOK_UP.put(groupCard.name().toUpperCase(), groupCard);
        }
    }

    public static GroupCardEnum get(String groupCard) {
        return LOOK_UP.get(MoreObjects.firstNonNull(groupCard, NONE.name()).toUpperCase());
    }

}
