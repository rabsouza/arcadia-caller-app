package br.com.battista.arcadiacaller.fragment;


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
import br.com.battista.arcadiacaller.adapter.HeroAdapter;
import br.com.battista.arcadiacaller.cache.EventCache;
import br.com.battista.arcadiacaller.model.Hero;
import br.com.battista.arcadiacaller.util.ProgressApp;

import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_HERO_DATA;


public class HeroesFragment extends BaseFragment {

    private static final String TAG = HeroesFragment.class.getSimpleName();

    private List<Hero> heroes = Lists.newArrayList();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    public HeroesFragment() {
    }

    public static HeroesFragment newInstance() {
        HeroesFragment fragment = new HeroesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Create new fragment Hero!");
        View view = inflater.inflate(R.layout.fragment_heroes, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                EventCache.createEvent(LOAD_HERO_DATA);
                refreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadHeroes();
    }

    public void loadHeroes() {
        Log.i(TAG, "loadHeroes: Load all heroes!");

        new ProgressApp(this.getActivity(), R.string.msg_action_loading, false) {

            @Override
            protected void onPostExecute(Boolean result) {
                recyclerView.setAdapter(new HeroAdapter(getContext(), heroes));
                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    String token = MainApplication.instance().getToken();
                    heroes = Inject.provideHeroService().findAll(token);
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }
        }.execute();
    }

}
