package br.com.battista.arcadiacaller.repository.contract;

import android.provider.BaseColumns;

public class DatabaseContract {

    public DatabaseContract() {
    }

    public static abstract class BaseEntry implements BaseColumns {

        public static final String COLUMN_NAME_CREATED_AT = "CREATED_AT";
        public static final String COLUMN_NAME_UPDATED_AT = "UPDATED_AT";
        public static final String COLUMN_NAME_VERSION = "VERSION";
        public static final String COLUMN_NAME_SYNCHRONIZED_AT = "SYNCHRONIZED_AT";
        public static final String COLUMN_NAME_ENTITY_SYNCHRONIZED = "ENTITY_SYNCHRONIZED";
    }

    public static abstract class UserEntry extends BaseEntry {

        public static final String TABLE_NAME = "USER";
        public static final String COLUMN_NAME_USERNAME = "USERNAME";
        public static final String COLUMN_NAME_MAIL = "MAIL";
        public static final String COLUMN_NAME_URL_AVATAR = "URL_AVATAR";
        public static final String COLUMN_NAME_PROFILE = "PROFILE";
        public static final String COLUMN_NAME_FRIENDS = "FRIENDS";
    }

    public static abstract class CampaignEntry extends BaseEntry {

        public static final String TABLE_NAME = "CAMPAIGN";
        public static final String COLUMN_NAME_KEY = "KEY";
    }

    public static abstract class CardEntry extends BaseEntry {

        public static final String TABLE_NAME = "CARD";
        public static final String COLUMN_NAME_NAME = "NAME";
        public static final String COLUMN_NAME_KEY = "KEY";
        public static final String COLUMN_NAME_TYPE = "TYPE_CARD";
        public static final String COLUMN_NAME_GROUP = "GROUP_CARD";
        public static final String COLUMN_NAME_COST = "COST";
        public static final String COLUMN_NAME_ACTIVE = "ACTIVE";
        public static final String COLUMN_NAME_REVISE = "REVISE";
        public static final String COLUMN_NAME_DENOUNCE = "DENOUNCE";
        public static final String COLUMN_NAME_DELETED = "DELETED";

    }

    public static abstract class GuildEntry extends BaseEntry {

        public static final String TABLE_NAME = "GUILD";
        public static final String COLUMN_NAME_NAME = "NAME";
    }

    public static abstract class HeroEntry extends BaseEntry {

        public static final String TABLE_NAME = "HERO";
        public static final String COLUMN_NAME_NAME = "NAME";
    }

    public static abstract class HeroGuildEntry extends BaseEntry {

        public static final String TABLE_NAME = "HERO_GUILD";
        public static final String COLUMN_NAME_HERO = "HERO";
    }

    public static abstract class SceneryEntry extends BaseEntry {

        public static final String TABLE_NAME = "SCENERY";
        public static final String COLUMN_NAME_NAME = "NAME";
        public static final String COLUMN_NAME_URL_SYMBOL = "URL_SYMBOL";
        public static final String COLUMN_NAME_WON_TITLE = "WON_TITLE";
        public static final String COLUMN_NAME_WON_REWARD = "WON_REWARD";
        public static final String COLUMN_NAME_DIFFICULTY = "DIFFICULTY";
        public static final String COLUMN_NAME_BENEFIT_TITLES = "BENEFIT_TITLES";
        public static final String COLUMN_NAME_LOCATION = "LOCATION";
        public static final String COLUMN_NAME_ACTIVE = "ACTIVE";
        public static final String COLUMN_NAME_REVISE = "REVISE";
        public static final String COLUMN_NAME_DENOUNCE = "DENOUNCE";
        public static final String COLUMN_NAME_DELETED = "DELETED";
    }

    public static abstract class SceneryCampaignEntry extends BaseEntry {

        public static final String TABLE_NAME = "SCENERY_CAMPAIGN";
        public static final String COLUMN_NAME_NAME = "NAME";
    }

}
