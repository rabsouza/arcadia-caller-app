package br.com.battista.arcadiacaller.fragment.detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.MessageFormat;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.SceneryCampaign;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;
import br.com.battista.arcadiacaller.util.AndroidUtils;

public class CampaignDetailCompleteSceneryFragment extends BaseFragment {

    private static final String TAG = CampaignDetailCompleteSceneryFragment.class.getSimpleName();

    private Campaign campaign;

    private TextView txtAlias;
    private TextView txtLocation;
    private TextView txtName;

    public CampaignDetailCompleteSceneryFragment() {
    }

    public static CampaignDetailCompleteSceneryFragment newInstance(Campaign campaign) {
        CampaignDetailCompleteSceneryFragment fragment = new CampaignDetailCompleteSceneryFragment();
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: Create detail scenery campaign!");
        final View viewFragment = inflater.inflate(R.layout.fragment_campaign_detail_complete_scenery, container, false);

        FloatingActionButton fab = (FloatingActionButton) viewFragment.findViewById(R.id.fab_done_complete_scenery_campaign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processNextAction(viewFragment);
            }
        });

        processDataFragment(viewFragment, getArguments());

        return viewFragment;
    }

    private void processNextAction(View view) {
        Log.d(TAG, "processNextAction: Process next action -> Activity MainActivity -> Fragment CampaignsFragment!");

        if (campaign == null) {
            AndroidUtils.snackbar(view, R.string.msg_error_campaign_not_found);
            return;
        }

    }

    private void processDataFragment(final View viewFragment, Bundle bundle) {
        Log.d(TAG, "processDataFragment: Processs bundle data Fragment!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);

            txtAlias = (TextView) viewFragment.findViewById(R.id.detail_card_view_campaign_alias);
            txtAlias.setText(MessageFormat.format("{0} - {1}", campaign.getKey(), campaign.getAlias()));

            SceneryCampaign sceneryCurrent = campaign.getSceneryCurrent();

            if (sceneryCurrent.getScenery() != null) {
                txtLocation = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_location);
                LocationSceneryEnum location = sceneryCurrent.getScenery().getLocation();
                txtLocation.setText(location.getDescRes());
            }

            if (sceneryCurrent.getScenery() != null) {
                txtName = (TextView) viewFragment.findViewById(R.id.detail_card_view_scenery_name);
                txtName.setText(sceneryCurrent.getScenery().getName());
            }
        }
    }
}
