package br.com.battista.arcadiacaller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;

public class FriendViewHolder extends RecyclerView.ViewHolder {

    private TextView txtUSername;

    public FriendViewHolder(View view) {
        super(view);

        txtUSername = (TextView) view.findViewById(R.id.card_view_friend_username);
    }

    public TextView getTxtUSername() {
        return txtUSername;
    }
}
