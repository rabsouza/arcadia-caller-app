package br.com.battista.arcadiacaller.activity;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.inventory.CampaignInventoryFragment;
import br.com.battista.arcadiacaller.model.Campaign;

public class CampaignInventoryActivity extends BaseActivity {

    public static final int POSITION_INITIAL = 0;
    private static final String TAG = CampaignInventoryActivity.class.getSimpleName();
    private Campaign campaign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_inventory);

        setupToolbarDetail();
        changeTitleCollapsingToolbar(R.string.title_campaign_inventory);

        processDataActivity(getIntent().getExtras());
        replaceDetailFragment(CampaignInventoryFragment.newInstance(campaign, POSITION_INITIAL), R.id.inventory_container);
    }

    private void processDataActivity(Bundle bundle) {
        Log.d(TAG, "processDataActivity: Process bundle data Activity!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);
        } else {
            campaign = new Campaign().active(TRUE).completed(FALSE).deleted(FALSE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        dialogCloseActivity();
    }

    private void dialogCloseActivity() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.alert_confirmation_dialog_title_exit)
                .setMessage(R.string.alert_confirmation_dialog_text_exit)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.btn_confirmation_dialog_exit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        superOnBackPressed();
                    }

                })
                .setNegativeButton(R.string.btn_confirmation_dialog_cancel, null).show();
    }

    private void superOnBackPressed() {
        super.onBackPressed();
    }

}
