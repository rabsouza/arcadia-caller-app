package br.com.battista.arcadiacaller.fragment.detail;

import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.BLUE;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.GREEN;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.ORANGE;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.RED;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.MessageFormat;
import java.util.ArrayList;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.SceneryCampaign;
import br.com.battista.arcadiacaller.util.AndroidUtils;

public class CampaignDetailCompleteSceneryFragment extends BaseFragment {

    private static final String TAG = CampaignDetailCompleteSceneryFragment.class.getSimpleName();

    private Campaign campaign;

    private TextView txtAliasCampaign;
    private TextView txtLocationScenery;
    private TextView txtNameScenery;
    private TextView txtWonRewardScenery;
    private TextView txtWonTitleScenery;
    private MaterialBetterSpinner spnGuildWinner;

    public CampaignDetailCompleteSceneryFragment() {
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

    private void processNextAction(View view) {
        Log.d(TAG, "processNextAction: Process next action -> Activity MainActivity -> Fragment CampaignsFragment!");

        if (campaign == null) {
            AndroidUtils.snackbar(view, R.string.msg_error_campaign_not_found);
            return;
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
                }

                txtWonTitleScenery = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_won_title);
                if (!Strings.isNullOrEmpty(scenery.getWonTitle())) {
                    txtWonTitleScenery.setText(scenery.getWonTitle());
                } else {
                    txtWonTitleScenery.setText(R.string.none);
                }
            }

            loadGuildsImg(viewFragment);

            ArrayList<String> guilds = Lists.newArrayList(campaign.getGuild01(), campaign.getGuild02(), campaign.getGuild03(), campaign.getGuild04());
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_dropdown_item_1line, guilds);
            spnGuildWinner = (MaterialBetterSpinner)
                    viewFragment.findViewById(R.id.detail_card_view_scenery_winner);
            spnGuildWinner.setAdapter(arrayAdapter);
        }
    }

    public void onCheckboxLeastDeaths(View view){

    }

    private void loadGuildsImg(View viewFragment) {
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
