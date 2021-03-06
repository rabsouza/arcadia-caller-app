package br.com.battista.arcadiacaller.adapter;

import static br.com.battista.arcadiacaller.util.HeroUtils.getHeroDefenseRes;
import static br.com.battista.arcadiacaller.util.HeroUtils.getHeroLifeRes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.model.Hero;
import br.com.battista.arcadiacaller.util.ImageLoadUtils;

public class HeroAdapter extends BaseAdapterAnimation<HeroViewHolder> {
    private static final String TAG = HeroAdapter.class.getSimpleName();

    private Context context;
    private List<Hero> heroes;


    public HeroAdapter(Context context, List<Hero> heroes) {
        super(context);
        this.context = context;
        this.heroes = heroes;
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.adapter_hero, viewGroup, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeroViewHolder holder, int position) {
        if (heroes != null && !heroes.isEmpty()) {
            setAnimationHolder(holder.itemView, position);

            final Hero hero = heroes.get(position);
            Log.i(TAG, String.format(
                    "onBindViewHolder: Fill to row position: %S with %s.", position, hero));

            holder.getTxtTitle().setText(hero.getName());
            holder.getTxtGroup().setText(hero.getGroup().getDescRes());

            String textAbility = context.getString(R.string.hint_hero_ability);
            holder.getTxtAbility().setText(MessageFormat.format(textAbility, hero.getAbility()));

            ImageLoadUtils
                    .loadImage(context,
                            getHeroDefenseRes(hero.getDefense()),
                            holder.getImgDefense());

            ImageLoadUtils
                    .loadImage(context,
                            getHeroLifeRes(hero.getLife()),
                            holder.getImgLife());

            ImageLoadUtils
                    .loadImageWithPlaceHolderAndError(context,
                            hero.getUrlPhoto(),
                            holder.getImgHero(),
                            R.drawable.image_error);

        } else {
            Log.w(TAG, "onBindViewHolder: No content to holder!");
        }

    }


    @Override
    public int getItemCount() {
        return heroes != null ? heroes.size() : 0;
    }
}
