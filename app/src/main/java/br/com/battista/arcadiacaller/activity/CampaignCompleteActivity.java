package br.com.battista.arcadiacaller.activity;

import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.BLUE;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.GREEN;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.ORANGE;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.RED;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.Guild;
import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.SceneryCampaign;
import br.com.battista.arcadiacaller.model.enuns.ActionEnum;
import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;
import br.com.battista.arcadiacaller.util.ImageLoadUtils;

public class CampaignCompleteActivity extends BaseActivity {

    private static final String TAG = CampaignDetailActivity.class.getSimpleName();

    private Campaign campaign;
    private TextView txtAliasCampaign;

    private TextView txtScenery01;
    private TextView txtScenery02;
    private TextView txtScenery03;
    private TextView txtScenery04;
    private TextView txtScenery05;
    private TextView txtScenery06;

    private TextView txtScenery01Location;
    private TextView txtScenery02Location;
    private TextView txtScenery03Location;
    private TextView txtScenery04Location;
    private TextView txtScenery05Location;
    private TextView txtScenery06Location;

    private TextView txtWinner;
    private TextView txtLeastDeaths;
    private TextView txtMostCoins;
    private TextView txtWonReward;
    private TextView txtWonTitle;

    private TextView txtGuildWinner;
    private TextView txtGuildWinnerHint;

    private Map<NameGuildEnum, List<Integer>> guildKeys = Maps.newHashMap();

    public CampaignCompleteActivity() {

        NameGuildEnum nameGuildBlue = NameGuildEnum.BLUE;
        List<Integer> keysBlue = Lists.newLinkedList();
        keysBlue.add(R.id.complete_card_view_scenery_guilds_img_blue);
        keysBlue.add(R.id.complete_card_view_scenery_guilds_login_blue);
        guildKeys.put(nameGuildBlue, keysBlue);

        NameGuildEnum nameGuildGreen = NameGuildEnum.GREEN;
        List<Integer> keysGreen = Lists.newLinkedList();
        keysGreen.add(R.id.complete_card_view_scenery_guilds_img_green);
        keysGreen.add(R.id.complete_card_view_scenery_guilds_login_green);
        guildKeys.put(nameGuildGreen, keysGreen);

        NameGuildEnum nameGuildRed = NameGuildEnum.RED;
        List<Integer> keysRed = Lists.newLinkedList();
        keysRed.add(R.id.complete_card_view_scenery_guilds_img_red);
        keysRed.add(R.id.complete_card_view_scenery_guilds_login_red);
        guildKeys.put(nameGuildRed, keysRed);

        NameGuildEnum nameGuildOrange = NameGuildEnum.ORANGE;
        List<Integer> keysOrange = Lists.newLinkedList();
        keysOrange.add(R.id.complete_card_view_scenery_guilds_img_orange);
        keysOrange.add(R.id.complete_card_view_scenery_guilds_login_orange);
        guildKeys.put(nameGuildOrange, keysOrange);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_complete);

        setupToolbarDetail();
        changeTitleCollapsingToolbar(R.string.title_campaign_completed);

        processDataActivity(getIntent().getExtras());
        loadFloatingAction();

    }

    private void loadGuildsImg() {
        Log.i(TAG, "loadGuildsImg: Load guilds imgs!");

        if (!Strings.isNullOrEmpty(campaign.getGuild01()) && campaign.getHeroesGuild01() != null) {
            ImageView imageView = (ImageView) findViewById(R.id.complete_card_view_scenery_guilds_img_blue);
            ImageLoadUtils
                    .loadImage(getContext(),
                            BLUE.getUrlImg(),
                            imageView);
        }

        if (!Strings.isNullOrEmpty(campaign.getGuild02()) && campaign.getHeroesGuild02() != null) {
            ImageView imageView = (ImageView) findViewById(R.id.complete_card_view_scenery_guilds_img_green);
            ImageLoadUtils
                    .loadImage(getContext(),
                            GREEN.getUrlImg(),
                            imageView);
        }

        if (!Strings.isNullOrEmpty(campaign.getGuild03()) && campaign.getHeroesGuild03() != null) {
            ImageView imageView = (ImageView) findViewById(R.id.complete_card_view_scenery_guilds_img_red);
            ImageLoadUtils
                    .loadImage(getContext(),
                            RED.getUrlImg(),
                            imageView);
        }

        if (!Strings.isNullOrEmpty(campaign.getGuild04()) && campaign.getHeroesGuild04() != null) {
            ImageView imageView = (ImageView) findViewById(R.id.complete_card_view_scenery_guilds_img_orange);
            ImageLoadUtils
                    .loadImage(getContext(),
                            ORANGE.getUrlImg(),
                            imageView);
        }
    }

    private void showGuildsActive(Campaign campaign) {
        Log.i(TAG, "showGuildsActive: Show the view elements of guild active!");
        if (Strings.isNullOrEmpty(campaign.getGuild01()) || campaign.getHeroesGuild01() == null) {
            for (Integer viewGuildResId : guildKeys.get(NameGuildEnum.BLUE)) {
                findViewById(viewGuildResId).setVisibility(View.GONE);
            }
        }

        if (Strings.isNullOrEmpty(campaign.getGuild02()) || campaign.getHeroesGuild02() == null) {
            for (Integer viewGuildResId : guildKeys.get(NameGuildEnum.GREEN)) {
                findViewById(viewGuildResId).setVisibility(View.GONE);
            }
        }

        if (Strings.isNullOrEmpty(campaign.getGuild03()) || campaign.getHeroesGuild03() == null) {
            for (Integer viewGuildResId : guildKeys.get(NameGuildEnum.RED)) {
                findViewById(viewGuildResId).setVisibility(View.GONE);
            }
        }

        if (Strings.isNullOrEmpty(campaign.getGuild04()) || campaign.getHeroesGuild04() == null) {
            for (Integer viewGuildResId : guildKeys.get(NameGuildEnum.ORANGE)) {
                findViewById(viewGuildResId).setVisibility(View.GONE);
            }
        }
    }

    private void fillGuildsActive() {

        Log.i(TAG, "showGuildsActive: Fill the view elements of guild active!");

        final String guild01 = campaign.getGuild01();
        final Guild heroesGuild01 = campaign.getHeroesGuild01();
        if (!Strings.isNullOrEmpty(guild01) && heroesGuild01 != null) {
            Log.i(TAG, MessageFormat.format("fillGuildsActive: Fill Guild 01 user: {0}!", guild01));

            TextView txtGuildUser = (TextView) findViewById(R.id.complete_card_view_scenery_guilds_login_blue);
            String textGuildUser = getString(R.string.hint_view_card_guild_user);
            String textNameGuild = getString(NameGuildEnum.BLUE.getResId());
            txtGuildUser.setText(MessageFormat.format(textGuildUser, textNameGuild, guild01));
        }

        final String guild02 = campaign.getGuild02();
        final Guild heroesGuild02 = campaign.getHeroesGuild02();
        if (!Strings.isNullOrEmpty(guild02) && heroesGuild02 != null) {
            Log.i(TAG, MessageFormat.format("fillGuildsActive: Fill Guild 02 user: {0}!", guild02));

            TextView txtGuildUser = (TextView) findViewById(R.id.complete_card_view_scenery_guilds_login_green);
            String textGuildUser = getString(R.string.hint_view_card_guild_user);
            String textNameGuild = getString(NameGuildEnum.GREEN.getResId());
            txtGuildUser.setText(MessageFormat.format(textGuildUser, textNameGuild, guild02));

        }

        final String guild03 = campaign.getGuild03();
        final Guild heroesGuild03 = campaign.getHeroesGuild03();
        if (!Strings.isNullOrEmpty(guild03) && heroesGuild03 != null) {
            Log.i(TAG, MessageFormat.format("fillGuildsActive: Fill Guild 03 user: {0}!", guild03));

            TextView txtGuildUser = (TextView) findViewById(R.id.complete_card_view_scenery_guilds_login_red);
            String textGuildUser = getString(R.string.hint_view_card_guild_user);
            String textNameGuild = getString(NameGuildEnum.RED.getResId());
            txtGuildUser.setText(MessageFormat.format(textGuildUser, textNameGuild, guild03));

        }

        final String guild04 = campaign.getGuild04();
        final Guild heroesGuild04 = campaign.getHeroesGuild04();
        if (!Strings.isNullOrEmpty(guild04) && heroesGuild04 != null) {
            Log.i(TAG, MessageFormat.format("fillGuildsActive: Fill Guild 04 user: {0}!", guild04));

            TextView txtGuildUser = (TextView) findViewById(R.id.complete_card_view_scenery_guilds_login_orange);
            String textGuildUser = getString(R.string.hint_view_card_guild_user);
            String textNameGuild = getString(NameGuildEnum.ORANGE.getResId());
            txtGuildUser.setText(MessageFormat.format(textGuildUser, textNameGuild, guild04));
        }
    }

    private void loadFloatingAction() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_done_complete_campaign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadMainActivity();
            }
        });
    }

    private void processDataActivity(Bundle bundle) {
        Log.d(TAG, "processDataActivity: Process bundle data Activity!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);

            if (!campaign.getCompleted()) {
                loadMainActivity();
            }

            loadGuildsImg();
            showGuildsActive(campaign);
            fillGuildsActive();

            String guildNameWinner = campaign.getSceneryCurrent().getWinner();
            txtGuildWinner = (TextView) findViewById(R.id.complete_card_view_guild_winner_name);
            txtGuildWinner.setText(guildNameWinner);

            txtGuildWinnerHint = (TextView) findViewById(R.id.complete_card_view_guild_winner_hint);
            CharSequence textWinner = getContext().getText(R.string.hint_complete_winner_text);
            txtGuildWinnerHint.setText(MessageFormat.format(textWinner.toString(), guildNameWinner));

            txtAliasCampaign = (TextView) findViewById(R.id.complete_card_view_campaign_alias);
            txtAliasCampaign.setText(MessageFormat.format("{0} - {1}", campaign.getKey(), campaign.getAlias()));

            String sceneryLocation = String.valueOf(getContext().getText(R.string.hint_scenery_head));

            txtScenery01Location = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_01_location);
            txtScenery01 = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_01);
            SceneryCampaign scenery1 = campaign.getScenery1();
            if (scenery1 != null) {
                txtScenery01.setText(scenery1.getName());

                Scenery scenery = scenery1.getScenery();
                CharSequence difficulty = getContext().getText(scenery.getDifficulty().getDescRes());
                CharSequence locationText = getContext().getText(scenery.getLocation().getDescRes());
                txtScenery01Location.setText(MessageFormat.format(sceneryLocation, difficulty, locationText));
            } else {
                txtScenery01.setText(R.string.none);
                txtScenery01Location.setText(R.string.none);
            }

            txtScenery02Location = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_02_location);
            txtScenery02 = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_02);
            SceneryCampaign scenery2 = campaign.getScenery2();
            if (scenery2 != null) {
                txtScenery02.setText(scenery2.getName());

                Scenery scenery = scenery2.getScenery();
                CharSequence difficulty = getContext().getText(scenery.getDifficulty().getDescRes());
                CharSequence locationText = getContext().getText(scenery.getLocation().getDescRes());
                txtScenery02Location.setText(MessageFormat.format(sceneryLocation, difficulty, locationText));
            } else {
                txtScenery02.setText(R.string.none);
                txtScenery02Location.setText(R.string.none);
            }

            txtScenery03Location = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_03_location);
            txtScenery03 = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_03);
            SceneryCampaign scenery3 = campaign.getScenery3();
            if (scenery3 != null) {
                txtScenery03.setText(scenery3.getName());

                Scenery scenery = scenery3.getScenery();
                CharSequence difficulty = getContext().getText(scenery.getDifficulty().getDescRes());
                CharSequence locationText = getContext().getText(scenery.getLocation().getDescRes());
                txtScenery03Location.setText(MessageFormat.format(sceneryLocation, difficulty, locationText));
            } else {
                txtScenery03.setText(R.string.none);
                txtScenery03Location.setText(R.string.none);
            }

            txtScenery04Location = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_04_location);
            txtScenery04 = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_04);
            SceneryCampaign scenery4 = campaign.getScenery4();
            if (scenery4 != null) {
                txtScenery04.setText(scenery4.getName());

                Scenery scenery = scenery4.getScenery();
                CharSequence difficulty = getContext().getText(scenery.getDifficulty().getDescRes());
                CharSequence locationText = getContext().getText(scenery.getLocation().getDescRes());
                txtScenery04Location.setText(MessageFormat.format(sceneryLocation, difficulty, locationText));
            } else {
                txtScenery04.setText(R.string.none);
                txtScenery04Location.setText(R.string.none);
            }

            txtScenery05Location = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_05_location);
            txtScenery05 = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_05);
            SceneryCampaign scenery5 = campaign.getScenery5();
            if (scenery5 != null) {
                txtScenery05.setText(scenery5.getName());

                Scenery scenery = scenery5.getScenery();
                CharSequence difficulty = getContext().getText(scenery.getDifficulty().getDescRes());
                CharSequence locationText = getContext().getText(scenery.getLocation().getDescRes());
                txtScenery05Location.setText(MessageFormat.format(sceneryLocation, difficulty, locationText));
            } else {
                txtScenery05.setText(R.string.none);
                txtScenery05Location.setText(R.string.none);
            }

            txtScenery06Location = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_06_location);
            txtScenery06 = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_06);
            SceneryCampaign scenery6 = campaign.getScenery6();
            if (scenery6 != null) {
                txtScenery06.setText(scenery6.getName());

                Scenery scenery = scenery6.getScenery();
                CharSequence difficulty = getContext().getText(scenery.getDifficulty().getDescRes());
                CharSequence locationText = getContext().getText(scenery.getLocation().getDescRes());
                txtScenery06Location.setText(MessageFormat.format(sceneryLocation, difficulty, locationText));
            } else {
                txtScenery06.setText(R.string.none);
                txtScenery06Location.setText(R.string.none);
            }

            txtWinner = (TextView) findViewById(R.id.complete_card_view_winners_medals_winners);
            List<String> winner = campaign.getWinners();
            if (winner == null || winner.isEmpty()) {
                txtWinner.setText(R.string.none);
            } else {
                txtWinner.setText(String.valueOf(winner));
            }

            txtLeastDeaths = (TextView) findViewById(R.id.complete_card_view_winners_medals_least_deaths);
            List<String> leastDeaths = campaign.getLeastDeaths();
            if (leastDeaths == null || leastDeaths.isEmpty()) {
                txtLeastDeaths.setText(R.string.none);
            } else {
                txtLeastDeaths.setText(String.valueOf(leastDeaths));
            }

            txtMostCoins = (TextView) findViewById(R.id.complete_card_view_winners_medals_most_coins);
            List<String> mostCoins = campaign.getMostCoins();
            if (mostCoins == null || mostCoins.isEmpty()) {
                txtMostCoins.setText(R.string.none);
            } else {
                txtMostCoins.setText(String.valueOf(mostCoins));
            }

            txtWonReward = (TextView) findViewById(R.id.complete_card_view_winners_medals_won_rewards);
            List<String> wonReward = campaign.getWonReward();
            if (wonReward == null || wonReward.isEmpty()) {
                txtWonReward.setText(R.string.none);
            } else {
                txtWonReward.setText(String.valueOf(wonReward));
            }

            txtWonTitle = (TextView) findViewById(R.id.complete_card_view_winners_medals_won_titles);
            List<String> wonTitle = campaign.getWonTitle();
            if (wonTitle == null || wonTitle.isEmpty()) {
                txtWonTitle.setText(R.string.none);
            } else {
                txtWonTitle.setText(String.valueOf(wonTitle));
            }

        } else {
            loadMainActivity();
        }
    }

    private void loadMainActivity() {
        Bundle args = new Bundle();
        args.putString(BundleConstant.ACTION, ActionEnum.START_FRAGMENT_CAMPAIGNS.name());
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
