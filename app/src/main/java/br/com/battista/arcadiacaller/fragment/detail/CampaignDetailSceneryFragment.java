package br.com.battista.arcadiacaller.fragment.detail;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.activity.MainActivity;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.fragment.CampaignsFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.enuns.ActionEnum;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;

public class CampaignDetailSceneryFragment extends BaseFragment {

    private static final String TAG = CampaignsFragment.class.getSimpleName();

    private Campaign campaign;
    private LocationSceneryEnum locationScenery;

    public CampaignDetailSceneryFragment() {
    }

    public static CampaignDetailSceneryFragment newInstance(Campaign campaign, LocationSceneryEnum locationScenery) {
        CampaignDetailSceneryFragment fragment = new CampaignDetailSceneryFragment();
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        args.putSerializable(BundleConstant.FILTER_LOCATION_SCENERY, locationScenery);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: Create detail scenery campaign!");
        final View viewFragment = inflater.inflate(R.layout.fragment_campaign_detail_scenery, container, false);

        FloatingActionButton fab = (FloatingActionButton) viewFragment.findViewById(R.id.fab_done_scenery_campaign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processNextAction(viewFragment);
            }
        });

        processDataFragment(getArguments());

        return viewFragment;
    }

    private void processNextAction(View view) {
        Log.d(TAG, "processNextAction: Process next action -> Fragment CampaignDetailSceneryFragment!");

        Bundle args = new Bundle();
        args.putString(BundleConstant.ACTION, ActionEnum.START_FRAGMENT_CAMPAIGNS.name());
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

    private void processDataFragment(Bundle bundle) {
        Log.d(TAG, "processDataFragment: Processs bundle data Fragment!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);
        }

        if (bundle.containsKey(BundleConstant.FILTER_LOCATION_SCENERY)) {
            locationScenery = (LocationSceneryEnum) bundle.getSerializable(BundleConstant.FILTER_LOCATION_SCENERY);
        }
    }

}
