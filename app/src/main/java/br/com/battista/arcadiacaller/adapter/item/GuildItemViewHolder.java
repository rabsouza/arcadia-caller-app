package br.com.battista.arcadiacaller.adapter.item;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;

public class GuildItemViewHolder extends RecyclerView.ViewHolder {

    private TextView txtName;

    private TextView txtUsername;

    private ImageView imgImg;


    public GuildItemViewHolder(View view) {
        super(view);

        txtName = (TextView) view.findViewById(R.id.item_guild_name);
        txtUsername = (TextView) view.findViewById(R.id.item_guild_username);
        imgImg = (ImageView) view.findViewById(R.id.item_guild_img);
    }

    public TextView getTxtName() {
        return txtName;
    }

    public TextView getTxtUsername() {
        return txtUsername;
    }

    public ImageView getImgImg() {
        return imgImg;
    }
}
