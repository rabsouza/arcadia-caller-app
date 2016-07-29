package br.com.battista.arcadiacaller.adapter;

import static br.com.battista.arcadiacaller.util.CardUtils.getCardCostRes;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.model.Card;
import br.com.battista.arcadiacaller.model.enuns.GroupCardEnum;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    private static final String TAG = CardAdapter.class.getSimpleName();

    private Context context;
    private List<Card> cards;
    private View view;

    public CardAdapter(Context context, List<Card> cards) {
        this.context = context;
        this.cards = cards;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.adapter_card, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        if (cards != null && !cards.isEmpty()) {
            final Card card = cards.get(position);
            Log.i(TAG, String.format(
                    "onBindViewHolder: Fill to row position: %S with %s.", position, card));

            TextView txtTitle = holder.getTxtTitle();
            txtTitle.setText(card.getName());

            GroupCardEnum cardGroup = card.getGroup();
            txtTitle.setTextColor(ContextCompat.getColor(context, cardGroup.getColorRes()));

            String textGroup = MessageFormat.format(String.valueOf(context.getText(R.string.hint_card_group)), context.getText(cardGroup.getDescRes()));
            holder.getTxtGroup().setText(textGroup);

            holder.getTxtType().setText(card.getType().getDescRes());

            Glide.with(context)
                    .load(getCardCostRes(card.getCost()))
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .crossFade()
                    .into(holder.getImgCost());
        } else {
            Log.w(TAG, "onBindViewHolder: No content to holder!");
        }

    }

    @Override
    public int getItemCount() {
        return cards != null ? cards.size() : 0;
    }
}
