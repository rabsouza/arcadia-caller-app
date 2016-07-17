package br.com.battista.arcadiacaller.model.enuns;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

import lombok.Getter;

public enum NameGuildEnum {
    ORANGE("Lion"),
    BLUE("Eagle"),
    RED("Fox"),
    GREEN("Panda");

    private static final Map<String, NameGuildEnum> LOOK_UP = Maps.newHashMap();

    static {
        for (NameGuildEnum nameGuild :
                NameGuildEnum.values()) {

            LOOK_UP.put(nameGuild.name().toUpperCase(), nameGuild);
        }
    }

    @Getter
    private final String name;

    NameGuildEnum(String name) {
        this.name = name;
    }

    public static NameGuildEnum get(String nameGuild) {
        return LOOK_UP.get(MoreObjects.firstNonNull(nameGuild, "").toUpperCase());
    }

}
