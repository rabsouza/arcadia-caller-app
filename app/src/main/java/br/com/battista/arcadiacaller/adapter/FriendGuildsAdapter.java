package br.com.battista.arcadiacaller.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Strings;

import java.util.List;

import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.ProfileAppConstant;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.model.dto.FriendDto;
import br.com.battista.arcadiacaller.util.ImageLoadUtils;

public class FriendGuildsAdapter extends RecyclerView.Adapter<FriendViewHolder> {
    private static final String TAG = FriendGuildsAdapter.class.getSimpleName();

    private Context context;
    private List<FriendDto> friends;

    public FriendGuildsAdapter(Context context, List<FriendDto> friends) {
        this.context = context;
        this.friends = friends;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.adapter_friend_guilds, viewGroup, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {
        if (friends != null && !friends.isEmpty()) {
            final FriendDto friend = friends.get(position);
            Log.i(TAG, String.format(
                    "onBindViewHolder: Fill to row position: %S with %s.", position, friend));

            final TextView txtUsername = holder.getTxtUsername();
            txtUsername.setText(friend.getUsername());

            User user = MainApplication.instance().getUser();
            if (ProfileAppConstant.FRIEND.equals(friend.getProfile())) {
                txtUsername.setTypeface(txtUsername.getTypeface(), Typeface.ITALIC);
            } else if (user != null && !Strings.isNullOrEmpty(user.getUsername()) && user.getUsername().equals(friend.getUsername())) {
                txtUsername.setTypeface(txtUsername.getTypeface(), Typeface.BOLD);
            }

            ImageLoadUtils
                    .loadImage(context,
                            friend.getUrlAvatar(),
                            holder.getImgAvatar());
        } else {
            Log.w(TAG, "onBindViewHolder: No content to holder!");
        }

    }

    @Override
    public int getItemCount() {
        return friends != null ? friends.size() : 0;
    }
}
