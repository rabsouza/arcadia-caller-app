package br.com.battista.arcadiacaller.fragment;


import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_SCENERY_DATA;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import br.com.battista.arcadiacaller.adapter.SceneryAdapter;
import br.com.battista.arcadiacaller.cache.EventCache;
import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.util.ProgressApp;

public class SceneriesFragment extends BaseFragment {

    private static final String TAG = SceneriesFragment.class.getSimpleName();

    private List<Scenery> sceneries = Lists.newArrayList();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    public SceneriesFragment() {
    }

    public static SceneriesFragment newInstance() {
        SceneriesFragment fragment = new SceneriesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Create new fragment Scenery!");
        View view = inflater.inflate(R.layout.fragment_sceneries, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                EventCache.createEvent(LOAD_SCENERY_DATA);
                refreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadSceneries();
    }

    public void loadSceneries() {
        Log.i(TAG, "loadSceneries: Load all sceneries!");

        new ProgressApp(this.getActivity(), R.string.msg_action_loading, false) {

            @Override
            protected void onPostExecute(Boolean result) {
                recyclerView.setAdapter(new SceneryAdapter(getContext(), sceneries));
                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    String token = MainApplication.instance().getToken();
                    sceneries = Inject.provideSceneryService().findAll(token);
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }
        }.execute();
    }

}
