package br.com.battista.arcadiacaller.fragment.detail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.fragment.CampaignsFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;
import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;

public class CampaignDetailGuildsFragment extends BaseFragment {

    private static final String TAG = CampaignsFragment.class.getSimpleName();

    private Campaign campaign;
    private EditText txtLogin01;
    private EditText txtLogin02;
    private EditText txtLogin03;
    private EditText txtLogin04;

    public CampaignDetailGuildsFragment() {
    }

    public static CampaignDetailGuildsFragment newInstance(Campaign campaign) {
        CampaignDetailGuildsFragment fragment = new CampaignDetailGuildsFragment();
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: Create detail guilds campaign!");
        final View viewFragment = inflater.inflate(R.layout.fragment_campaign_detail_guilds, container, false);

        FloatingActionButton fab = (FloatingActionButton) viewFragment.findViewById(R.id.fab_next_guilds_campaign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processNextAction(viewFragment);
            }
        });

        Glide.with(getContext())
                .load(NameGuildEnum.BLUE.getUrlImg())
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_guilds_img_blue));

        Glide.with(getContext())
                .load(NameGuildEnum.GREEN.getUrlImg())
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_guilds_img_green));

        Glide.with(getContext())
                .load(NameGuildEnum.ORANGE.getUrlImg())
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_guilds_img_orange));

        Glide.with(getContext())
                .load(NameGuildEnum.RED.getUrlImg())
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_guilds_img_red));

        processDataFragment(getArguments());

        return viewFragment;
    }

    private void processDataFragment(Bundle bundle) {
        Log.d(TAG, "processDataFragment: Processs bundle data Fragment!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);
        }
    }

    private void processNextAction(View view) {
        Log.d(TAG, "processNextAction: Process next action -> Fragment CampaignDetailSceneryFragment!");



        replaceDetailFragment(CampaignDetailSceneryFragment.newInstance(campaign, getLocationScenery()));
    }

    @NonNull
    private LocationSceneryEnum getLocationScenery() {
        LocationSceneryEnum locationScenery = LocationSceneryEnum.NONE;
        if (campaign == null) {
            locationScenery = LocationSceneryEnum.NONE;
        } else if (campaign.getScenery1() == null || campaign.getScenery2() == null || campaign.getScenery3() == null) {
            locationScenery = LocationSceneryEnum.OUT_CIRCLE;
        } else if (campaign.getScenery4() == null || campaign.getScenery5() == null) {
            locationScenery = LocationSceneryEnum.INNER_CIRCLE;
        } else if (campaign.getScenery6() == null) {
            locationScenery = LocationSceneryEnum.ULTIMATE;
        }
        return locationScenery;
    }

}
