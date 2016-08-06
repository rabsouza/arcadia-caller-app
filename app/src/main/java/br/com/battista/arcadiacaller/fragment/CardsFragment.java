package br.com.battista.arcadiacaller.fragment;


import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_CARD_DATA;

import android.os.AsyncTask;
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
import br.com.battista.arcadiacaller.adapter.CardAdapter;
import br.com.battista.arcadiacaller.cache.EventCache;
import br.com.battista.arcadiacaller.model.Card;
import br.com.battista.arcadiacaller.model.enuns.GroupCardEnum;


public class CardsFragment extends BaseFragment {

    private static final String TAG = CardsFragment.class.getSimpleName();

    private List<Card> cards = Lists.newArrayList();
    private RecyclerView recyclerView;

    public CardsFragment() {
    }

    public static CardsFragment newInstance() {
        CardsFragment fragment = new CardsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Create new fragment Card!");
        View view = inflater.inflate(R.layout.fragment_cards, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        loadGroupCards(view);

        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                EventCache.createEvent(LOAD_CARD_DATA);
                refreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    private void loadGroupCards(View view) {
        final List<String> groupsCard = Lists.newArrayList();
        for (GroupCardEnum groupCard : GroupCardEnum.values()) {
            final String textGroupCard = getContext().getString(groupCard.getDescRes());
            groupsCard.add(textGroupCard);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, groupsCard);
        MaterialBetterSpinner spnGroupCard = (MaterialBetterSpinner)
                view.findViewById(R.id.card_recycler_view_group);
        spnGroupCard.setAdapter(arrayAdapter);
        spnGroupCard.setText(groupsCard.get(0));
        spnGroupCard.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, MessageFormat.format("onItemClick: Click {0} group name!", i));

                final GroupCardEnum groupCardEnum = GroupCardEnum.get(i);
                Log.i(TAG, MessageFormat.format("onItemClick: Filter cards by group: {0}.", groupCardEnum));
                loadCards(groupCardEnum);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadCards(GroupCardEnum.NONE);
    }

    public void loadCards(final GroupCardEnum groupCard) {
        Log.i(TAG, "loadCards: Load all cards!");

        new AsyncTask<Void, Integer, Boolean>() {

            @Override
            protected void onPostExecute(Boolean result) {
                recyclerView.setAdapter(new CardAdapter(getContext(), cards));
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    String token = MainApplication.instance().getToken();
                    if (groupCard != null && !groupCard.equals(GroupCardEnum.NONE)) {
                        cards = Inject.provideCardService().findByGroup(token, groupCard);
                    } else {
                        cards = Inject.provideCardService().findAll(token);
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
