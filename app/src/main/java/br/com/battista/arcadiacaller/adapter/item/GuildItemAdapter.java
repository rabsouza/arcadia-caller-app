package br.com.battista.arcadiacaller.adapter.item;

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

public class GuildItemAdapter extends RecyclerView.Adapter<GuildItemViewHolder> {
    private static final String TAG = GuildItemAdapter.class.getSimpleName();

    private Context context;
    private List<GuildDto> guilds;
    private View view;

    public GuildItemAdapter(Context context, List<GuildDto> guilds) {
        this.context = context;
        this.guilds = guilds;
    }

    @Override
    public GuildItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_guild, viewGroup, false);
        return new GuildItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuildItemViewHolder holder, int position) {
        if (guilds != null && !guilds.isEmpty()) {
            final GuildDto guild = guilds.get(position);
            Log.i(TAG, String.format(
                    "onBindViewHolder: Fill to row position: %S with %s.", position, guild));

            holder.getTxtName().setText(context.getText(guild.getName().getResId()));

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
