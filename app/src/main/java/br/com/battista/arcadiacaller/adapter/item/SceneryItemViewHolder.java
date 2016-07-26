package br.com.battista.arcadiacaller.adapter.item;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;
import lombok.Getter;

public class SceneryItemViewHolder extends RecyclerView.ViewHolder {

    @Getter
    private TextView txtName;

    @Getter
    private ImageView imgAction;


    public SceneryItemViewHolder(View view) {
        super(view);

        txtName = (TextView) view.findViewById(R.id.item_scenery_name);
        imgAction = (ImageView) view.findViewById(R.id.item_scenery_action);
    }

}
