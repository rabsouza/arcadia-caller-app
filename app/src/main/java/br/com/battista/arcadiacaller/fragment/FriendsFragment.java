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
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.adapter.FriendAdapter;
import br.com.battista.arcadiacaller.constants.FriendConstant;
import br.com.battista.arcadiacaller.exception.EntityAlreadyExistsException;
import br.com.battista.arcadiacaller.exception.ValidatorException;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.service.LoginService;
import br.com.battista.arcadiacaller.service.UserService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

public class FriendsFragment extends BaseFragment {

    private static final String TAG = FriendsFragment.class.getSimpleName();

    private List<String> friends = Lists.newArrayList();
    private RecyclerView recyclerView;
    private ImageButton btnAdd;
    private EditText txtUsername;

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
        final View viewFragment = inflater.inflate(R.layout.fragment_friends, container, false);

        recyclerView = (RecyclerView) viewFragment.findViewById(R.id.friends_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);

        btnAdd = (ImageButton) viewFragment.findViewById(R.id.card_view_campaign_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewFriend(viewFragment);
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });

        return viewFragment;
    }

    private void addNewFriend(View view) {
        Log.i(TAG, "addNewFriend: Add new friend!");

        txtUsername = (EditText) view.findViewById(R.id.card_view_friends_username);
        if (Strings.isNullOrEmpty(txtUsername.getText().toString())) {
            String msgErrorUsername = getContext().getString(R.string.msg_username_required);
            AndroidUtils.changeErrorEditText(txtUsername, msgErrorUsername, true);
            return;
        }
        AndroidUtils.changeErrorEditText(txtUsername);
        final String username = txtUsername.getText().toString().trim();

        Log.d(TAG, MessageFormat.format("Create new friend with username: {0}.", username));

        final View currentView = view;
        new ProgressApp(getActivity(), R.string.msg_action_saving, false) {
            private Boolean alreadyExistseUser = Boolean.FALSE;

            @Override
            protected void onPostExecute(Boolean result) {
                if (!result || alreadyExistseUser) {
                    Log.d(TAG, "onPostExecute: Failed in create user!");
                    AndroidUtils.snackbar(currentView, R.string.msg_failed_already_exists_friend);
                } else {
                    txtUsername.setText("");
                    recyclerView.setAdapter(new FriendAdapter(getContext(), friends));
                    Log.d(TAG, "onPostExecute: Success in create user!");
                }
                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    LoginService loginService = Inject.provideLoginService();
                    UserService userService = Inject.provideUserService();

                    MainApplication instance = MainApplication.instance();
                    String token = instance.getToken();
                    User user = instance.getUser();

                    Log.d(TAG, MessageFormat.format(
                            "doInBackground: create to friend with username: {0}.", username));

                    User userBuild = new User()
                            .username(username)
                            .mail(FriendConstant.MAIL_FRIEND)
                            .profile(FriendConstant.PROFILE_FRIEND);

                    User userFriend = null;
                    if (userService.existsUsername(token, username)) {
                        Log.i(TAG, "doInBackground: Add a friend existing!");
                        userFriend = userService.findByUsername(token, username);
                    } else {
                        Log.i(TAG, "doInBackground: Create new friend!");
                        userFriend = loginService.create(userBuild);
                    }

                    if (userFriend != null) {
                        Log.d(TAG, MessageFormat.format(
                                "doInBackground: Add to friend {0} the user: {1}.", username, user.getUsername()));
                        user.addFriend(username);

                        user = userService.addFriends(token, user);
                        if (user != null) {
                            instance.setUser(user);
                            friends.clear();
                            friends.addAll(user.getFriends());
                            return true;
                        }
                    }
                } catch (EntityAlreadyExistsException e) {
                    Log.e(TAG, "EntityAlreadyExistsException: Error create new user!", e);
                    alreadyExistseUser = true;
                } catch (ValidatorException e) {
                    Log.e(TAG, "ValidatorException: Error create new user!", e);
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                }
                return false;
            }
        }.execute();
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
                if (friends == null || friends.isEmpty()) {
                    AndroidUtils.snackbar(getView(), R.string.msg_empty_friends);
                } else {
                    recyclerView.setAdapter(new FriendAdapter(getContext(), friends));
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
                    friends.clear();
                    if (user != null) {
                        instance.setUser(user);
                        friends.addAll(user.getFriends());
                    } else {
                        friends.addAll(userMain.getFriends());
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
