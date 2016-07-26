package br.com.battista.arcadiacaller.adapter.item;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;
import lombok.Getter;

public class GuildItemViewHolder extends RecyclerView.ViewHolder {

    @Getter
    private TextView txtName;

    @Getter
    private TextView txtUsername;

    @Getter
    private ImageView imgImg;


    public GuildItemViewHolder(View view) {
        super(view);

        txtName = (TextView) view.findViewById(R.id.item_guild_name);
        txtUsername = (TextView) view.findViewById(R.id.item_guild_username);
        imgImg = (ImageView) view.findViewById(R.id.item_guild_img);
    }

}
