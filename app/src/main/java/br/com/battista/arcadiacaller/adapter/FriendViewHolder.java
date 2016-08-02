package br.com.battista.arcadiacaller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;
import lombok.Getter;

public class FriendViewHolder extends RecyclerView.ViewHolder {

    @Getter
    private TextView txtTitle;

    @Getter
    private ImageView imgCost;

    @Getter
    private TextView txtType;

    @Getter
    private TextView txtGroup;

    public FriendViewHolder(View view) {
        super(view);

        txtTitle = (TextView) view.findViewById(R.id.card_view_card_title);
        txtType = (TextView) view.findViewById(R.id.card_view_card_type);
        txtGroup = (TextView) view.findViewById(R.id.card_view_card_group);
        imgCost = (ImageView) view.findViewById(R.id.card_view_card_cost);
    }

}
