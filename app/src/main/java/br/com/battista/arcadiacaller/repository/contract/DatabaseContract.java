package br.com.battista.arcadiacaller.repository.contract;

import android.provider.BaseColumns;

public class DatabaseContract {

    public DatabaseContract() {
    }

    public static abstract class BaseEntry implements BaseColumns {

        public static final String COLUMN_NAME_CREATED_AT = "createdAt";
        public static final String COLUMN_NAME_UPDATED_AT = "updatedAt";
        public static final String COLUMN_NAME_VERSION = "version";
    }

    public static abstract class UserEntry extends BaseEntry {

        public static final String TABLE_NAME = "User";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_MAIL = "mail";
        public static final String COLUMN_NAME_URL_AVATAR = "urlAvatar";
        public static final String COLUMN_NAME_PROFILE = "profile";
        public static final String COLUMN_NAME_FRIENDS = "friends";
    }

    public static abstract class CampaignEntry extends BaseEntry {

        public static final String TABLE_NAME = "Campaign";
        public static final String COLUMN_NAME_KEY = "key";
    }

    public static abstract class CardEntry extends BaseEntry {

        public static final String TABLE_NAME = "Card";
        public static final String COLUMN_NAME_NAME = "name";
    }

    public static abstract class GuildEntry extends BaseEntry {

        public static final String TABLE_NAME = "Guild";
        public static final String COLUMN_NAME_NAME = "name";
    }

    public static abstract class HeroEntry extends BaseEntry {

        public static final String TABLE_NAME = "Hero";
        public static final String COLUMN_NAME_NAME = "name";
    }

    public static abstract class HeroGuildEntry extends BaseEntry {

        public static final String TABLE_NAME = "HeroGuild";
        public static final String COLUMN_NAME_HERO = "hero";
    }

    public static abstract class SceneryEntry extends BaseEntry {

        public static final String TABLE_NAME = "Scenery";
        public static final String COLUMN_NAME_NAME = "name";
    }

    public static abstract class SceneryCampaignEntry extends BaseEntry {

        public static final String TABLE_NAME = "SceneryCampaign";
        public static final String COLUMN_NAME_NAME = "name";
    }

}
