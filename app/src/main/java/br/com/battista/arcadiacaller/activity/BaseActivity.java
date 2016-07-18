package br.com.battista.arcadiacaller.activity;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.model.User;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    protected Context getContext() {
        return this;
    }

    protected Activity getActivity() {
        return this;
    }

    protected void loadNavigationViewHeader(NavigationView navigationView) {
        if (navigationView != null && navigationView.getHeaderCount() > 0) {
            View view = navigationView.getHeaderView(0);
            if (view != null) {
                MainApplication instance = MainApplication.getInstance();
                User user = instance.getUser();
                Log.i(TAG, String.format(
                        "loadNavigationViewHeader: Fill navigation header with user: !",
                        user));

                TextView textViewName = (TextView) view.findViewById(R.id.nav_view_header_username);
                if (textViewName != null) {
                    textViewName.setText(user.getUsername());
                }

                TextView textViewMail = (TextView) view.findViewById(R.id.nav_view_header_mail);
                if (textViewMail != null) {
                    textViewMail.setText(user.getMail());
                }

                ImageView imageViewImg = (ImageView) view.findViewById(R.id.nav_view_header_img);
                if (imageViewImg != null) {
                    Glide
                            .with(navigationView.getContext())
                            .load(user.getUrlAvatar())
                            .centerCrop()
                            .crossFade()
                            .into(imageViewImg);
                }
            }
        }
    }

    protected void loadToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }

}
