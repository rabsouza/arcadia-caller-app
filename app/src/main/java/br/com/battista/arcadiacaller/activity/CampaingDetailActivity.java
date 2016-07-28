package br.com.battista.arcadiacaller.activity;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.detail.CampaignDetailNewFragment;
import br.com.battista.arcadiacaller.model.Campaign;

public class CampaingDetailActivity extends BaseActivity {

    private static final String TAG = CampaingDetailActivity.class.getSimpleName();

    private static final String DEFAULT_BACKGROUND_HEADER = "https://storage.googleapis.com/arcadia-quest-storage/campaign/background_campaign.png";

    private Campaign campaign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaing_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.detail_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getContext().getString(R.string.title_campaign_detail));

        processDataActivity(getIntent().getExtras());
        replaceDetailFragment(CampaignDetailNewFragment.newInstance(campaign));
    }

    protected void replaceDetailFragment(Fragment fragment) {
        if (fragment != null) {
            Log.d(TAG, "replaceFragment: Change to detail fragment!");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, fragment).commit();
        }
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
