package br.com.battista.arcadiacaller.fragment;


import static br.com.battista.arcadiacaller.constants.FriendConstant.URL_AVATAR;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.text.MessageFormat;
import java.util.Set;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.adapter.FriendAdapter;
import br.com.battista.arcadiacaller.constants.FriendConstant;
import br.com.battista.arcadiacaller.constants.ProfileAppConstant;
import br.com.battista.arcadiacaller.exception.EntityAlreadyExistsException;
import br.com.battista.arcadiacaller.exception.ValidatorException;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.model.dto.FriendDto;
import br.com.battista.arcadiacaller.service.LoginService;
import br.com.battista.arcadiacaller.service.UserService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

public class FriendsFragment extends BaseFragment {

    private static final String TAG = FriendsFragment.class.getSimpleName();

    private Set<FriendDto> friends = Sets.newTreeSet();
    private RecyclerView recyclerView;
    private ImageButton btnAdd;
    private EditText txtUsername;
    private SwipeRefreshLayout refreshLayout;


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
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
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

        refreshLayout = (SwipeRefreshLayout) viewFragment.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadFriends();
                refreshLayout.setRefreshing(false);
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
                    recyclerView.setAdapter(new FriendAdapter(getActivity(), getContext(), Lists.newArrayList(friends)));
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
                            loadAllFriend(user, token);
                            friends.clear();
                            friends.addAll(user.getFriendsDto());
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

            private void loadAllFriend(User user, String token) {
                if (user != null && !user.getFriends().isEmpty()) {
                    UserService userService = Inject.provideUserService();

                    for (String friend : user.getFriends()) {

                        final User userFriend = userService.findByUsername(token, friend);
                        if (userFriend != null) {
                            final String urlAvatar;
                            if (ProfileAppConstant.FRIEND.equals(userFriend.getProfile())) {
                                urlAvatar = URL_AVATAR;
                            } else {
                                urlAvatar = userFriend.getUrlAvatar();
                            }
                            final FriendDto friendDto = new FriendDto().username(userFriend.getUsername()).urlAvatar(urlAvatar).profile(userFriend.getProfile());
                            user.addFriend(friendDto);
                        }
                    }
                }
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
                    recyclerView.setAdapter(new FriendAdapter(getActivity(), getContext(), Lists.newArrayList(friends)));
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
                    if (user != null && user.getFriends() != null) {
                        instance.setUser(user);
                        loadAllFriend(user, token);
                        friends.addAll(user.getFriendsDto());
                    } else if (userMain.getFriends() != null) {
                        friends.addAll(userMain.getFriendsDto());
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }

            private void loadAllFriend(User user, String token) {
                Log.i(TAG, "loadAllFriend: Load all friends!");
                if (user != null && !user.getFriends().isEmpty()) {
                    UserService userService = Inject.provideUserService();

                    for (String friend : user.getFriends()) {

                        final User userFriend = userService.findByUsername(token, friend);
                        if (userFriend != null) {
                            final String urlAvatar;
                            if (ProfileAppConstant.FRIEND.equals(userFriend.getProfile())) {
                                urlAvatar = URL_AVATAR;
                            } else {
                                urlAvatar = userFriend.getUrlAvatar();
                            }
                            final FriendDto friendDto = new FriendDto().username(userFriend.getUsername()).urlAvatar(urlAvatar).profile(userFriend.getProfile());
                            user.addFriend(friendDto);
                        }
                    }
                }
            }
        }.execute();

    }

}
