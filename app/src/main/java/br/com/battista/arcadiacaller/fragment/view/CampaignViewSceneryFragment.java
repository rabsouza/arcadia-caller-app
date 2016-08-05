package br.com.battista.arcadiacaller.fragment.view;

import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.BLUE;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.GREEN;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.ORANGE;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.RED;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.MessageFormat;
import java.util.Map;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.activity.MainActivity;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.SceneryCampaign;
import br.com.battista.arcadiacaller.model.enuns.ActionEnum;
import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ImageLoadUtils;


public class CampaignViewSceneryFragment extends BaseFragment {

    private static final String TAG = CampaignViewSceneryFragment.class.getSimpleName();

    private Campaign campaign;

    private TextView txtAliasCampaign;
    private TextView txtLocationScenery;
    private TextView txtNameScenery;
    private TextView txtWonRewardScenery;
    private TextView txtWonTitleScenery;
    private MaterialBetterSpinner spnGuildWinner;

    private SceneryCampaign sceneryCurrent;
    private int position;

    private Map<NameGuildEnum, Integer[]> guildKeys = Maps.newHashMap();

    public CampaignViewSceneryFragment() {

        NameGuildEnum nameGuildBlue = NameGuildEnum.BLUE;
        Integer keysBlue[] = {
                R.id.detail_card_view_complete_scenery_result_guild_img_blue,
                R.id.detail_card_view_scenery_least_deaths_blue,
                R.id.detail_card_view_scenery_most_coins_blue,
                R.id.detail_card_view_scenery_won_reward_blue,
                R.id.detail_card_view_scenery_won_title_blue,
                R.id.detail_card_view_scenery_saved_coin_blue};
        guildKeys.put(nameGuildBlue, keysBlue);

        NameGuildEnum nameGuildGreen = NameGuildEnum.GREEN;
        Integer keysGreen[] = {
                R.id.detail_card_view_complete_scenery_result_guild_img_green,
                R.id.detail_card_view_scenery_least_deaths_green,
                R.id.detail_card_view_scenery_most_coins_green,
                R.id.detail_card_view_scenery_won_reward_green,
                R.id.detail_card_view_scenery_won_title_green,
                R.id.detail_card_view_scenery_saved_coin_green};
        guildKeys.put(nameGuildGreen, keysGreen);

        NameGuildEnum nameGuildRed = NameGuildEnum.RED;
        Integer keysRed[] = {
                R.id.detail_card_view_complete_scenery_result_guild_img_red,
                R.id.detail_card_view_scenery_least_deaths_red,
                R.id.detail_card_view_scenery_most_coins_red,
                R.id.detail_card_view_scenery_won_reward_red,
                R.id.detail_card_view_scenery_won_title_red,
                R.id.detail_card_view_scenery_saved_coin_red};
        guildKeys.put(nameGuildRed, keysRed);

        NameGuildEnum nameGuildOrange = NameGuildEnum.ORANGE;
        Integer keysOrange[] = {
                R.id.detail_card_view_complete_scenery_result_guild_img_orange,
                R.id.detail_card_view_scenery_least_deaths_orange,
                R.id.detail_card_view_scenery_most_coins_orange,
                R.id.detail_card_view_scenery_won_reward_orange,
                R.id.detail_card_view_scenery_won_title_orange,
                R.id.detail_card_view_scenery_saved_coin_orange};
        guildKeys.put(nameGuildOrange, keysOrange);

    }

    public static CampaignViewSceneryFragment newInstance(Campaign campaign, SceneryCampaign sceneryCurrent, Integer position) {
        CampaignViewSceneryFragment fragment = new CampaignViewSceneryFragment();
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        args.putSerializable(BundleConstant.SCENERY, sceneryCurrent);
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
        if (sceneryCurrent != null && sceneryCurrent.getCompleted()) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    processNextAction(viewFragment);
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

    private void loadMainActivity() {
        Bundle args = new Bundle();
        args.putString(BundleConstant.ACTION, ActionEnum.START_FRAGMENT_CAMPAIGNS.name());
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

    private void processNextAction(final View view) {
        Log.d(TAG, "processNextAction: Process next action -> Activity MainActivity -> Fragment CampaignsFragment!");

    }

    private void processDataFragment(final View viewFragment, Bundle bundle) {
        Log.d(TAG, "processDataFragment: Process bundle data Fragment!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);

            txtAliasCampaign = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_alias);
            txtAliasCampaign.setText(MessageFormat.format("{0} - {1}", campaign.getKey(), campaign.getAlias()));

            sceneryCurrent = (SceneryCampaign) bundle.getSerializable(BundleConstant.SCENERY);
            if (sceneryCurrent != null && sceneryCurrent.getCompleted()) {
                Scenery scenery = sceneryCurrent.getScenery();
                loadDataScenery(viewFragment, scenery);
                loadGuildsImg(viewFragment);
                loadGuildWinner(viewFragment, sceneryCurrent);
                showGuildsActive(viewFragment, campaign);
                fillGuildsActive(viewFragment, campaign, sceneryCurrent);
            } else {
                AndroidUtils.snackbar(viewFragment, getContext().getText(R.string.msg_error_campaign_not_found).toString());
            }

            if (bundle.containsKey(BundleConstant.POSITION)) {
                position = bundle.getInt(BundleConstant.POSITION);
            } else {
                position = 1;
            }
        } else {
            campaign = new Campaign();
            AndroidUtils.snackbar(viewFragment, getContext().getText(R.string.msg_error_campaign_not_found).toString());
        }
    }

    private void fillGuildsActive(View viewFragment, Campaign campaign, SceneryCampaign sceneryCurrent) {
        Log.i(TAG, "showGuildsActive: Fill the view elements of guild active!");
    }

    private void showGuildsActive(View viewFragment, Campaign campaign) {
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

    private void loadGuildWinner(View viewFragment, SceneryCampaign sceneryCurrent) {
        Log.i(TAG, "loadGuildWinner: Load all guilds active for winners!");

        TextView txtWinner = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_winner);
        String textWinner = getContext().getString(R.string.hint_view_user_winner);
        txtWinner.setText(MessageFormat.format(textWinner, sceneryCurrent.getWinner()));
    }

    private void loadGuildsImg(View viewFragment) {
        Log.i(TAG, "loadGuildsImg: Load guilds imgs!");

        ImageView imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_complete_scenery_result_guild_img_blue);
        ImageLoadUtils
                .loadImage(getContext(),
                        BLUE.getUrlImg(),
                        imageView);

        imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_complete_scenery_result_guild_img_green);
        ImageLoadUtils
                .loadImage(getContext(),
                        GREEN.getUrlImg(),
                        imageView);

        imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_complete_scenery_result_guild_img_red);
        ImageLoadUtils
                .loadImage(getContext(),
                        RED.getUrlImg(),
                        imageView);

        imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_complete_scenery_result_guild_img_orange);
        ImageLoadUtils
                .loadImage(getContext(),
                        ORANGE.getUrlImg(),
                        imageView);
    }

}
