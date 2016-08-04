package br.com.battista.arcadiacaller.activity;

import android.os.Bundle;
import android.util.Log;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.view.CampaignViewGuildsFragment;
import br.com.battista.arcadiacaller.model.Campaign;

public class CampaingViewActivity extends BaseActivity {

    private static final String TAG = CampaingViewActivity.class.getSimpleName();

    private Campaign campaign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaing_view);

        setupToolbarDetail();
        changeTitleCollapsingToolbar(R.string.title_campaign_view);

        processDataActivity(getIntent().getExtras());
        replaceDetailFragment(CampaignViewGuildsFragment.newInstance(campaign), R.id.detail_container_view);
    }


    private void processDataActivity(Bundle bundle) {
        Log.d(TAG, "processDataActivity: Processs bundle data Activity!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}