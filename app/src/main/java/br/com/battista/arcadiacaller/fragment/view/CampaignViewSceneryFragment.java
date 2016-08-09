package br.com.battista.arcadiacaller.fragment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.activity.CampaignCompleteActivity;
import br.com.battista.arcadiacaller.activity.MainActivity;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.Guild;
import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.SceneryCampaign;
import br.com.battista.arcadiacaller.model.enuns.ActionEnum;
import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ImageLoadUtils;

import static br.com.battista.arcadiacaller.R.id.detail_card_view_scenery_guilds_img_blue;
import static br.com.battista.arcadiacaller.R.id.detail_card_view_scenery_guilds_login_blue;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.BLUE;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.GREEN;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.ORANGE;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.RED;


public class CampaignViewSceneryFragment extends BaseFragment {

    private static final String TAG = CampaignViewSceneryFragment.class.getSimpleName();

    private Campaign campaign;

    private TextView txtAliasCampaign;
    private TextView txtScenery;
    private TextView txtLocationScenery;
    private TextView txtNameScenery;
    private TextView txtWonRewardScenery;
    private TextView txtWonTitleScenery;
    private MaterialBetterSpinner spnGuildWinner;

    private SceneryCampaign sceneryCurrent;
    private int position;

    private Map<NameGuildEnum, List<Integer>> guildKeys = Maps.newHashMap();

    public CampaignViewSceneryFragment() {

        NameGuildEnum nameGuildBlue = NameGuildEnum.BLUE;
        List<Integer> keysBlue = Lists.newLinkedList();
        keysBlue.add(R.id.detail_card_view_complete_scenery_result_guild_img_blue);
        keysBlue.add(R.id.detail_card_view_scenery_least_deaths_blue);
        keysBlue.add(R.id.detail_card_view_scenery_most_coins_blue);
        keysBlue.add(R.id.detail_card_view_scenery_won_reward_blue);
        keysBlue.add(R.id.detail_card_view_scenery_won_title_blue);
        keysBlue.add(R.id.detail_card_view_scenery_guilds_img_blue);
        keysBlue.add(R.id.detail_card_view_scenery_guilds_login_blue);
        guildKeys.put(nameGuildBlue, keysBlue);

        NameGuildEnum nameGuildGreen = NameGuildEnum.GREEN;
        List<Integer> keysGreen = Lists.newLinkedList();
        keysGreen.add(R.id.detail_card_view_complete_scenery_result_guild_img_green);
        keysGreen.add(R.id.detail_card_view_scenery_least_deaths_green);
        keysGreen.add(R.id.detail_card_view_scenery_most_coins_green);
        keysGreen.add(R.id.detail_card_view_scenery_won_reward_green);
        keysGreen.add(R.id.detail_card_view_scenery_won_title_green);
        keysGreen.add(R.id.detail_card_view_scenery_guilds_img_green);
        keysGreen.add(R.id.detail_card_view_scenery_guilds_login_green);
        guildKeys.put(nameGuildGreen, keysGreen);

        NameGuildEnum nameGuildRed = NameGuildEnum.RED;
        List<Integer> keysRed = Lists.newLinkedList();
        keysRed.add(R.id.detail_card_view_complete_scenery_result_guild_img_red);
        keysRed.add(R.id.detail_card_view_scenery_least_deaths_red);
        keysRed.add(R.id.detail_card_view_scenery_most_coins_red);
        keysRed.add(R.id.detail_card_view_scenery_won_reward_red);
        keysRed.add(R.id.detail_card_view_scenery_won_title_red);
        keysRed.add(R.id.detail_card_view_scenery_guilds_img_red);
        keysRed.add(R.id.detail_card_view_scenery_guilds_login_red);
        guildKeys.put(nameGuildRed, keysRed);

        NameGuildEnum nameGuildOrange = NameGuildEnum.ORANGE;
        List<Integer> keysOrange = Lists.newLinkedList();
        keysOrange.add(R.id.detail_card_view_complete_scenery_result_guild_img_orange);
        keysOrange.add(R.id.detail_card_view_scenery_least_deaths_orange);
        keysOrange.add(R.id.detail_card_view_scenery_most_coins_orange);
        keysOrange.add(R.id.detail_card_view_scenery_won_reward_orange);
        keysOrange.add(R.id.detail_card_view_scenery_won_title_orange);
        keysOrange.add(R.id.detail_card_view_scenery_guilds_img_orange);
        keysOrange.add(R.id.detail_card_view_scenery_guilds_login_orange);
        guildKeys.put(nameGuildOrange, keysOrange);

    }

    public static CampaignViewSceneryFragment newInstance(Campaign campaign, Integer position) {
        CampaignViewSceneryFragment fragment = new CampaignViewSceneryFragment();
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        args.putInt(BundleConstant.POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: Create detail scenery campaign!");
        final View viewFragment = inflater.inflate(R.layout.fragment_campaign_view_scenery, container, false);

        processDataFragment(viewFragment, getArguments());

        FloatingActionButton fab = (FloatingActionButton) viewFragment.findViewById(R.id.fab_next_view_scenery_campaign);

        SceneryCampaign nextScenery = campaign.getSceneryPosition(position + 1);
        if (nextScenery.getCompleted()) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    processNextAction();
                }
            });
        } else if (position == 6 && campaign.getCompleted()) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadCampaignCompleteActivity(campaign);
                }
            });
        } else {
            fab.setImageResource(R.drawable.ic_done);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadMainActivity();
                }
            });
        }

        return viewFragment;
    }

    private void loadCampaignCompleteActivity(Campaign campaign) {
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        Intent intent = new Intent(getContext(), CampaignCompleteActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

    private void loadMainActivity() {
        Bundle args = new Bundle();
        args.putString(BundleConstant.ACTION, ActionEnum.START_FRAGMENT_CAMPAIGNS.name());
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

    private void processNextAction() {
        Log.d(TAG, MessageFormat.format(
                "processNextAction: Process next action -> Activity CampaignViewActivity -> Fragment CampaignViewSceneryFragment -> Position: {0}!",
                position));
        replaceDetailFragment(CampaignViewSceneryFragment.newInstance(campaign, ++position), R.id.detail_container_view);
    }

    private void processDataFragment(final View viewFragment, Bundle bundle) {
        Log.d(TAG, "processDataFragment: Process bundle data Fragment!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);

            txtAliasCampaign = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_alias);
            txtAliasCampaign.setText(MessageFormat.format("{0} - {1}", campaign.getKey(), campaign.getAlias()));


            if (bundle.containsKey(BundleConstant.POSITION)) {
                position = bundle.getInt(BundleConstant.POSITION);
            } else {
                position = 1;
            }
            sceneryCurrent = campaign.getSceneryPosition(position);

            if (sceneryCurrent.getCompleted()) {
                Scenery scenery = sceneryCurrent.getScenery();
                loadDataScenery(viewFragment, scenery);
                loadGuildsImg(viewFragment);
                loadGuildWinner(viewFragment);
                fillGuildsActive(viewFragment);
                showGuildsActive(viewFragment);
            } else {
                AndroidUtils.snackbar(viewFragment, getContext().getText(R.string.msg_error_campaign_not_found).toString());
            }

            txtScenery = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_complete_scenery_title);
            String textSceneryCurrent = getString(R.string.hint_view_scenery_current);
            txtScenery.setText(MessageFormat.format(textSceneryCurrent, position));

        } else {
            campaign = new Campaign();
            AndroidUtils.snackbar(viewFragment, getContext().getText(R.string.msg_error_campaign_not_found).toString());
        }
    }

    private void fillGuildsActive(@NonNull View viewFragment) {

        List<String> leastDeaths = sceneryCurrent.getLeastDeaths();
        if (leastDeaths == null) {
            leastDeaths = Lists.newLinkedList();
        }
        List<String> mostCoins = sceneryCurrent.getMostCoins();
        if (mostCoins == null) {
            mostCoins = Lists.newLinkedList();
        }
        List<String> wonReward = sceneryCurrent.getWonReward();
        if (wonReward == null) {
            wonReward = Lists.newLinkedList();
        }
        List<String> wonTitle = sceneryCurrent.getWonTitle();
        if (wonTitle == null) {
            wonTitle = Lists.newLinkedList();
        }

        Log.i(TAG, "showGuildsActive: Fill the view elements of guild active!");

        final String guild01 = campaign.getGuild01();
        final Guild heroesGuild01 = campaign.getHeroesGuild01();
        if (!Strings.isNullOrEmpty(guild01) && heroesGuild01 != null) {
            Log.i(TAG, MessageFormat.format("fillGuildsActive: Fill Guild 01 user: {0}!", guild01));
            List<Integer> keys = guildKeys.get(NameGuildEnum.BLUE);

            TextView txtGuildUser = (TextView) viewFragment.findViewById(detail_card_view_scenery_guilds_login_blue);
            String textGuildUser = getString(R.string.hint_view_card_guild_user);
            String textNameGuild = getString(NameGuildEnum.BLUE.getResId());
            txtGuildUser.setText(MessageFormat.format(textGuildUser, textNameGuild, guild01));

            int visibility = leastDeaths.contains(guild01) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(1)).setVisibility(visibility);

            visibility = mostCoins.contains(guild01) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(2)).setVisibility(visibility);

            visibility = wonReward.contains(guild01) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(3)).setVisibility(visibility);

            visibility = wonTitle.contains(guild01) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(4)).setVisibility(visibility);

        }

        final String guild02 = campaign.getGuild02();
        final Guild heroesGuild02 = campaign.getHeroesGuild02();
        if (!Strings.isNullOrEmpty(guild02) && heroesGuild02 != null) {
            Log.i(TAG, MessageFormat.format("fillGuildsActive: Fill Guild 02 user: {0}!", guild02));
            List<Integer> keys = guildKeys.get(NameGuildEnum.GREEN);

            TextView txtGuildUser = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_guilds_login_green);
            String textGuildUser = getString(R.string.hint_view_card_guild_user);
            String textNameGuild = getString(NameGuildEnum.GREEN.getResId());
            txtGuildUser.setText(MessageFormat.format(textGuildUser, textNameGuild, guild02));

            int visibility = leastDeaths.contains(guild02) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(1)).setVisibility(visibility);

            visibility = mostCoins.contains(guild02) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(2)).setVisibility(visibility);

            visibility = wonReward.contains(guild02) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(3)).setVisibility(visibility);

            visibility = wonTitle.contains(guild02) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(4)).setVisibility(visibility);

        }

        final String guild03 = campaign.getGuild03();
        final Guild heroesGuild03 = campaign.getHeroesGuild03();
        if (!Strings.isNullOrEmpty(guild03) && heroesGuild03 != null) {
            Log.i(TAG, MessageFormat.format("fillGuildsActive: Fill Guild 03 user: {0}!", guild03));
            List<Integer> keys = guildKeys.get(NameGuildEnum.RED);

            TextView txtGuildUser = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_guilds_login_red);
            String textGuildUser = getString(R.string.hint_view_card_guild_user);
            String textNameGuild = getString(NameGuildEnum.RED.getResId());
            txtGuildUser.setText(MessageFormat.format(textGuildUser, textNameGuild, guild03));

            int visibility = leastDeaths.contains(guild03) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(1)).setVisibility(visibility);

            visibility = mostCoins.contains(guild03) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(2)).setVisibility(visibility);

            visibility = wonReward.contains(guild03) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(3)).setVisibility(visibility);

            visibility = wonTitle.contains(guild03) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(4)).setVisibility(visibility);

        }

        final String guild04 = campaign.getGuild04();
        final Guild heroesGuild04 = campaign.getHeroesGuild04();
        if (!Strings.isNullOrEmpty(guild04) && heroesGuild04 != null) {
            Log.i(TAG, MessageFormat.format("fillGuildsActive: Fill Guild 04 user: {0}!", guild04));
            List<Integer> keys = guildKeys.get(NameGuildEnum.ORANGE);

            TextView txtGuildUser = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_guilds_login_orange);
            String textGuildUser = getString(R.string.hint_view_card_guild_user);
            String textNameGuild = getString(NameGuildEnum.ORANGE.getResId());
            txtGuildUser.setText(MessageFormat.format(textGuildUser, textNameGuild, guild04));

            int visibility = leastDeaths.contains(guild04) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(1)).setVisibility(visibility);

            visibility = mostCoins.contains(guild04) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(2)).setVisibility(visibility);

            visibility = wonReward.contains(guild04) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(3)).setVisibility(visibility);

            visibility = wonTitle.contains(guild04) ? View.VISIBLE : View.INVISIBLE;
            viewFragment.findViewById(keys.get(4)).setVisibility(visibility);

        }
    }

    private void showGuildsActive(@NonNull View viewFragment) {
        Log.i(TAG, "showGuildsActive: Show the view elements of guild active!");
        if (Strings.isNullOrEmpty(campaign.getGuild01()) || campaign.getHeroesGuild01() == null) {
            for (Integer viewGuildResId : guildKeys.get(NameGuildEnum.BLUE)) {
                viewFragment.findViewById(viewGuildResId).setVisibility(View.GONE);
            }
        }

        if (Strings.isNullOrEmpty(campaign.getGuild02()) || campaign.getHeroesGuild02() == null) {
            for (Integer viewGuildResId : guildKeys.get(NameGuildEnum.GREEN)) {
                viewFragment.findViewById(viewGuildResId).setVisibility(View.GONE);
            }
        }

        if (Strings.isNullOrEmpty(campaign.getGuild03()) || campaign.getHeroesGuild03() == null) {
            for (Integer viewGuildResId : guildKeys.get(NameGuildEnum.RED)) {
                viewFragment.findViewById(viewGuildResId).setVisibility(View.GONE);
            }
        }

        if (Strings.isNullOrEmpty(campaign.getGuild04()) || campaign.getHeroesGuild04() == null) {
            for (Integer viewGuildResId : guildKeys.get(NameGuildEnum.ORANGE)) {
                viewFragment.findViewById(viewGuildResId).setVisibility(View.GONE);
            }
        }
    }

    private void loadDataScenery(View viewFragment, Scenery scenery) {
        if (scenery != null) {
            Log.i(TAG, "processDataFragment: Fill current scenery!");

            txtLocationScenery = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_location);
            String sceneryLocation = String.valueOf(getContext().getText(R.string.hint_scenery_head));
            CharSequence difficulty = getContext().getText(scenery.getDifficulty().getDescRes());
            CharSequence locationText = getContext().getText(scenery.getLocation().getDescRes());
            txtLocationScenery.setText(MessageFormat.format(sceneryLocation, difficulty, locationText));

            txtNameScenery = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_name);
            txtNameScenery.setText(scenery.getName());

            txtWonRewardScenery = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_won_reward);
            if (scenery.getWonReward() != null) {
                txtWonRewardScenery.setText(scenery.getWonReward().getName());
            } else {
                txtWonRewardScenery.setText(R.string.none);
                (viewFragment.findViewById(R.id.detail_card_view_scenery_won_reward_group)).setVisibility(View.GONE);
            }

            txtWonTitleScenery = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_won_title);
            if (!Strings.isNullOrEmpty(scenery.getWonTitle())) {
                txtWonTitleScenery.setText(scenery.getWonTitle());
            } else {
                txtWonTitleScenery.setText(R.string.none);
                (viewFragment.findViewById(R.id.detail_card_view_scenery_won_title_group)).setVisibility(View.GONE);
            }
        }
    }

    private void loadGuildWinner(View viewFragment) {
        Log.i(TAG, "loadGuildWinner: Load all guilds active for winners!");

        TextView txtWinner = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_winner);
        String textWinner = getContext().getString(R.string.hint_view_user_winner);
        txtWinner.setText(MessageFormat.format(textWinner, sceneryCurrent.getWinner()));
    }

    private void loadGuildsImg(View viewFragment) {
        Log.i(TAG, "loadGuildsImg: Load guilds imgs!");

        if (!Strings.isNullOrEmpty(campaign.getGuild01()) && campaign.getHeroesGuild01() != null) {
            ImageView imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_complete_scenery_result_guild_img_blue);
            ImageLoadUtils
                    .loadImage(getContext(),
                            BLUE.getUrlImg(),
                            imageView);

            imageView = (ImageView) viewFragment.findViewById(detail_card_view_scenery_guilds_img_blue);
            ImageLoadUtils
                    .loadImage(getContext(),
                            BLUE.getUrlImg(),
                            imageView);
        }

        if (!Strings.isNullOrEmpty(campaign.getGuild02()) && campaign.getHeroesGuild02() != null) {
            ImageView imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_complete_scenery_result_guild_img_green);
            ImageLoadUtils
                    .loadImage(getContext(),
                            GREEN.getUrlImg(),
                            imageView);

            imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_scenery_guilds_img_green);
            ImageLoadUtils
                    .loadImage(getContext(),
                            GREEN.getUrlImg(),
                            imageView);
        }

        if (!Strings.isNullOrEmpty(campaign.getGuild03()) && campaign.getHeroesGuild03() != null) {
            ImageView imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_complete_scenery_result_guild_img_red);
            ImageLoadUtils
                    .loadImage(getContext(),
                            RED.getUrlImg(),
                            imageView);

            imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_scenery_guilds_img_red);
            ImageLoadUtils
                    .loadImage(getContext(),
                            RED.getUrlImg(),
                            imageView);
        }

        if (!Strings.isNullOrEmpty(campaign.getGuild04()) && campaign.getHeroesGuild04() != null) {
            ImageView imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_complete_scenery_result_guild_img_orange);
            ImageLoadUtils
                    .loadImage(getContext(),
                            ORANGE.getUrlImg(),
                            imageView);

            imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_scenery_guilds_img_orange);
            ImageLoadUtils
                    .loadImage(getContext(),
                            ORANGE.getUrlImg(),
                            imageView);
        }
    }

}
