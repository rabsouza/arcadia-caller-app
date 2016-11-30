package br.com.battista.arcadiacaller.adapter.dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.model.dto.GuildDto;
import br.com.battista.arcadiacaller.util.ImageLoadUtils;

public class GuildDialogItemAdapter extends RecyclerView.Adapter<GuildDialogItemViewHolder> {
    private static final String TAG = GuildDialogItemAdapter.class.getSimpleName();

    private Context context;
    private List<GuildDto> guilds;
    private View view;

    public GuildDialogItemAdapter(Context context, List<GuildDto> guilds) {
        this.context = context;
        this.guilds = guilds;
    }

    @Override
    public GuildDialogItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_dialog_guild, viewGroup, false);
        return new GuildDialogItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuildDialogItemViewHolder holder, int position) {
        if (guilds != null && !guilds.isEmpty()) {
            final GuildDto guild = guilds.get(position);
            Log.i(TAG, String.format(
                    "onBindViewHolder: Fill to row position: %S with %s.", position, guild));

            holder.getTxtUsername().setText(guild.getUsername());

            ImageLoadUtils
                    .loadImageWithImageError(context,
                            guild.getName().getUrlImg(),
                            holder.getImgImg(),
                            android.R.drawable.sym_def_app_icon);

            Log.w(TAG, "onBindViewHolder: No content to holder!");
        }

    }

    @Override
    public int getItemCount() {
        return guilds != null ? guilds.size() : 0;
    }
}
