package br.com.battista.arcadiacaller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.battista.arcadiacaller.R;
import lombok.Getter;

public class HeroViewHolder extends RecyclerView.ViewHolder {

    @Getter
    private TextView txtTitle;

    @Getter
    private TextView txtGroup;

    @Getter
    private TextView txtAbility;

    @Getter
    private ImageView imgHero;

    @Getter
    private ImageView imgLife;

    @Getter
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

}
