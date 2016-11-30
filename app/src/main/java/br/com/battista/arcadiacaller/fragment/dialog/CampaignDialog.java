package br.com.battista.arcadiacaller.fragment.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.List;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.adapter.dialog.GuildDialogItemAdapter;
import br.com.battista.arcadiacaller.adapter.dialog.SceneryDialogItemAdapter;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.dto.GuildDto;
import br.com.battista.arcadiacaller.model.dto.SceneryDto;
import br.com.battista.arcadiacaller.service.ScreenShareService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.DateUtils;
import br.com.battista.arcadiacaller.util.ImageLoadUtils;

public class CampaignDialog extends DialogFragment {

    private static final String TAG = CampaignDialog.class.getSimpleName();

    private View container;

    private ImageView background;
    private TextView txtTitle;
    private TextView txtKey;
    private TextView txtDate;
    private TextView txtStatus;
    private Campaign campaign;

    private Button btnShare;
    private Button btnCancel;


    public CampaignDialog() {
    }

    public static CampaignDialog newInstance(Campaign campaign) {
        CampaignDialog fragment = new CampaignDialog();
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.dialog_campaign, container, true);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        loadViews(viewFragment);
        processDataFragment(viewFragment, getArguments());

        return viewFragment;
    }

    private void processDataFragment(final View viewFragment, Bundle bundle) {
        Log.d(TAG, "processDataFragment: Process bundle data Fragment!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);

            ImageLoadUtils.loadImage(getActivity(), R.drawable.background_share, background);

            txtTitle.setText(getText(R.string.hint_title_share_camping));
            txtKey.setText(campaign.getKey());

            Calendar date = Calendar.getInstance();
            date.setTime(campaign.getCreatedAt());
            txtDate.setText(DateUtils.formatWithHours(date));
            txtStatus.setText(campaign.getStatusCurrent().getDescRes());

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "onClick: Cancel to share campaign!");
                    dismiss();
                }
            });

            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "onClick: Process to share campaign!");
                    btnShare.setVisibility(View.INVISIBLE);
                    btnCancel.setVisibility(View.INVISIBLE);
                    new ScreenShareService(container.getContext()).shareScreen(container, campaign);
                    new Runnable() {
                        @Override
                        public void run() {
                            dismiss();
                        }
                    }.run();
                }
            });

            List<GuildDto> guildDtos = campaign.generateGuildsDto();
            Log.i(TAG, MessageFormat.format("onBindViewHolder: Load {0} guildDtos to campaign: {1}!"
                    , guildDtos.size(), campaign.getKey()));
            setupGuildsRecyclerViewItem(viewFragment, guildDtos);

            List<SceneryDto> sceneryDtos = campaign.generateSceneriesDto();
            Log.i(TAG, MessageFormat.format("onBindViewHolder: Load {0} sceneryDtos to campaign: {1}!"
                    , sceneryDtos.size(), campaign.getKey()));
            setupSceneriesRecyclerViewItem(viewFragment, sceneryDtos);

        } else {
            campaign = new Campaign();
            AndroidUtils.snackbar(viewFragment, getActivity().getText(R.string.msg_error_campaign_not_found).toString());
        }
    }

    private void loadViews(View viewFragment) {
        Log.i(TAG, "loadViews: load all views!");
        container = viewFragment.findViewById(R.id.dialog_view_campaign_container);

        background = (ImageView) viewFragment.findViewById(R.id.dialog_view_campaign_img);
        txtTitle = (TextView) viewFragment.findViewById(R.id.dialog_view_campaign_title);
        txtKey = (TextView) viewFragment.findViewById(R.id.dialog_view_campaign_key);
        txtDate = (TextView) viewFragment.findViewById(R.id.dialog_view_campaign_date);
        txtStatus = (TextView) viewFragment.findViewById(R.id.dialog_view_campaign_status);

        btnCancel = (Button) viewFragment.findViewById(R.id.btn_cancel);
        btnShare = (Button) viewFragment.findViewById(R.id.btn_share);
    }

    private void setupGuildsRecyclerViewItem(View view, List<GuildDto> guildDtos) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dialog_view_campaign_guilds_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(new GuildDialogItemAdapter(getActivity(), guildDtos));
    }

    private void setupSceneriesRecyclerViewItem(View view, List<SceneryDto> sceneryDtos) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dialog_view_campaign_sceneries_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(new SceneryDialogItemAdapter(getActivity(), sceneryDtos));
    }

}
