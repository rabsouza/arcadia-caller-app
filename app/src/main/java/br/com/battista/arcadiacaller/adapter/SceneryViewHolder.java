package br.com.battista.arcadiacaller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;

public class SceneryViewHolder extends RecyclerView.ViewHolder {

    private TextView txtTitle;

    private TextView txtDifficulty;

    private TextView txtWonReward;

    private TextView txtWonTitle;


    public SceneryViewHolder(View view) {
        super(view);

        txtTitle = (TextView) view.findViewById(R.id.card_view_scenery_title);
        txtDifficulty = (TextView) view.findViewById(R.id.card_view_scenery_difficulty);
        txtWonReward = (TextView) view.findViewById(R.id.card_view_scenery_won_reward);
        txtWonTitle = (TextView) view.findViewById(R.id.card_view_scenery_won_title);
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public TextView getTxtDifficulty() {
        return txtDifficulty;
    }

    public TextView getTxtWonReward() {
        return txtWonReward;
    }

    public TextView getTxtWonTitle() {
        return txtWonTitle;
    }
}
