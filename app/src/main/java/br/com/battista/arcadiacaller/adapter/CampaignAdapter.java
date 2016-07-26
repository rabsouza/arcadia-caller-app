package br.com.battista.arcadiacaller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.util.AndroidUtils;

public class CampaignAdapter extends RecyclerView.Adapter<CampaignViewHolder> {
    private static final String TAG = CampaignAdapter.class.getSimpleName();

    private Context context;
    private List<Campaign> campaigns;
    private View view;

    public CampaignAdapter(Context context, List<Campaign> campaigns) {
        this.context = context;
        this.campaigns = campaigns;
    }

    @Override
    public CampaignViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.adapter_campaign, viewGroup, false);
        return new CampaignViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CampaignViewHolder holder, int position) {
        if (campaigns != null && !campaigns.isEmpty()) {
            final Campaign campaign = campaigns.get(position);
            Log.i(TAG, String.format(
                    "onBindViewHolder: Fill to row position: %S with %s.", position, campaign));

            holder.getTxtTitle().setText(campaign.getAlias());
            holder.getTxtKey().setText(campaign.getKey());

            holder.getBtnDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AndroidUtils.toast(context, R.string.msg_blank_fragment);
                }
            });

            holder.getBtnEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AndroidUtils.toast(context, R.string.msg_blank_fragment);
                }
            });

            holder.getBtnPlay().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AndroidUtils.toast(context, R.string.msg_blank_fragment);
                }
            });

            holder.getBtnFinish().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AndroidUtils.toast(context, R.string.msg_blank_fragment);
                }
            });
        } else {
            Log.w(TAG, "onBindViewHolder: No content to holder!");
        }

    }

    @Override
    public int getItemCount() {
        return campaigns != null ? campaigns.size() : 0;
    }
}
