package br.com.battista.arcadiacaller.fragment.detail;


import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.activity.MainActivity;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.fragment.CampaignsFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.SceneryCampaign;
import br.com.battista.arcadiacaller.model.enuns.ActionEnum;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;
import br.com.battista.arcadiacaller.service.CampaignService;
import br.com.battista.arcadiacaller.service.SceneryService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

public class CampaignDetailSceneryFragment extends BaseFragment {

    private static final String TAG = CampaignsFragment.class.getSimpleName();

    private Campaign campaign;
    private LocationSceneryEnum locationScenery;
    private Map<String, Scenery> sceneryMap = Maps.newLinkedHashMap();

    private MaterialBetterSpinner spnLocation;
    private MaterialBetterSpinner spnScenery;

    public CampaignDetailSceneryFragment() {
    }

    public static CampaignDetailSceneryFragment newInstance(Campaign campaign, LocationSceneryEnum locationScenery) {
        CampaignDetailSceneryFragment fragment = new CampaignDetailSceneryFragment();
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        args.putSerializable(BundleConstant.FILTER_LOCATION_SCENERY, locationScenery);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: Create detail scenery campaign!");
        final View viewFragment = inflater.inflate(R.layout.fragment_campaign_detail_scenery, container, false);

        FloatingActionButton fab = (FloatingActionButton) viewFragment.findViewById(R.id.fab_done_scenery_campaign);
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
        Log.d(TAG, "processNextAction: Process next action -> Fragment CampaignDetailSceneryFragment!");

        String name = spnScenery.getText().toString();
        Scenery scenery = sceneryMap.get(name);
        SceneryCampaign sceneryCampaign = SceneryCampaign.builder().name(name).scenery(scenery).active(TRUE).completed(FALSE).deleted(FALSE).build();
        campaign.setScenery1(sceneryCampaign);

        final View currentView = view;
        new ProgressApp(getActivity(), R.string.msg_action_saving, false) {
            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    Log.i(TAG, "onPostExecute: Success add scenery to campaign!");
                    Bundle args = new Bundle();
                    args.putString(BundleConstant.ACTION, ActionEnum.START_FRAGMENT_CAMPAIGNS.name());
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtras(args);

                    getContext().startActivity(intent);
                } else {
                    Log.i(TAG, "onPostExecute: Error add scenery to campaign!");
                    AndroidUtils.snackbar(currentView, R.string.msg_error_campaign_not_found);
                }
                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    String token = MainApplication.instance().getToken();
                    CampaignService campaignService = Inject.provideCampaignService();
                    campaign = campaignService.update(token, campaign);
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }
        }.execute();

    }

    private void processDataFragment(final View viewFragment, Bundle bundle) {
        Log.d(TAG, "processDataFragment: Processs bundle data Fragment!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);
        }

        if (bundle.containsKey(BundleConstant.FILTER_LOCATION_SCENERY)) {
            locationScenery = (LocationSceneryEnum) bundle.getSerializable(BundleConstant.FILTER_LOCATION_SCENERY);

            String textLocation = getString(locationScenery.getDescRes());
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_dropdown_item_1line, (List<String>) Lists.newArrayList(textLocation));
            spnLocation = (MaterialBetterSpinner)
                    viewFragment.findViewById(R.id.detail_card_view_scenery_location);
            spnLocation.setAdapter(arrayAdapter);
            spnLocation.setText(textLocation);

            new AsyncTask<Void, Integer, Boolean>() {

                List<Scenery> sceneries;

                @Override
                protected Boolean doInBackground(Void... voids) {
                    String token = MainApplication.instance().getToken();
                    SceneryService sceneryService = Inject.provideSceneryService();
                    sceneries = sceneryService.findByLocation(token, locationScenery);
                    return true;
                }

                @Override
                protected void onPostExecute(Boolean result) {

                    for (Scenery scenery : sceneries) {
                        sceneryMap.put(scenery.getName(), scenery);
                    }

                    ArrayList<String> namesScenery = Lists.newArrayList(sceneryMap.keySet());
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_dropdown_item_1line, (List<String>) namesScenery);
                    spnScenery = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_scenery_name);
                    spnScenery.setAdapter(arrayAdapter);

                    SceneryCampaign sceneryCampaignCurrent = campaign.getSceneryCurrent();
                    if (Strings.isNullOrEmpty(sceneryCampaignCurrent.getName())) {
                        spnScenery.setText(namesScenery.get(0));
                    } else if (namesScenery.contains(sceneryCampaignCurrent.getName())) {
                        spnScenery.setText(sceneryCampaignCurrent.getName());
                    } else {
                        spnScenery.setText(namesScenery.get(0));
                    }
                }

            }.execute();
        }
    }

}
