package br.com.battista.arcadiacaller.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.collect.Lists;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.activity.CampaingDetailActivity;
import br.com.battista.arcadiacaller.adapter.item.GuildItemAdapter;
import br.com.battista.arcadiacaller.adapter.item.SceneryItemAdapter;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.dto.GuildDto;
import br.com.battista.arcadiacaller.model.dto.SceneryDto;
import br.com.battista.arcadiacaller.model.enuns.CampaignStatusEnum;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import lombok.Getter;

import static br.com.battista.arcadiacaller.model.enuns.CampaignStatusEnum.*;

public class CampaignAdapter extends RecyclerView.Adapter<CampaignViewHolder> {

    private static final String TAG = CampaignAdapter.class.getSimpleName();

    @Getter
    private Context context;

    @Getter
    private Activity activity;

    private List<Campaign> campaigns;
    private View viewCampaign;

    private RecyclerView recyclerView;

    public CampaignAdapter(Activity activity, Context context, List<Campaign> campaigns) {
        this.activity = activity;
        this.context = context;
        this.campaigns = campaigns;
    }

    @Override
    public CampaignViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        viewCampaign = LayoutInflater.from(context)
                .inflate(R.layout.adapter_campaign, viewGroup, false);

        return new CampaignViewHolder(viewCampaign);
    }

    private void setupGuildsRecicleViewItem(View view, List<GuildDto> guildDtos) {
        recyclerView = (RecyclerView) view.findViewById(R.id.card_view_campaign_guilds_recycler_view);
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
    public void onBindViewHolder(CampaignViewHolder holder, final int position) {
        if (campaigns != null && !campaigns.isEmpty()) {
            final Campaign campaign = campaigns.get(position);
            Log.i(TAG, String.format(
                    "onBindViewHolder: Fill to row position: %S with %s.", position, campaign));

            final CampaignStatusEnum statusCurrent = campaign.getStatusCurrent();
            Log.i(TAG, MessageFormat.format("onBindViewHolder: Current status campaign", statusCurrent));

            holder.getTxtTitle().setText(campaign.getAlias());
            holder.getTxtKey().setText(campaign.getKey());

            final RecyclerView.Adapter adapterCurrent = this;
            final View viewCurrent = viewCampaign;

            holder.getBtnDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (CREATED_CAMPAIGN.equals(statusCurrent)) {
                        createDialogDeleteCampaign(campaign, position, adapterCurrent, viewCurrent);
                    } else {
                        String msgWarnDelete = getContext().getResources().getString(R.string.msg_warn_delete_campaign);
                        AndroidUtils.snackbar(viewCampaign, MessageFormat.format(msgWarnDelete, campaign.getKey()));
                    }
                }
            });

            holder.getBtnEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (CREATED_CAMPAIGN.equals(statusCurrent)) {
                        createEditCampaign(campaign, adapterCurrent);
                    } else {
                        String msgWarnDelete = getContext().getResources().getString(R.string.msg_warn_edit_campaign);
                        AndroidUtils.snackbar(viewCampaign, MessageFormat.format(msgWarnDelete, campaign.getKey()));
                    }
                }
            });

            holder.getBtnPlay().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Lists.newArrayList(CREATED_CAMPAIGN, EDITED_SCENERY, ADDED_SCENERY).contains(statusCurrent) && campaign.existsHeroes()) {
                        createDialogPlayCampaign(campaign, viewCurrent);
                    } else {
                        String msgWarnPlay = getContext().getResources().getString(R.string.msg_warn_play_campaign);
                        AndroidUtils.snackbar(viewCampaign, MessageFormat.format(msgWarnPlay, campaign.getKey()));
                    }
                }
            });

            holder.getBtnFinish().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (COMPLETED_CAMPAIGN.equals(statusCurrent)) {
                        AndroidUtils.snackbar(viewCampaign, R.string.msg_blank_fragment);
                    } else {
                        String msgWarnDelete = getContext().getResources().getString(R.string.msg_warn_complete_campaign);
                        AndroidUtils.snackbar(viewCampaign, MessageFormat.format(msgWarnDelete, campaign.getKey()));
                    }
                }
            });

            holder.getBtnInfo().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AndroidUtils.snackbar(viewCampaign, R.string.msg_blank_fragment);
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

    private void createEditCampaign(Campaign campaign, RecyclerView.Adapter adapterCurrent) {
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        Intent intent = new Intent(getContext(), CampaingDetailActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
        adapterCurrent.notifyDataSetChanged();
    }

    private void createDialogDeleteCampaign(final Campaign campaign, final int position, final RecyclerView.Adapter adapterCurrent, final View viewCurrent) {
        String msgDelete = getContext().getResources().getString(R.string.alert_confirmation_dialog_text_delete);
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.alert_confirmation_dialog_title_delete)
                .setMessage(MessageFormat.format(msgDelete, campaign.getKey()))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.btn_confirmation_dialog_delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        new AsyncTask<Void, Integer, Boolean>() {
                            @Override
                            protected Boolean doInBackground(Void... voids) {
                                Log.i(TAG, "onClick: Delete the campaign by key!");
                                String token = MainApplication.instance().getToken();
                                return Inject.provideCampaignService().delete(token, campaign);
                            }

                            @Override
                            protected void onPostExecute(Boolean result) {
                                if (result) {
                                    Log.d(TAG, "onClick: Success delete the campaign by key and refresh recyclerView!");
                                    campaigns.remove(position);
                                    adapterCurrent.notifyItemRemoved(position);
                                    adapterCurrent.notifyDataSetChanged();
                                } else {
                                    Log.d(TAG, "onClick: Error delete the campaign by key!");
                                    AndroidUtils.snackbar(viewCurrent, R.string.msg_error_delete_campaign);
                                }
                            }

                        }.execute();

                    }
                })
                .setNegativeButton(R.string.btn_confirmation_dialog_cancel, null).show();
    }

    private void createDialogPlayCampaign(final Campaign campaign, final View viewCurrent) {
        String msgPlay = getContext().getResources().getString(R.string.alert_confirmation_dialog_text_play);
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.alert_confirmation_dialog_title_play)
                .setMessage(MessageFormat.format(msgPlay, campaign.getKey()))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.btn_confirmation_dialog_play, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        AndroidUtils.snackbar(viewCampaign, R.string.msg_blank_fragment);
                    }
                })
                .setNegativeButton(R.string.btn_confirmation_dialog_cancel, null).show();
    }

    private void loadCampaignDetailActivity(Campaign campaign) {
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        Intent intent = new Intent(getContext(), CampaingDetailActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return campaigns != null ? campaigns.size() : 0;
    }
}
