package br.com.battista.arcadiacaller.activity;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.model.User;
import lombok.Getter;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    @Getter
    private Toolbar toolbar;

    protected Context getContext() {
        return this;
    }

    protected Activity getActivity() {
        return this;
    }

    protected void setUpToolbar(int title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            if (title != 0) {
                toolbar.setTitle(title);
            }

            Log.d(TAG, "setUpToolbar: Active support toolbar!");
            setSupportActionBar(toolbar);
        }
    }

    protected void changeToolbarTitle(MenuItem menuItem) {
        CharSequence title = menuItem.getTitle();
        Log.i(TAG, String.format("onNavigationItemSelected: Select menu item: %s!", title));
        if (toolbar != null) {
            toolbar.setTitle(title);
        }
    }

    protected void replaceFragment(Fragment fragment) {
        if (fragment != null) {
            Log.d(TAG, "replaceFragment: Change to fragment!");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment).commit();
        }
    }

    protected void loadNavigationViewHeader(NavigationView navigationView) {
        if (navigationView != null && navigationView.getHeaderCount() > 0) {
            View view = navigationView.getHeaderView(0);
            if (view != null) {
                User user = MainApplication.instance().getUser();
                Log.i(TAG, String.format(
                        "loadNavigationViewHeader: Fill navigation header with user: !", user));

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
                    Glide.with(navigationView.getContext())
                            .load(user.getUrlAvatar())
                            .centerCrop()
                            .crossFade()
                            .into(imageViewImg);
                }
            }
        }
    }

    protected void loadToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected void loadDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

}
