package br.com.battista.arcadiacaller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;
import lombok.Getter;

public class CampaignViewHolder extends RecyclerView.ViewHolder {

    @Getter
    private TextView txtTitle;

    @Getter
    private TextView txtKey;

    @Getter
    private TextView txtDate;

    @Getter
    private TextView txtStatus;

    @Getter
    private ImageButton btnFinish;

    @Getter
    private ImageButton btnEdit;

    @Getter
    private ImageButton btnDelete;

    @Getter
    private ImageButton btnPlay;

    @Getter
    private ImageButton btnInfo;

    public CampaignViewHolder(View view) {
        super(view);

        txtTitle = (TextView) view.findViewById(R.id.card_view_campaign_title);
        txtKey = (TextView) view.findViewById(R.id.card_view_campaign_key);
        txtDate = (TextView) view.findViewById(R.id.card_view_campaign_date);
        txtStatus = (TextView) view.findViewById(R.id.card_view_campaign_status);

        btnFinish = (ImageButton) view.findViewById(R.id.card_view_campaign_finish);
        btnPlay = (ImageButton) view.findViewById(R.id.card_view_campaign_play);
        btnEdit = (ImageButton) view.findViewById(R.id.card_view_campaign_edit);
        btnDelete = (ImageButton) view.findViewById(R.id.card_view_campaign_delete);
        btnInfo = (ImageButton) view.findViewById(R.id.card_view_campaign_info);
    }

}
