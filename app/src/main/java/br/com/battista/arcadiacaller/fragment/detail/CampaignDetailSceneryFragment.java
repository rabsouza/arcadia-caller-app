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
import br.com.battista.arcadiacaller.model.enuns.ActionEnum;

public class CampaignDetailSceneryFragment extends BaseFragment {

    private static final String TAG = CampaignsFragment.class.getSimpleName();

    public CampaignDetailSceneryFragment() {
    }

    public static CampaignDetailSceneryFragment newInstance() {
        CampaignDetailSceneryFragment fragment = new CampaignDetailSceneryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: Create detail scenery campaign!");
        View view = inflater.inflate(R.layout.fragment_campaign_detail_scenery, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_done_scenery_campaign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proccessNextAction(view);
            }
        });

        return view;
    }

    private void proccessNextAction(View view) {
        Bundle args = new Bundle();
        args.putString(BundleConstant.ACTION, ActionEnum.START_FRAGMENT_CAMPAIGNS.name());
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }


}
