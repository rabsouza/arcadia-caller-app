package br.com.battista.arcadiacaller.model.enuns;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

import br.com.battista.arcadiacaller.R;
import lombok.Getter;

public enum GroupHeroEnum {

    NONE(R.string.group_hero_none),
    BEYOND_THE_GRAVE_EXPANSION(R.string.group_hero_beyond_the_grave_expansion),
    PROMO_HEROES(R.string.group_hero_promo_heroes),
    CORE_BOX(R.string.group_hero_core_box),
    MONSTERS_AS_HEROES(R.string.group_hero_monsters_as_heroes),
    EXTRA(R.string.group_heroes_extra);

    private static final Map<String, GroupHeroEnum> LOOK_UP = Maps.newHashMap();

    static {
        for (GroupHeroEnum groupHero :
                GroupHeroEnum.values()) {
            LOOK_UP.put(groupHero.name().toUpperCase(), groupHero);
        }
    }

    @Getter
    private int descRes;

    GroupHeroEnum(int descRes) {
        this.descRes = descRes;
    }


    public static GroupHeroEnum get(String groupHero) {
        return LOOK_UP.get(MoreObjects.firstNonNull(groupHero, NONE.name()).toUpperCase());
    }

    public static GroupHeroEnum get(int ordenal) {
        for (GroupHeroEnum groupHero : GroupHeroEnum.values()) {
            if (groupHero.ordinal() == ordenal) {
                return groupHero;
            }
        }
        return GroupHeroEnum.NONE;
    }

}
