package br.com.battista.arcadiacaller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;
import lombok.Getter;

public class SceneryViewHolder extends RecyclerView.ViewHolder {

    @Getter
    private TextView txtTitle;

    @Getter
    private TextView txtDifficulty;

    @Getter
    private TextView txtWonReward;

    @Getter
    private TextView txtWonTitle;


    public SceneryViewHolder(View view) {
        super(view);

        txtTitle = (TextView) view.findViewById(R.id.card_view_scenery_title);
        txtDifficulty = (TextView) view.findViewById(R.id.card_view_scenery_difficulty);
        txtWonReward = (TextView) view.findViewById(R.id.card_view_scenery_won_reward);
        txtWonTitle = (TextView) view.findViewById(R.id.card_view_scenery_won_title);
    }

}
