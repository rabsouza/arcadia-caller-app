package br.com.battista.arcadiacaller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;
import lombok.Getter;

public class CardViewHolder extends RecyclerView.ViewHolder {

    @Getter
    private TextView txtTitle;

    @Getter
    private ImageView imgCost;

    @Getter
    private TextView txtTypeEffect;

    @Getter
    private TextView txtGroupEffect;

    @Getter
    private TextView txtGroup;

    public CardViewHolder(View view) {
        super(view);

        txtTitle = (TextView) view.findViewById(R.id.card_view_card_title);
        txtTypeEffect = (TextView) view.findViewById(R.id.card_view_card_type_effect);
        txtGroupEffect = (TextView) view.findViewById(R.id.card_view_card_group_effect);
        txtGroup = (TextView) view.findViewById(R.id.card_view_card_group);
        imgCost = (ImageView) view.findViewById(R.id.card_view_card_cost);
    }

}
