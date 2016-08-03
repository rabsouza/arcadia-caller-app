package br.com.battista.arcadiacaller.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
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

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.CustomEvent;

import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.util.AppUtils;
import br.com.battista.arcadiacaller.util.ImageLoadUtils;
import lombok.Getter;

import static br.com.battista.arcadiacaller.constants.CrashlyticsConstant.KEY_ACTIVITY;
import static br.com.battista.arcadiacaller.constants.CrashlyticsConstant.KEY_OPEN_ACTIVITY;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    @Getter
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String nameView = this.getClass().getSimpleName();

        Answers.getInstance().logCustom(new CustomEvent(KEY_OPEN_ACTIVITY)
                .putCustomAttribute(KEY_ACTIVITY, nameView));

        Answers.getInstance().logContentView(new ContentViewEvent()
                .putContentName(nameView)
                .putContentType(KEY_ACTIVITY));

        AppUtils.goToHomeIfApplicationIsNull(getContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppUtils.goToHomeIfApplicationIsNull(getContext());
    }

    protected Context getContext() {
        return this;
    }

    protected Activity getActivity() {
        return this;
    }

    protected void setUpToolbar(int title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            changeTitleToolbar(title);

            Log.d(TAG, "setUpToolbar: Active support toolbar!");
            setSupportActionBar(toolbar);
        }
    }

    protected void changeTitleCollapsingToolbar(int titleResId) {
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getContext().getString(titleResId));
    }

    protected void setupToolbarDetail() {
        setSupportActionBar((Toolbar) findViewById(R.id.detail_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    protected void changeTitleToolbar(int title) {
        if (title != 0) {
            toolbar.setTitle(title);
        }
    }

    protected void changeToolbarTitleByMenu(@NonNull MenuItem menuItem) {
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
                    .replace(R.id.container, fragment, fragment.getTag()).commit();
        }
    }

    protected void replaceDetailFragment(Fragment fragment, int resIdContainer) {
        if (fragment != null) {
            Log.d(TAG, "replaceFragment: Change to detail fragment!");
            getSupportFragmentManager().beginTransaction()
                    .replace(resIdContainer, fragment).commit();
        }
    }

    protected void loadNavigationViewHeader(NavigationView navigationView) {
        MainApplication mainApplication = MainApplication.instance();
        AppUtils.goToHomeIfUserIsNull(mainApplication, getContext());

        if (navigationView != null && navigationView.getHeaderCount() > 0) {
            View view = navigationView.getHeaderView(0);
            if (view != null) {
                User user = mainApplication.getUser();
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

                    ImageLoadUtils
                            .loadImageWithImageError(navigationView.getContext(),
                                    user.getUrlAvatar(),
                                    imageViewImg,
                                    R.drawable.profile);
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
