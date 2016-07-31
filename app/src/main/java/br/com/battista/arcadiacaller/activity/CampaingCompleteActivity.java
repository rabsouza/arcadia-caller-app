package br.com.battista.arcadiacaller.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.SceneryCampaign;
import br.com.battista.arcadiacaller.model.enuns.ActionEnum;

public class CampaingCompleteActivity extends BaseActivity {

    private static final String TAG = CampaingDetailActivity.class.getSimpleName();

    private Campaign campaign;
    private TextView txtAliasCampaign;

    private TextView txtScenery01;
    private TextView txtScenery02;
    private TextView txtScenery03;
    private TextView txtScenery04;
    private TextView txtScenery05;
    private TextView txtScenery06;

    private TextView txtWinner;
    private TextView txtLeastDeaths;
    private TextView txtMostCoins;
    private TextView txtWonReward;
    private TextView txtWonTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaing_complete);

        setupToolbarDetail();
        changeTitleCollapsingToolbar(R.string.title_campaign_completed);

        processDataActivity(getIntent().getExtras());
        loadFloatingAction();

    }

    private void loadFloatingAction() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_done_complete_campaign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadMainActivity();
            }
        });
    }

    private void processDataActivity(Bundle bundle) {
        Log.d(TAG, "processDataActivity: Processs bundle data Activity!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);

            if (!campaign.getCompleted()) {
                loadMainActivity();
            }

            txtAliasCampaign = (TextView) findViewById(R.id.complete_card_view_campaign_alias);
            txtAliasCampaign.setText(MessageFormat.format("{0} - {1}", campaign.getKey(), campaign.getAlias()));

            txtScenery01 = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_01);
            SceneryCampaign scenery1 = campaign.getScenery1();
            if (scenery1 != null) {
                txtScenery01.setText(scenery1.getName());
            } else {
                txtScenery01.setText(R.string.none);
            }

            txtScenery02 = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_02);
            SceneryCampaign scenery2 = campaign.getScenery2();
            if (scenery2 != null) {
                txtScenery02.setText(scenery2.getName());
            } else {
                txtScenery02.setText(R.string.none);
            }

            txtScenery03 = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_03);
            SceneryCampaign scenery3 = campaign.getScenery3();
            if (scenery3 != null) {
                txtScenery03.setText(scenery3.getName());
            } else {
                txtScenery03.setText(R.string.none);
            }

            txtScenery04 = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_04);
            SceneryCampaign scenery4 = campaign.getScenery4();
            if (scenery4 != null) {
                txtScenery04.setText(scenery4.getName());
            } else {
                txtScenery04.setText(R.string.none);
            }

            txtScenery05 = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_05);
            SceneryCampaign scenery5 = campaign.getScenery5();
            if (scenery5 != null) {
                txtScenery05.setText(scenery5.getName());
            } else {
                txtScenery05.setText(R.string.none);
            }

            txtScenery06 = (TextView) findViewById(R.id.complete_card_view_sceneries_scenery_06);
            SceneryCampaign scenery6 = campaign.getScenery6();
            if (scenery6 != null) {
                txtScenery06.setText(scenery6.getName());
            } else {
                txtScenery06.setText(R.string.none);
            }

            txtWinner = (TextView) findViewById(R.id.complete_card_view_winners_medals_winners);
            List<String> winner = campaign.getWinner();
            if (winner == null || winner.isEmpty()) {
                txtWinner.setText(R.string.none);
            } else {
                txtWinner.setText(String.valueOf(winner));
            }

            txtLeastDeaths = (TextView) findViewById(R.id.complete_card_view_winners_medals_least_deaths);
            List<String> leastDeaths = campaign.getLeastDeaths();
            if (leastDeaths == null || leastDeaths.isEmpty()) {
                txtLeastDeaths.setText(R.string.none);
            } else {
                txtLeastDeaths.setText(String.valueOf(leastDeaths));
            }

            txtMostCoins = (TextView) findViewById(R.id.complete_card_view_winners_medals_most_coins);
            List<String> mostCoins = campaign.getMostCoins();
            if (mostCoins == null || mostCoins.isEmpty()) {
                txtMostCoins.setText(R.string.none);
            } else {
                txtMostCoins.setText(String.valueOf(mostCoins));
            }

            txtWonReward = (TextView) findViewById(R.id.complete_card_view_winners_medals_won_rewards);
            List<String> wonReward = campaign.getWonReward();
            if (wonReward == null || wonReward.isEmpty()) {
                txtWonReward.setText(R.string.none);
            } else {
                txtWonReward.setText(String.valueOf(wonReward));
            }

            txtWonTitle = (TextView) findViewById(R.id.complete_card_view_winners_medals_won_titles);
            List<String> wonTitle = campaign.getWonTitle();
            if (wonTitle == null || wonTitle.isEmpty()) {
                txtWonTitle.setText(R.string.none);
            } else {
                txtWonTitle.setText(String.valueOf(wonTitle));
            }

        } else {
            loadMainActivity();
        }
    }

    private void loadMainActivity() {
        Bundle args = new Bundle();
        args.putString(BundleConstant.ACTION, ActionEnum.START_FRAGMENT_CAMPAIGNS.name());
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {

        new AlertDialog.Builder(this)
                .setTitle(R.string.alert_confirmation_dialog_title_exit)
                .setMessage(R.string.alert_confirmation_dialog_text_exit)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.btn_confirmation_dialog_exit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        onBackPressed();
                    }
                })
                .setNegativeButton(R.string.btn_confirmation_dialog_cancel, null).show();

        return true;
    }
}
