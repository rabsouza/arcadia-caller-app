package br.com.battista.arcadiacaller.adapter;

import static com.activeandroid.Cache.getContext;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.adapter.item.GuildItemAdapter;
import br.com.battista.arcadiacaller.adapter.item.SceneryItemAdapter;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.dto.GuildDto;
import br.com.battista.arcadiacaller.model.dto.SceneryDto;
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
        view = LayoutInflater.from(context)
                .inflate(R.layout.adapter_campaign, viewGroup, false);

        return new CampaignViewHolder(view);
    }

    private void setupGuildsRecicleViewItem(View view, List<GuildDto> guildDtos) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.card_view_campaign_guilds_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(new GuildItemAdapter(getContext(), guildDtos));
    }

    private void setupSceneriesRecicleViewItem(View view, List<SceneryDto> sceneryDtos) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.card_view_campaign_sceneries_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(new SceneryItemAdapter(getContext(), sceneryDtos));
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

            List<GuildDto> guildDtos = campaign.generateGuildsDto();
            Log.i(TAG, MessageFormat.format("onBindViewHolder: Load {0} guildDtos to campaign: {1}!"
                    , guildDtos.size(), campaign.getKey()));
            setupGuildsRecicleViewItem(holder.itemView, guildDtos);

            List<SceneryDto> sceneryDtos = campaign.generateSceneriesDto();
            Log.i(TAG, MessageFormat.format("onBindViewHolder: Load {0} sceneryDtos to campaign: {1}!"
                    , sceneryDtos.size(), campaign.getKey()));
            setupSceneriesRecicleViewItem(holder.itemView, sceneryDtos);

        } else {
            Log.w(TAG, "onBindViewHolder: No content to holder!");
        }

    }

    @Override
    public int getItemCount() {
        return campaigns != null ? campaigns.size() : 0;
    }
}
