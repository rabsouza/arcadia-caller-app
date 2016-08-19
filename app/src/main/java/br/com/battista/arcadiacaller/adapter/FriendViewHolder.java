package br.com.battista.arcadiacaller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;

public class FriendViewHolder extends RecyclerView.ViewHolder {

    private TextView txtUsername;
    private ImageView imgAvatar;

    public FriendViewHolder(View view) {
        super(view);

        txtUsername = (TextView) view.findViewById(R.id.card_view_friend_username);
        imgAvatar = (ImageView) view.findViewById(R.id.card_view_friend_img);
    }

    public TextView getTxtUsername() {
        return txtUsername;
    }

    public ImageView getImgAvatar() {
        return imgAvatar;
    }
}
