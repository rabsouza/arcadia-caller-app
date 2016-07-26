package br.com.battista.arcadiacaller.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.collect.Lists;

import java.util.List;

import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.adapter.CampaignAdapter;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.util.ProgressApp;


/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignsFragment extends BaseFragment {

    private static final String TAG = CampaignsFragment.class.getSimpleName();

    private List<Campaign> campaigns = Lists.newArrayList();
    private RecyclerView recyclerView;

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

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadCampaigns();
    }

    public void loadCampaigns() {
        Log.i(TAG, "loadCampaigns: Load all campaigns!");

        new ProgressApp(this.getActivity(), R.string.msg_action_loading, false) {

            @Override
            protected void onPostExecute(Boolean result) {
                recyclerView.setAdapter(new CampaignAdapter(getContext(), campaigns));
                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    String token = MainApplication.instance().getToken();
                    //campaigns = Inject.provideCampaignService().findAll(token);
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }
        }.execute();
    }

}
