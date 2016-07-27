package br.com.battista.arcadiacaller.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;

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

        Glide.with(getContext())
                .load(NameGuildEnum.BLUE.getUrlImg())
                .crossFade()
                .into((ImageView) findViewById(R.id.detail_card_view_guilds_img_blue));

        Glide.with(getContext())
                .load(NameGuildEnum.GREEN.getUrlImg())
                .crossFade()
                .into((ImageView) findViewById(R.id.detail_card_view_guilds_img_green));

        Glide.with(getContext())
                .load(NameGuildEnum.ORANGE.getUrlImg())
                .crossFade()
                .into((ImageView) findViewById(R.id.detail_card_view_guilds_img_orange));

        Glide.with(getContext())
                .load(NameGuildEnum.RED.getUrlImg())
                .crossFade()
                .into((ImageView) findViewById(R.id.detail_card_view_guilds_img_red));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
