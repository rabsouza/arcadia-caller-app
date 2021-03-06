package br.com.battista.arcadiacaller.repository.contract;

import android.provider.BaseColumns;

public class DatabaseContract {

    public DatabaseContract() {
    }

    public static abstract class BaseEntry implements BaseColumns {

        public static final String COLUMN_NAME_ID = "ID";
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
        public static final String COLUMN_NAME_FRIENDS_DTO = "FRIENDS_DTO";
    }

    public static abstract class CampaignEntry extends BaseEntry {

        public static final String TABLE_NAME = "CAMPAIGN";
        public static final String COLUMN_NAME_ALIAS = "ALIAS";
        public static final String COLUMN_NAME_WHEN = "WHEN";
        public static final String COLUMN_NAME_GUILD_01 = "GUILD_01";
        public static final String COLUMN_NAME_HEROES_GUILD_01 = "HEROES_GUILD_01";
        public static final String COLUMN_NAME_GUILD_02 = "GUILD_02";
        public static final String COLUMN_NAME_HEROES_GUILD_02 = "HEROES_GUILD_02";
        public static final String COLUMN_NAME_GUILD_03 = "GUILD_03";
        public static final String COLUMN_NAME_HEROES_GUILD_03 = "HEROES_GUILD_03";
        public static final String COLUMN_NAME_GUILD_04 = "GUILD_04";
        public static final String COLUMN_NAME_HEROES_GUILD_04 = "HEROES_GUILD_04";
        public static final String COLUMN_NAME_KEY = "KEY";
        public static final String COLUMN_NAME_SCENERY_01 = "SCENERY_01";
        public static final String COLUMN_NAME_SCENERY_02 = "SCENERY_02";
        public static final String COLUMN_NAME_SCENERY_03 = "SCENERY_03";
        public static final String COLUMN_NAME_SCENERY_04 = "SCENERY_04";
        public static final String COLUMN_NAME_SCENERY_05 = "SCENERY_05";
        public static final String COLUMN_NAME_SCENERY_06 = "SCENERY_06";
        public static final String COLUMN_NAME_CREATED = "CREATED";
        public static final String COLUMN_NAME_WINNER = "WINNER";
        public static final String COLUMN_NAME_WINNERS = "WINNERS";
        public static final String COLUMN_NAME_LEAST_DEATHS = "LEAST_DEATHS";
        public static final String COLUMN_NAME_MOST_COINS = "MOST_COINS";
        public static final String COLUMN_NAME_WON_REWARD = "WON_REWARD";
        public static final String COLUMN_NAME_WON_TITLE = "WON_TITLE";
        public static final String COLUMN_NAME_ACTIVE = "ACTIVE";
        public static final String COLUMN_NAME_COMPLETED = "COMPLETED";
        public static final String COLUMN_NAME_DELETED = "DELETED";
    }

    public static abstract class CardEntry extends BaseEntry {

        public static final String TABLE_NAME = "CARD";
        public static final String COLUMN_NAME_NAME = "NAME";
        public static final String COLUMN_NAME_KEY = "KEY";
        public static final String COLUMN_NAME_TYPE = "TYPE_CARD";
        public static final String COLUMN_NAME_GROUP = "GROUP_CARD";
        public static final String COLUMN_NAME_TYPE_EFFECT = "TYPE_EFFECT";
        public static final String COLUMN_NAME_GROUP_EFFECT = "GROUP_EFFECT";
        public static final String COLUMN_NAME_COST = "COST";
        public static final String COLUMN_NAME_ACTIVE = "ACTIVE";
        public static final String COLUMN_NAME_REVISE = "REVISE";
        public static final String COLUMN_NAME_DENOUNCE = "DENOUNCE";
        public static final String COLUMN_NAME_DELETED = "DELETED";

    }

    public static abstract class GuildEntry extends BaseEntry {

        public static final String TABLE_NAME = "GUILD";
        public static final String COLUMN_NAME_VICTORIES = "VICTORIES";
        public static final String COLUMN_NAME_DEFEATS = "DEFEATS";
        public static final String COLUMN_NAME_BENEFIT_TITLES = "BENEFIT_TITLES";
        public static final String COLUMN_NAME_REWARD_CARDS = "REWARD_CARDS";
        public static final String COLUMN_NAME_NAME = "NAME";
        public static final String COLUMN_NAME_USER = "USER";
        public static final String COLUMN_NAME_HERO_01 = "HERO_01";
        public static final String COLUMN_NAME_HERO_02 = "HERO_02";
        public static final String COLUMN_NAME_HERO_03 = "HERO_03";
        public static final String COLUMN_NAME_SAVED_MONEY = "SAVED_MONEY";
    }

    public static abstract class HeroEntry extends BaseEntry {

        public static final String TABLE_NAME = "HERO";
        public static final String COLUMN_NAME_NAME = "NAME";
        public static final String COLUMN_NAME_URL_PHOTO = "URL_PHOTO";
        public static final String COLUMN_NAME_DEFENSE = "DEFENSE";
        public static final String COLUMN_NAME_LIFE = "LIFE";
        public static final String COLUMN_NAME_ABILITY = "ABILITY";
        public static final String COLUMN_NAME_GROUP = "GROUP_HERO";
        public static final String COLUMN_NAME_ACTIVE = "ACTIVE";
        public static final String COLUMN_NAME_REVISE = "REVISE";
        public static final String COLUMN_NAME_DENOUNCE = "DENOUNCE";
        public static final String COLUMN_NAME_DELETED = "DELETED";
    }

    public static abstract class HeroGuildEntry extends BaseEntry {

        public static final String TABLE_NAME = "HERO_GUILD";
        public static final String COLUMN_NAME_HERO = "HERO";
        public static final String COLUMN_NAME_CARD_01 = "CARD_01";
        public static final String COLUMN_NAME_CARD_02 = "CARD_02";
        public static final String COLUMN_NAME_CARD_03 = "CARD_03";
        public static final String COLUMN_NAME_CARD_04 = "CARD_04";
        public static final String COLUMN_NAME_CURSE_CARD = "CURSE_CARD";
        public static final String COLUMN_NAME_COMMENTS = "COMMENTS";
        public static final String COLUMN_NAME_ACTIVE = "ACTIVE";
        public static final String COLUMN_NAME_DELETED = "DELETED";
    }

    public static abstract class SceneryEntry extends BaseEntry {

        public static final String TABLE_NAME = "SCENERY";
        public static final String COLUMN_NAME_NAME = "NAME";
        public static final String COLUMN_NAME_URL_SYMBOL = "URL_SYMBOL";
        public static final String COLUMN_NAME_WON_TITLE = "WON_TITLE";
        public static final String COLUMN_NAME_WON_REWARD = "WON_REWARD";
        public static final String COLUMN_NAME_KEY_WON_REWARD = "KEY_WON_REWARD";
        public static final String COLUMN_NAME_DIFFICULTY = "DIFFICULTY";
        public static final String COLUMN_NAME_BENEFIT_TITLES = "BENEFIT_TITLES";
        public static final String COLUMN_NAME_STR_BENEFIT_TITLES = "STR_BENEFIT_TITLES";
        public static final String COLUMN_NAME_LOCATION = "LOCATION";
        public static final String COLUMN_NAME_ACTIVE = "ACTIVE";
        public static final String COLUMN_NAME_REVISE = "REVISE";
        public static final String COLUMN_NAME_DENOUNCE = "DENOUNCE";
        public static final String COLUMN_NAME_DELETED = "DELETED";
    }

    public static abstract class SceneryCampaignEntry extends BaseEntry {

        public static final String TABLE_NAME = "SCENERY_CAMPAIGN";
        public static final String COLUMN_NAME_NAME = "NAME";
        public static final String COLUMN_NAME_SCENERY = "SCENERY";
        public static final String COLUMN_NAME_WINNER = "WINNER";
        public static final String COLUMN_NAME_LEAST_DEATHS = "LEAST_DEATHS";
        public static final String COLUMN_NAME_MOST_COINS = "MOST_COINS";
        public static final String COLUMN_NAME_WON_REWARD = "WON_REWARD";
        public static final String COLUMN_NAME_WON_TITLE = "WON_TITLE";
        public static final String COLUMN_NAME_ACTIVE = "ACTIVE";
        public static final String COLUMN_NAME_COMPLETED = "COMPLETED";
        public static final String COLUMN_NAME_DELETED = "DELETED";
    }

    public static abstract class StatisticUserEntry extends BaseEntry {

        public static final String TABLE_NAME = "STATISTIC_USER";
        public static final String COLUMN_NAME_USERNAME = "USERNAME";
        public static final String COLUMN_NAME_MAIL = "MAIL";
        public static final String COLUMN_NAME_URL_AVATAR = "URL_AVATAR";
        public static final String COLUMN_NAME_CAMPAIGNS = "CAMPAIGNS";
        public static final String COLUMN_NAME_COMPLETEDS = "COMPLETEDS";
        public static final String COLUMN_NAME_SCENERIES = "SCENERIES";
        public static final String COLUMN_NAME_GUILDS = "GUILDS";
        public static final String COLUMN_NAME_CAMPAIGN_WINNERS = "CAMPAIGN_WINNERS";
        public static final String COLUMN_NAME_CAMPAIGN_DEFEATS = "CAMPAIGN_DEFEATS";
        public static final String COLUMN_NAME_CAMPAIGN_MEDALS_WINNERS = "CAMPAIGN_MEDALS_WINNERS";
        public static final String COLUMN_NAME_CAMPAIGN_MEDALS_LEAST_DEATHS = "CAMPAIGN_MEDALS_LEAST_DEATHS";
        public static final String COLUMN_NAME_CAMPAIGN_MEDALS_MOST_COINS = "CAMPAIGN_MEDALS_MOST_COINS";
        public static final String COLUMN_NAME_CAMPAIGN_MEDALS_WON_REWARDS = "CAMPAIGN_MEDALS_WON_REWARDS";
        public static final String COLUMN_NAME_CAMPAIGN_MEDALS_WON_TITLES = "CAMPAIGN_MEDALS_WON_TITLES";
        public static final String COLUMN_NAME_SCENERY_WINNERS = "SCENERY_WINNERS";
        public static final String COLUMN_NAME_SCENERY_DEFEATS = "SCENERY_DEFEATS";
        public static final String COLUMN_NAME_SCENERY_LEAST_DEATHS = "SCENERY_LEAST_DEATHS";
        public static final String COLUMN_NAME_SCENERY_MOST_COINS = "SCENERY_MOST_COINS";
        public static final String COLUMN_NAME_SCENERY_WON_REWARDS = "SCENERY_WON_REWARDS";
        public static final String COLUMN_NAME_SCENERY_WON_TITLES = "SCENERY_WON_TITLES";

    }

}
