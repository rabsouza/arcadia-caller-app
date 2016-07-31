package br.com.battista.arcadiacaller.fragment.detail;

import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.BLUE;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.GREEN;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.ORANGE;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.RED;
import static br.com.battista.arcadiacaller.util.AndroidUtils.changeErrorSpinner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.activity.MainActivity;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.exception.ValidatorException;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.Guild;
import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.SceneryCampaign;
import br.com.battista.arcadiacaller.model.enuns.ActionEnum;
import br.com.battista.arcadiacaller.model.enuns.CampaignStatusEnum;
import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;
import br.com.battista.arcadiacaller.service.CampaignService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

public class CampaignDetailCompleteSceneryFragment extends BaseFragment {

    private static final String TAG = CampaignDetailCompleteSceneryFragment.class.getSimpleName();

    private Campaign campaign;

    private TextView txtAliasCampaign;
    private TextView txtLocationScenery;
    private TextView txtNameScenery;
    private TextView txtWonRewardScenery;
    private TextView txtWonTitleScenery;
    private MaterialBetterSpinner spnGuildWinner;

    private Map<NameGuildEnum, Integer[]> guildKeys = Maps.newHashMap();

    public CampaignDetailCompleteSceneryFragment() {

        NameGuildEnum nameGuildBlue = NameGuildEnum.BLUE;
        Integer keysBlue[] = {
                R.id.detail_card_view_complete_scenery_result_guild_img_blue,
                R.id.detail_card_view_scenery_least_deaths_blue,
                R.id.detail_card_view_scenery_most_coins_blue,
                R.id.detail_card_view_scenery_won_reward_blue,
                R.id.detail_card_view_scenery_won_title_blue,
                R.id.detail_card_view_scenery_saved_coin_blue};
        guildKeys.put(nameGuildBlue, keysBlue);

        NameGuildEnum nameGuildGrenn = NameGuildEnum.GREEN;
        Integer keysGrenn[] = {
                R.id.detail_card_view_complete_scenery_result_guild_img_green,
                R.id.detail_card_view_scenery_least_deaths_green,
                R.id.detail_card_view_scenery_most_coins_green,
                R.id.detail_card_view_scenery_won_reward_green,
                R.id.detail_card_view_scenery_won_title_green,
                R.id.detail_card_view_scenery_saved_coin_green};
        guildKeys.put(nameGuildGrenn, keysGrenn);

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

    public static CampaignDetailCompleteSceneryFragment newInstance(Campaign campaign) {
        CampaignDetailCompleteSceneryFragment fragment = new CampaignDetailCompleteSceneryFragment();
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: Create detail scenery campaign!");
        final View viewFragment = inflater.inflate(R.layout.fragment_campaign_detail_complete_scenery, container, false);

        FloatingActionButton fab = (FloatingActionButton) viewFragment.findViewById(R.id.fab_next_complete_scenery_campaign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processNextAction(viewFragment);
            }
        });

        processDataFragment(viewFragment, getArguments());

        return viewFragment;
    }

    private void processNextAction(final View view) {
        Log.d(TAG, "processNextAction: Process next action -> Activity MainActivity -> Fragment CampaignsFragment!");

        if (campaign == null) {
            AndroidUtils.snackbar(view, R.string.msg_error_campaign_not_found);
            return;
        }

        final SceneryCampaign sceneryCurrent = campaign.getSceneryCurrent();
        List<String> activeGuilds = campaign.getAllActiveGuilds();
        spnGuildWinner = (MaterialBetterSpinner) view.findViewById(R.id.detail_card_view_scenery_winner);
        String guildWinner = spnGuildWinner.getText().toString();
        if (activeGuilds.contains(guildWinner)) {
            Log.i(TAG, "processNextAction: Fill data scenery!");
            changeErrorSpinner(spnGuildWinner);

            sceneryCurrent.setWinner(guildWinner);
            Guild guild01 = campaign.getHeroesGuild01();
            String campaignGuild01 = campaign.getGuild01();
            Integer[] keyGuildBlue = guildKeys.get(NameGuildEnum.BLUE);
            fillResultGuild(view, sceneryCurrent, guildWinner, guild01, campaignGuild01, keyGuildBlue);

            Guild guild02 = campaign.getHeroesGuild02();
            String campaignGuild02 = campaign.getGuild02();
            Integer[] keyGuildGreen = guildKeys.get(NameGuildEnum.GREEN);
            fillResultGuild(view, sceneryCurrent, guildWinner, guild02, campaignGuild02, keyGuildGreen);

            Guild guild03 = campaign.getHeroesGuild03();
            String campaignGuild03 = campaign.getGuild03();
            Integer[] keyGuildRed = guildKeys.get(NameGuildEnum.RED);
            fillResultGuild(view, sceneryCurrent, guildWinner, guild03, campaignGuild03, keyGuildRed);

            Guild guild04 = campaign.getHeroesGuild04();
            String campaignGuild04 = campaign.getGuild04();
            Integer[] keyGuildOrange = guildKeys.get(NameGuildEnum.ORANGE);
            fillResultGuild(view, sceneryCurrent, guildWinner, guild04, campaignGuild04, keyGuildOrange);

            sceneryCurrent.setCompleted(Boolean.TRUE);

            String msgFinishScenery = getContext().getResources().getString(R.string.alert_confirmation_dialog_text_scenery);
            new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.alert_confirmation_dialog_title_play)
                    .setMessage(MessageFormat.format(msgFinishScenery, sceneryCurrent.getScenery().getName()))
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(R.string.btn_confirmation_dialog_finish, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Log.i(TAG, MessageFormat.format("onClick: Finish scenery: {0}!", sceneryCurrent));
                            finishScenery(view);
                        }
                    })
                    .setNegativeButton(R.string.btn_confirmation_dialog_cancel, null).show();

        } else {
            String warnGuildWinner = getContext().getText(R.string.msg_warn_guild_winner).toString();
            changeErrorSpinner(spnGuildWinner, warnGuildWinner, true);
            return;
        }

    }

    public void finishScenery(View view) {
        final View currentView = view;
        new ProgressApp(getActivity(), R.string.msg_action_saving, false) {
            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    if (CampaignStatusEnum.COMPLETED_CAMPAIGN.equals(campaign.getStatusCurrent())) {
                        loadMainActivity();
                    } else {
                        replaceDetailFragment(CampaignDetailSceneryFragment.newInstance(campaign, campaign.getLocationCurrent()), R.id.detail_container_complete);
                    }
                } else {
                    AndroidUtils.snackbar(currentView, R.string.msg_failed_update_campaign);
                }
                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    String token = MainApplication.instance().getToken();

                    CampaignService campaignService = Inject.provideCampaignService();
                    Log.i(TAG, "doInBackground: Update the campaign with completed scenery!");
                    campaign = campaignService.update(token, campaign);

                } catch (ValidatorException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                    return false;
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }
        }.execute();

    }

    private void loadMainActivity() {
        Bundle args = new Bundle();
        args.putString(BundleConstant.ACTION, ActionEnum.START_FRAGMENT_CAMPAIGNS.name());
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

    private void fillResultGuild(View view, SceneryCampaign sceneryCurrent, String guildWinner, Guild guild, String campaignGuild, Integer[] keyGuild) {
        if (!Strings.isNullOrEmpty(campaignGuild) && guild != null) {
            if (campaignGuild.equalsIgnoreCase(guildWinner)) {
                guild.incVictories();
            } else {
                guild.incDefeats();
            }

            if (keyGuild.length > 1 && ((CheckBox) view.findViewById(keyGuild[1])).isChecked()) {
                sceneryCurrent.addLeastDeaths(campaignGuild);
            }

            if (keyGuild.length > 2 && ((CheckBox) view.findViewById(keyGuild[2])).isChecked()) {
                sceneryCurrent.addMostCoins(campaignGuild);
            }

            if (keyGuild.length > 3 && ((CheckBox) view.findViewById(keyGuild[3])).isChecked()) {
                sceneryCurrent.addWonReward(campaignGuild);
                if (sceneryCurrent.getScenery() != null && sceneryCurrent.getScenery().getWonReward() != null) {
                    guild.addRewardCards(sceneryCurrent.getScenery().getWonReward().getName());
                }
            }

            if (keyGuild.length > 4 && ((CheckBox) view.findViewById(keyGuild[4])).isChecked()) {
                sceneryCurrent.addWonTitle(campaignGuild);
                if (sceneryCurrent.getScenery() != null) {
                    guild.addBenefitTitles(sceneryCurrent.getScenery().getWonTitle());
                }
            }

            if (keyGuild.length > 5 && ((CheckBox) view.findViewById(keyGuild[5])).isChecked()) {
                guild.setSavedMoney(Boolean.TRUE);
            } else {
                guild.setSavedMoney(Boolean.FALSE);
            }
        }
    }

    private void processDataFragment(final View viewFragment, Bundle bundle) {
        Log.d(TAG, "processDataFragment: Processs bundle data Fragment!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);

            txtAliasCampaign = (TextView) viewFragment.findViewById(R.id.detail_card_view_campaign_alias);
            txtAliasCampaign.setText(MessageFormat.format("{0} - {1}", campaign.getKey(), campaign.getAlias()));

            SceneryCampaign sceneryCurrent = campaign.getSceneryCurrent();
            Scenery scenery = sceneryCurrent.getScenery();
            loadDataScenery(viewFragment, scenery);
            loadGuildsImg(viewFragment);
            loadGuildsWinner(viewFragment);
            showGuildsActive(viewFragment, campaign);
        }
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

    private void loadGuildsWinner(View viewFragment) {
        Log.i(TAG, "loadGuildsWinner: Load all guilds active for winner!");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, campaign.getAllActiveGuilds());
        spnGuildWinner = (MaterialBetterSpinner)
                viewFragment.findViewById(R.id.detail_card_view_scenery_winner);
        spnGuildWinner.setAdapter(arrayAdapter);
        spnGuildWinner.setText(R.string.none);
    }

    private void loadGuildsImg(View viewFragment) {
        Log.i(TAG, "loadGuildsImg: Load guilds imgs!");
        Glide.with(getContext())
                .load(BLUE.getUrlImg())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_complete_scenery_result_guild_img_blue));

        Glide.with(getContext())
                .load(GREEN.getUrlImg())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_complete_scenery_result_guild_img_green));

        Glide.with(getContext())
                .load(RED.getUrlImg())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_complete_scenery_result_guild_img_red));

        Glide.with(getContext())
                .load(ORANGE.getUrlImg())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_complete_scenery_result_guild_img_orange));
    }
}
