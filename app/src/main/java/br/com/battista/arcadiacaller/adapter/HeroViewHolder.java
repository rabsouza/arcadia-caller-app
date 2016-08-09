package br.com.battista.arcadiacaller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;

public class HeroViewHolder extends RecyclerView.ViewHolder {

    private TextView txtTitle;

    private TextView txtGroup;

    private TextView txtAbility;

    private ImageView imgHero;

    private ImageView imgLife;

    private ImageView imgDefense;

    public HeroViewHolder(View view) {
        super(view);

        txtTitle = (TextView) view.findViewById(R.id.card_view_hero_title);
        txtGroup = (TextView) view.findViewById(R.id.card_view_hero_group);
        txtAbility = (TextView) view.findViewById(R.id.card_view_hero_ability);

        imgHero = (ImageView) view.findViewById(R.id.card_view_hero_img_hero);
        imgLife = (ImageView) view.findViewById(R.id.card_view_hero_life);
        imgDefense = (ImageView) view.findViewById(R.id.card_view_hero_defense);
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public TextView getTxtGroup() {
        return txtGroup;
    }

    public TextView getTxtAbility() {
        return txtAbility;
    }

    public ImageView getImgHero() {
        return imgHero;
    }

    public ImageView getImgLife() {
        return imgLife;
    }

    public ImageView getImgDefense() {
        return imgDefense;
    }
}
