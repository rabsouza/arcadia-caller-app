package br.com.battista.arcadiacaller.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Strings;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.model.Card;
import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;

public class SceneryAdapter extends RecyclerView.Adapter<SceneryViewHolder> {
    private static final String TAG = SceneryAdapter.class.getSimpleName();

    private Context context;
    private List<Scenery> sceneries;
    private View view;

    public SceneryAdapter(Context context, List<Scenery> sceneries) {
        this.context = context;
        this.sceneries = sceneries;
    }

    @Override
    public SceneryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.adapter_scenery, viewGroup, false);
        return new SceneryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SceneryViewHolder holder, int position) {
        if (sceneries != null && !sceneries.isEmpty()) {
            final Scenery scenery = sceneries.get(position);
            Log.i(TAG, String.format(
                    "onBindViewHolder: Fill to row position: %S with %s.", position, scenery));

            TextView txtTitle = holder.getTxtTitle();
            txtTitle.setText(scenery.getName());

            LocationSceneryEnum location = scenery.getLocation();
            txtTitle.setTextColor(ContextCompat.getColor(context, location.getColorRes()));

            String sceneryHead = String.valueOf(context.getText(R.string.hint_scenery_head));
            CharSequence difficulty = context.getText(scenery.getDifficulty().getDescRes());
            CharSequence locationText = context.getText(scenery.getLocation().getDescRes());
            holder.getTxtDifficulty().setText(MessageFormat.format(sceneryHead, difficulty, locationText));

            Card wonReward = scenery.getWonReward();
            if (wonReward == null) {
                holder.getTxtWonReward().setText(R.string.none);
            } else {
                holder.getTxtWonReward().setText(wonReward.getName());
            }

            String wonTitle = scenery.getWonTitle();
            if (Strings.isNullOrEmpty(wonTitle)) {
                holder.getTxtWonTitle().setText(R.string.none);
            } else {
                holder.getTxtWonTitle().setText(wonTitle);
            }

            List<String> benefitTitles = scenery.getBenefitTitles();
            if (benefitTitles == null || benefitTitles.isEmpty()) {
                holder.getTxtBenefitTitles().setText(R.string.none);
            } else {
                holder.getTxtBenefitTitles().setText(benefitTitles.toString());
            }

        } else {
            Log.w(TAG, "onBindViewHolder: No content to holder!");
        }

    }

    @Override
    public int getItemCount() {
        return sceneries != null ? sceneries.size() : 0;
    }
}
