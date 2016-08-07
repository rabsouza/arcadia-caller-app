package br.com.battista.arcadiacaller.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.collect.Lists;

import java.util.List;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.activity.CampaignDetailActivity;
import br.com.battista.arcadiacaller.adapter.CampaignAdapter;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;


public class CampaignsFragment extends BaseFragment {

    private static final String TAG = CampaignsFragment.class.getSimpleName();

    private List<Campaign> campaigns = Lists.newArrayList();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    public CampaignsFragment() {
    }

    public static CampaignsFragment newInstance() {
        CampaignsFragment fragment = new CampaignsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Create new fragment Campaign!");
        View view = inflater.inflate(R.layout.fragment_campaign, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.campaign_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                loadCampaigns();
            }
        });

        loadFloatingAction(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadCampaigns();
    }

    private void loadFloatingAction(View view) {
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_new_campaign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadCampaignDetailActivity();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void loadCampaignDetailActivity() {
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, new Campaign());
        Intent intent = new Intent(getContext(), CampaignDetailActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

    public void loadCampaigns() {
        Log.i(TAG, "loadCampaigns: Load all campaigns!");

        new ProgressApp(this.getActivity(), R.string.msg_action_loading, false) {

            @Override
            protected void onPostExecute(Boolean result) {
                CampaignAdapter adapter = new CampaignAdapter(getActivity(), getContext(), campaigns);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                if (campaigns.isEmpty()) {
                    AndroidUtils.snackbar(getView(), R.string.msg_empty_campaigns);
                }
                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    MainApplication application = MainApplication.instance();
                    String token = application.getToken();
                    String username = application.getUser().getUsername();
                    campaigns = Inject.provideCampaignService().findByUser(token, username);
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }
        }.execute();
    }

}
