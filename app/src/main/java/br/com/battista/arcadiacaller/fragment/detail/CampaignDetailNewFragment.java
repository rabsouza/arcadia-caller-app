package br.com.battista.arcadiacaller.fragment.detail;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.fragment.CampaignsFragment;


public class CampaignDetailNewFragment extends BaseFragment {

    private static final String TAG = CampaignsFragment.class.getSimpleName();

    public CampaignDetailNewFragment() {
    }

    public static CampaignDetailNewFragment newInstance() {
        CampaignDetailNewFragment fragment = new CampaignDetailNewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: Create detail new campaign!");
        View view = inflater.inflate(R.layout.fragment_campaign_detail_new, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_next_new_campaign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proccessNextAction(view);
            }
        });

        return view;
    }

    private void proccessNextAction(View view) {
        replaceDetailFragment(CampaignDetailGuildsFragment.newInstance());
    }

}
