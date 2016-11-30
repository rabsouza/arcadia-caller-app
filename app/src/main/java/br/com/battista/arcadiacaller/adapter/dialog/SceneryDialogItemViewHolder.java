package br.com.battista.arcadiacaller.adapter.dialog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;

public class SceneryDialogItemViewHolder extends RecyclerView.ViewHolder {

    private TextView txtName;

    private ImageView imgAction;


    public SceneryDialogItemViewHolder(View view) {
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
