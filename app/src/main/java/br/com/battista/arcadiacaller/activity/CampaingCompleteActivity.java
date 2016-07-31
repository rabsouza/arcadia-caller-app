package br.com.battista.arcadiacaller.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;

import java.text.MessageFormat;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaing_complete);

        setupToolbarDetail();
        changeTitleCollapsingToolbar(R.string.title_campaign_completed);

        processDataActivity(getIntent().getExtras());

    }

    private void processDataActivity(Bundle bundle) {
        Log.d(TAG, "processDataActivity: Processs bundle data Activity!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);

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
