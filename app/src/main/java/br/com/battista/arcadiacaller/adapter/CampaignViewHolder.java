package br.com.battista.arcadiacaller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;

public class CampaignViewHolder extends RecyclerView.ViewHolder {

    private TextView txtTitle;

    private TextView txtKey;

    private TextView txtDate;

    private TextView txtStatus;

    private ImageButton btnFinish;

    private ImageButton btnEdit;

    private ImageButton btnDelete;

    private ImageButton btnPlay;

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

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public TextView getTxtKey() {
        return txtKey;
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public TextView getTxtStatus() {
        return txtStatus;
    }

    public ImageButton getBtnFinish() {
        return btnFinish;
    }

    public ImageButton getBtnEdit() {
        return btnEdit;
    }

    public ImageButton getBtnDelete() {
        return btnDelete;
    }

    public ImageButton getBtnPlay() {
        return btnPlay;
    }

    public ImageButton getBtnInfo() {
        return btnInfo;
    }
}
