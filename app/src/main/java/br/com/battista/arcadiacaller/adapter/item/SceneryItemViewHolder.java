package br.com.battista.arcadiacaller.adapter.item;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;

public class SceneryItemViewHolder extends RecyclerView.ViewHolder {

    private TextView txtName;

    private ImageView imgAction;


    public SceneryItemViewHolder(View view) {
        super(view);

        txtName = (TextView) view.findViewById(R.id.item_scenery_name);
        imgAction = (ImageView) view.findViewById(R.id.item_scenery_action);
    }

    public TextView getTxtName() {
        return txtName;
    }

    public ImageView getImgAction() {
        return imgAction;
    }
}
