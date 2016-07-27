package br.com.battista.arcadiacaller.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.battista.arcadiacaller.R;


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
        return inflater.inflate(R.layout.fragment_campaign_detail_new, container, false);
    }

}
