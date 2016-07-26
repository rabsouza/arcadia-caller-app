package br.com.battista.arcadiacaller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;
import lombok.Getter;

public class CampaignViewHolder extends RecyclerView.ViewHolder {

    @Getter
    private TextView txtTitle;

    public CampaignViewHolder(View view) {
        super(view);

        txtTitle = (TextView) view.findViewById(R.id.card_view_campaign_title);
    }

}
