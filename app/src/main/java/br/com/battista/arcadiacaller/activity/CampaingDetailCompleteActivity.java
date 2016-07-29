package br.com.battista.arcadiacaller.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.detail.CampaignDetailCompleteSceneryFragment;
import br.com.battista.arcadiacaller.model.Campaign;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class CampaingDetailCompleteActivity extends BaseActivity {

    private static final String TAG = CampaingDetailActivity.class.getSimpleName();

    private Campaign campaign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaing_detail_complete);

        setupToolbarDetail();
        changeTitleCollapsingToolbar(R.string.title_campaign_detail);

        processDataActivity(getIntent().getExtras());
        replaceDetailFragment(CampaignDetailCompleteSceneryFragment.newInstance(campaign), R.id.detail_container_complete);
    }

    private void processDataActivity(Bundle bundle) {
        Log.d(TAG, "processDataActivity: Processs bundle data Activity!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);
        } else {
            campaign = Campaign.builder().active(TRUE).completed(FALSE).deleted(FALSE).build();
        }
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
