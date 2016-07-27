package br.com.battista.arcadiacaller.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.fragment.detail.CampaignDetailNewFragment;
import br.com.battista.arcadiacaller.model.Campaign;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class CampaingDetailActivity extends BaseActivity {

    private static final String TAG = CampaingDetailActivity.class.getSimpleName();

    private static final String DEFAULT_BACKGROUND_HEADER = "https://storage.googleapis.com/arcadia-quest-storage/campaign/background_campaign.png";

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
        int titleTextColor = ContextCompat.getColor(getContext(), R.color.colorTitle);
        collapsingToolbar.setExpandedTitleColor(titleTextColor);

        Glide.with(getContext())
                .load(DEFAULT_BACKGROUND_HEADER)
                .crossFade()
                .into((ImageView) findViewById(R.id.detail_image_toolbar));

        Campaign campaign = Campaign.builder().active(TRUE).completed(FALSE).deleted(FALSE).build();
        replaceDetailFragment(CampaignDetailNewFragment.newInstance(campaign));
    }

    protected void replaceDetailFragment(Fragment fragment) {
        if (fragment != null) {
            Log.d(TAG, "replaceFragment: Change to detail fragment!");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, fragment).commit();
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
