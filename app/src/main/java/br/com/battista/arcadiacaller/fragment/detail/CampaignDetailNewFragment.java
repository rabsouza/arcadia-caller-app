package br.com.battista.arcadiacaller.fragment.detail;


import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.common.base.Strings;

import java.text.MessageFormat;
import java.util.Date;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.service.CampaignService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;


public class CampaignDetailNewFragment extends BaseFragment {

    private static final String TAG = CampaignDetailNewFragment.class.getSimpleName();

    private EditText txtAlias;
    private Campaign campaignCreated;

    public CampaignDetailNewFragment() {
    }

    public static CampaignDetailNewFragment newInstance(Campaign campaign) {
        CampaignDetailNewFragment fragment = new CampaignDetailNewFragment();
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: Create detail new campaign!");
        final View viewFragment = inflater.inflate(R.layout.fragment_campaign_detail_new, container, false);

        FloatingActionButton fab = (FloatingActionButton) viewFragment.findViewById(R.id.fab_next_new_campaign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processNextAction(viewFragment);
            }
        });

        processDataFragment(viewFragment, getArguments());

        return viewFragment;
    }

    private void processDataFragment(View view, Bundle bundle) {
        Log.d(TAG, "processDataFragment: Process bundle data Fragment!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaignCreated = (Campaign) bundle.getSerializable(BundleConstant.DATA);

            txtAlias = (EditText) view.findViewById(R.id.detail_card_view_campaign_alias);
            txtAlias.setText(campaignCreated.getAlias());
        }
    }

    private void processNextAction(View view) {
        Log.d(TAG, "processNextAction: Process next action -> Fragment CampaignDetailGuildsFragment!");

        txtAlias = (EditText) view.findViewById(R.id.detail_card_view_campaign_alias);
        if (Strings.isNullOrEmpty(txtAlias.getText().toString())) {
            String msgErrorUsername = getContext().getString(R.string.msg_alias_required);
            AndroidUtils.changeErrorEditText(txtAlias, msgErrorUsername, true);
            return;
        }
        AndroidUtils.changeErrorEditText(txtAlias);

        final String alias = txtAlias.getText().toString().trim();
        Log.d(TAG, MessageFormat.format("Create campaign with alias: {0}.", alias));
        String username = MainApplication.instance().getUser().getUsername();
        if (campaignCreated == null) {
            campaignCreated = new Campaign().alias(alias).created(username).when(new Date()).active(TRUE).completed(FALSE).deleted(FALSE);
        } else {
            campaignCreated.setAlias(alias);
            campaignCreated.setCreated(username);
            campaignCreated.setWhen(new Date());
        }

        final View currentView = view;
        new ProgressApp(getActivity(), R.string.msg_action_saving, false) {

            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    replaceDetailFragment(CampaignDetailGuildsFragment.newInstance(campaignCreated), R.id.detail_container);
                } else {
                    AndroidUtils.snackbar(currentView, R.string.msg_failed_create_campaign);
                }
                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    String token = MainApplication.instance().getToken();

                    CampaignService campaignService = Inject.provideCampaignService();
                    campaignCreated = campaignService.create(token, campaignCreated);
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }
        }.execute();

    }

}
