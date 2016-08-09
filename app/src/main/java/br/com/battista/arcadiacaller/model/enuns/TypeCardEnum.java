package br.com.battista.arcadiacaller.model.enuns;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

import br.com.battista.arcadiacaller.R;

public enum TypeCardEnum {
    NONE(R.string.type_card_none),
    UPGRADE(R.string.type_card_upgrade),
    DEATH_CURSE(R.string.type_card_death_curse);

    private static final Map<String, TypeCardEnum> LOOK_UP = Maps.newHashMap();

    static {
        for (TypeCardEnum typeCard :
                TypeCardEnum.values()) {
            LOOK_UP.put(typeCard.name().toUpperCase(), typeCard);
        }
    }

    private int descRes;

    TypeCardEnum(int descRes) {
        this.descRes = descRes;
    }

    public static TypeCardEnum get(String typeCard) {
        return LOOK_UP.get(MoreObjects.firstNonNull(typeCard, NONE.name()).toUpperCase());
    }

    public int getDescRes() {
        return descRes;
    }
}
