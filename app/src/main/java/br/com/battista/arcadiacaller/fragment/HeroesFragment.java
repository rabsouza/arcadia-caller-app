package br.com.battista.arcadiacaller.fragment;


import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_HERO_DATA;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.common.collect.Lists;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.adapter.HeroAdapter;
import br.com.battista.arcadiacaller.cache.EventCache;
import br.com.battista.arcadiacaller.model.Hero;
import br.com.battista.arcadiacaller.model.enuns.GroupHeroEnum;
import br.com.battista.arcadiacaller.util.ProgressApp;


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

        recyclerView = (RecyclerView) view.findViewById(R.id.hero_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        loadGroupHeroes(view);

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

    private void loadGroupHeroes(View view) {
        final List<String> groupsHero = Lists.newArrayList();
        for (GroupHeroEnum groupHero : GroupHeroEnum.values()) {
            final String textGroupHero = getContext().getString(groupHero.getDescRes());
            groupsHero.add(textGroupHero);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, groupsHero);
        MaterialBetterSpinner spnGroupHero = (MaterialBetterSpinner)
                view.findViewById(R.id.hero_recycler_view_group);
        spnGroupHero.setAdapter(arrayAdapter);
        spnGroupHero.setText(groupsHero.get(0));
        spnGroupHero.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, MessageFormat.format("onItemClick: Click {0} group name!", i));

                final GroupHeroEnum groupHeroEnum = GroupHeroEnum.get(i);
                Log.i(TAG, MessageFormat.format("onItemClick: Filter heroes by group: {0}.", groupHeroEnum));
                loadHeroes(groupHeroEnum);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadHeroes(GroupHeroEnum.NONE);
    }

    public void loadHeroes(final GroupHeroEnum groupHero) {
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
                    if (groupHero != null && !groupHero.equals(GroupHeroEnum.NONE)) {
                        heroes = Inject.provideHeroService().findByGroup(token, groupHero);
                    } else {
                        heroes = Inject.provideHeroService().findAll(token);
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }
        }.execute();
    }

}
