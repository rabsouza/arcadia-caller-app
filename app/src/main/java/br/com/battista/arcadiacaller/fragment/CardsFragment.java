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

import com.google.common.collect.Lists;

import java.util.List;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.adapter.CardAdapter;
import br.com.battista.arcadiacaller.cache.EventCache;
import br.com.battista.arcadiacaller.model.Card;


public class CardsFragment extends BaseFragment {

    private static final String TAG = CardsFragment.class.getSimpleName();

    private List<Card> cards = Lists.newArrayList();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

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

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                EventCache.createEvent(LOAD_CARD_DATA);
                refreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadCards();
    }

    public void loadCards() {
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
                    cards = Inject.provideCardService().findAll(token);
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }
        }.execute();
    }

}
