package br.com.battista.arcadiacaller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.battista.arcadiacaller.R;

public class FriendAdapter extends RecyclerView.Adapter<FriendViewHolder> {
    private static final String TAG = FriendAdapter.class.getSimpleName();

    private Context context;
    private List<String> friends;
    private View view;

    public FriendAdapter(Context context, List<String> friends) {
        this.context = context;
        this.friends = friends;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.adapter_friend, viewGroup, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {
        if (friends != null && !friends.isEmpty()) {
            final String friend = friends.get(position);
            Log.i(TAG, String.format(
                    "onBindViewHolder: Fill to row position: %S with %s.", position, friend));

            holder.getTxtUSername().setText(friend);
        } else {
            Log.w(TAG, "onBindViewHolder: No content to holder!");
        }

    }

    @Override
    public int getItemCount() {
        return friends != null ? friends.size() : 0;
    }
}
