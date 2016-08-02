package br.com.battista.arcadiacaller.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
import br.com.battista.arcadiacaller.adapter.FriendAdapter;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

public class FriendsFragment extends BaseFragment {

    private static final String TAG = FriendsFragment.class.getSimpleName();

    private List<String> friends = Lists.newArrayList();
    private RecyclerView recyclerView;

    public FriendsFragment() {
    }

    public static FriendsFragment newInstance() {
        FriendsFragment fragment = new FriendsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Create new fragment User!");
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.friends_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadFriends();
    }

    public void loadFriends() {
        Log.i(TAG, "loadFriends: Load all friends!");

        new ProgressApp(this.getActivity(), R.string.msg_action_loading, false) {

            @Override
            protected void onPostExecute(Boolean result) {
                recyclerView.setAdapter(new FriendAdapter(getContext(), friends));
                if (friends == null || friends.isEmpty()) {
                    AndroidUtils.snackbar(getView(), R.string.msg_empty_friends);
                }
                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    final MainApplication instance = MainApplication.instance();
                    String token = instance.getToken();
                    final User userMain = instance.getUser();
                    User user = Inject.provideUserService().findByUsername(token, userMain.getUsername());
                    if (user != null) {
                        instance.setUser(user);
                        friends = user.getFriends();
                    } else {
                        friends = userMain.getFriends();
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
