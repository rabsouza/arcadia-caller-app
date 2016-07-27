package br.com.battista.arcadiacaller.fragment.detail;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.fragment.CampaignsFragment;
import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;

public class CampaignDetailGuildsFragment extends BaseFragment {

    private static final String TAG = CampaignsFragment.class.getSimpleName();

    public CampaignDetailGuildsFragment() {
    }

    public static CampaignDetailGuildsFragment newInstance() {
        CampaignDetailGuildsFragment fragment = new CampaignDetailGuildsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: Create detail guilds campaign!");
        View view = inflater.inflate(R.layout.fragment_campaign_detail_guilds, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_next_guilds_campaign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proccessNextAction(view);
            }
        });

        Glide.with(getContext())
                .load(NameGuildEnum.BLUE.getUrlImg())
                .crossFade()
                .into((ImageView) view.findViewById(R.id.detail_card_view_guilds_img_blue));

        Glide.with(getContext())
                .load(NameGuildEnum.GREEN.getUrlImg())
                .crossFade()
                .into((ImageView) view.findViewById(R.id.detail_card_view_guilds_img_green));

        Glide.with(getContext())
                .load(NameGuildEnum.ORANGE.getUrlImg())
                .crossFade()
                .into((ImageView) view.findViewById(R.id.detail_card_view_guilds_img_orange));

        Glide.with(getContext())
                .load(NameGuildEnum.RED.getUrlImg())
                .crossFade()
                .into((ImageView) view.findViewById(R.id.detail_card_view_guilds_img_red));

        return view;
    }

    private void proccessNextAction(View view) {

    }

}
