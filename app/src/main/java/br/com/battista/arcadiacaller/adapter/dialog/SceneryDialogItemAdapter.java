package br.com.battista.arcadiacaller.adapter.dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.model.dto.SceneryDto;

public class SceneryDialogItemAdapter extends RecyclerView.Adapter<SceneryDialogItemViewHolder> {
    private static final String TAG = SceneryDialogItemAdapter.class.getSimpleName();

    private Context context;
    private List<SceneryDto> sceneries;
    private View view;

    public SceneryDialogItemAdapter(Context context, List<SceneryDto> sceneries) {
        this.context = context;
        this.sceneries = sceneries;
    }

    @Override
    public SceneryDialogItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_dialog_scenery, viewGroup, false);
        return new SceneryDialogItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SceneryDialogItemViewHolder holder, int position) {
        if (sceneries != null && !sceneries.isEmpty()) {
            final SceneryDto sceneryDto = sceneries.get(position);
            Log.i(TAG, String.format(
                    "onBindViewHolder: Fill to row position: %S with %s.", position, sceneryDto));

            holder.getTxtName().setText(sceneryDto.getName());

            if (sceneryDto.getCompleted()) {
                holder.getImgAction().setImageResource(R.drawable.ic_done);
            } else {
                holder.getImgAction().setImageResource(R.drawable.ic_play);
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
