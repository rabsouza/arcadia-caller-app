package br.com.battista.arcadiacaller.model.enuns;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

import br.com.battista.arcadiacaller.R;

public enum NameGuildEnum {
    ORANGE(R.string.name_guild_orange, "https://storage.googleapis.com/arcadia-quest-storage/guilds/guild_orange.png"),
    BLUE(R.string.name_guild_blue, "https://storage.googleapis.com/arcadia-quest-storage/guilds/guild_blue.png"),
    RED(R.string.name_guild_red, "https://storage.googleapis.com/arcadia-quest-storage/guilds/guild_red.png"),
    GREEN(R.string.name_guild_green, "https://storage.googleapis.com/arcadia-quest-storage/guilds/guild_green.png");

    private static final Map<String, NameGuildEnum> LOOK_UP = Maps.newHashMap();

    static {
        for (NameGuildEnum resIdGuild :
                NameGuildEnum.values()) {

            LOOK_UP.put(resIdGuild.name().toUpperCase(), resIdGuild);
        }
    }

    private final int resId;

    private final String urlImg;

    NameGuildEnum(int resId, String urlImg) {
        this.resId = resId;
        this.urlImg = urlImg;
    }

    public static NameGuildEnum get(String name) {
        return LOOK_UP.get(MoreObjects.firstNonNull(name, BLUE.name()).toUpperCase());
    }

    public int getResId() {
        return resId;
    }

    public String getUrlImg() {
        return urlImg;
    }
}
