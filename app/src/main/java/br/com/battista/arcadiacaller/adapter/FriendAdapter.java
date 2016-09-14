package br.com.battista.arcadiacaller.adapter;

import static br.com.battista.arcadiacaller.constants.FriendConstant.URL_AVATAR;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.ProfileAppConstant;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.model.dto.FriendDto;
import br.com.battista.arcadiacaller.service.UserService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ImageLoadUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

public class FriendAdapter extends RecyclerView.Adapter<FriendViewHolder> {
    private static final String TAG = FriendAdapter.class.getSimpleName();

    private Activity activity;
    private Context context;
    private List<FriendDto> friends;
    private View viewCurrent;

    public FriendAdapter(Activity activity, Context context, List<FriendDto> friends) {
        this.activity = activity;
        this.context = context;
        this.friends = friends;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        viewCurrent = LayoutInflater.from(context)
                .inflate(R.layout.adapter_friend, viewGroup, false);
        return new FriendViewHolder(viewCurrent);
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {
        if (friends != null && !friends.isEmpty()) {
            final FriendDto friend = friends.get(position);
            Log.i(TAG, String.format(
                    "onBindViewHolder: Fill to row position: %S with %s.", position, friend));

            final TextView txtUsername = holder.getTxtUsername();
            txtUsername.setText(friend.getUsername());
            if (ProfileAppConstant.FRIEND.equals(friend.getProfile())) {
                txtUsername.setTypeface(txtUsername.getTypeface(), Typeface.ITALIC);
            }

            ImageLoadUtils
                    .loadImage(context,
                            friend.getUrlAvatar(),
                            holder.getImgAvatar());

            final RecyclerView.Adapter adapterCurrent = this;
            final int positionRemoved = holder.getAdapterPosition();
            holder.getBtnRemove().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createDialogRemoveFriend(friend.getUsername(), positionRemoved, adapterCurrent, viewCurrent);
                }
            });
        } else {
            Log.w(TAG, "onBindViewHolder: No content to holder!");
        }

    }

    private void createDialogRemoveFriend(final String friend, final int position, final RecyclerView.Adapter adapterCurrent, final View viewCurrent) {
        String msgDelete = context.getResources().getString(R.string.alert_confirmation_dialog_text_remove);
        new AlertDialog.Builder(context)
                .setTitle(R.string.alert_confirmation_dialog_title_delete)
                .setMessage(MessageFormat.format(msgDelete, friend))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.btn_confirmation_dialog_remove, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        new ProgressApp(activity, R.string.msg_action_removing, false) {
                            @Override
                            protected Boolean doInBackground(Void... voids) {
                                try {
                                    Log.i(TAG, "onClick: Remove the the friend to user!");
                                    MainApplication instance = MainApplication.instance();
                                    String token = instance.getToken();
                                    User user = instance.getUser();
                                    user.removeFriend(friend);

                                    UserService userService = Inject.provideUserService();
                                    user = userService.addFriends(token, user);
                                    if (user != null) {
                                        instance.setUser(user);
                                        loadAllFriend(user, token);
                                        friends.clear();
                                        friends.addAll(user.getFriendsDto());
                                        return true;
                                    }
                                } catch (Exception e) {
                                    Log.e(TAG, e.getLocalizedMessage(), e);
                                }
                                return false;
                            }

                            @Override
                            protected void onPostExecute(Boolean result) {
                                if (result) {
                                    Log.d(TAG, "onClick: Success remove the friend to user and refresh recyclerView!");
                                    adapterCurrent.notifyItemRemoved(position);
                                    adapterCurrent.notifyDataSetChanged();
                                } else {
                                    Log.d(TAG, "onClick: Error remove the friend to user!");
                                    AndroidUtils.snackbar(viewCurrent, R.string.msg_error_delete_campaign);
                                }
                                dismissProgress();
                            }

                        }.execute();

                    }
                })
                .setNegativeButton(R.string.btn_confirmation_dialog_cancel, null).show();
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

    @Override
    public int getItemCount() {
        return friends != null ? friends.size() : 0;
    }
}
