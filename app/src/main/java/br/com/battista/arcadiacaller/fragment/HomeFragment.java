package br.com.battista.arcadiacaller.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Calendar;

import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.util.AppUtils;
import br.com.battista.arcadiacaller.util.DateUtils;


public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Create new fragment Home!");

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        MainApplication application = MainApplication.instance();
        AppUtils.goToHomeIfUserIsNull(application, getContext());
        User user = application.getUser();

        Log.i(TAG, "onCreateView: Load all data to User!");
        TextView txtUsername = (TextView) view.findViewById(R.id.card_view_home_username);
        txtUsername.setText(user.getUsername());

        TextView txtMail = (TextView) view.findViewById(R.id.card_view_home_mail);
        txtMail.setText(user.getMail());

        TextView txtCreated = (TextView) view.findViewById(R.id.card_view_home_created);
        Calendar created = Calendar.getInstance();
        created.setTime(user.getCreatedAt());
        txtCreated.setText(DateUtils.format(created));

        ImageView imageViewImg = (ImageView) view.findViewById(R.id.card_view_home_img);
        if (imageViewImg != null) {
            Glide.with(getContext())
                    .load(user.getUrlAvatar())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .centerCrop()
                    .crossFade()
                    .into(imageViewImg);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
