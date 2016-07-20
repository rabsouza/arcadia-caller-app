package br.com.battista.arcadiacaller.model.enuns;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

import br.com.battista.arcadiacaller.R;
import lombok.Getter;

public enum TypeCardEnum {
    NONE(R.string.type_card_none),
    UPGRADE(R.string.type_card_upgrade),
    DEATH_CURSE(R.string.type_card_death_curse);

    private static final Map<String, TypeCardEnum> LOOK_UP = Maps.newHashMap();

    @Getter
    private int descRes;

    static {
        for (TypeCardEnum typeCard :
                TypeCardEnum.values()) {
            LOOK_UP.put(typeCard.name().toUpperCase(), typeCard);
        }
    }

    private TypeCardEnum(int descRes) {
        this.descRes = descRes;
    }

    public static TypeCardEnum get(String typeCard) {
        return LOOK_UP.get(MoreObjects.firstNonNull(typeCard, NONE.name()).toUpperCase());
    }

}
