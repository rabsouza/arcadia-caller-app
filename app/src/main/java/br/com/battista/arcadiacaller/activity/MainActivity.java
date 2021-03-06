package br.com.battista.arcadiacaller.activity;

import static br.com.battista.arcadiacaller.R.id.nav_menu_campaign;
import static br.com.battista.arcadiacaller.R.id.nav_menu_friends;
import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_STATISTIC_USER_DATA;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.crashlytics.android.Crashlytics;

import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.cache.EventCache;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.CampaignsFragment;
import br.com.battista.arcadiacaller.fragment.CardsFragment;
import br.com.battista.arcadiacaller.fragment.FriendsFragment;
import br.com.battista.arcadiacaller.fragment.HeroesFragment;
import br.com.battista.arcadiacaller.fragment.HomeFragment;
import br.com.battista.arcadiacaller.fragment.SceneriesFragment;
import br.com.battista.arcadiacaller.fragment.dialog.AboutDialog;
import br.com.battista.arcadiacaller.model.enuns.ActionEnum;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadToolbar();
        setUpToolbar(R.string.title_app);
        loadDrawer();
        loadNavigationViewHeader();
        logUserCrashlytics();

        loadFragmentInitial(getIntent().getExtras());

    }

    private void logUserCrashlytics() {
        MainApplication instance = MainApplication.instance();
        Crashlytics.setUserIdentifier(instance.getToken());
        Crashlytics.setUserEmail(instance.getUser().getMail());
        Crashlytics.setUserName(instance.getUser().getUsername());
    }

    private void loadFragmentInitial(Bundle extras) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (extras != null && extras.containsKey(BundleConstant.ACTION)) {
            ActionEnum action = ActionEnum.get(extras.get(BundleConstant.ACTION).toString());
            if (ActionEnum.START_FRAGMENT_CAMPAIGNS.equals(action)) {
                Log.i(TAG, "loadFragmentInitial: Load the CampaignsFragment!");
                changeTitleToolbar(R.string.title_campaigns);

                navigationView.getMenu().getItem(1).setChecked(true);

                replaceFragment(CampaignsFragment.newInstance());
                return;
            }
        }
        Log.i(TAG, "loadFragmentInitial: Load the HomeFragment!");
        navigationView.getMenu().getItem(0).setChecked(true);
        replaceFragment(HomeFragment.newInstance());
    }

    private void loadNavigationViewHeader() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        super.loadNavigationViewHeader(navigationView);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.getMenu().getItem(0).setChecked(true);
            replaceFragment(HomeFragment.newInstance());
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        changeBtnOpenCampaign(View.GONE);
        if (id == R.id.nav_menu_home) {
            Log.d(TAG, "onNavigationItemSelected: Go to menu Home.");
            changeToolbarTitleByMenu(menuItem);
            replaceFragment(HomeFragment.newInstance());

        } else if (id == nav_menu_campaign) {
            Log.d(TAG, "onNavigationItemSelected: Go to menu Campaign.");
            changeToolbarTitleByMenu(menuItem);
            changeBtnOpenCampaign(View.VISIBLE);
            replaceFragment(CampaignsFragment.newInstance());

        } else if (id == nav_menu_friends) {
            Log.d(TAG, "onNavigationItemSelected: Go to menu Friends.");
            changeToolbarTitleByMenu(menuItem);
            replaceFragment(FriendsFragment.newInstance());

        } else if (id == R.id.nav_menu_heroes) {
            Log.d(TAG, "onNavigationItemSelected: Go to menu Heroes.");
            changeToolbarTitleByMenu(menuItem);
            replaceFragment(HeroesFragment.newInstance());

        } else if (id == R.id.nav_menu_cards) {
            Log.d(TAG, "onNavigationItemSelected: Go to menu Cards.");
            changeToolbarTitleByMenu(menuItem);
            replaceFragment(CardsFragment.newInstance());

        } else if (id == R.id.nav_menu_sceneries) {
            Log.d(TAG, "onNavigationItemSelected: Go to menu Sceneries.");
            changeToolbarTitleByMenu(menuItem);
            replaceFragment(SceneriesFragment.newInstance());

        } else if (id == R.id.nav_menu_about) {
            Log.d(TAG, "onNavigationItemSelected: Go to menu Help.");
            AboutDialog.showAbout(getSupportFragmentManager());

        } else if (id == R.id.nav_menu_logout) {
            Log.d(TAG, "onNavigationItemSelected: Go to menu Logout.");
            new AlertDialog.Builder(this)
                    .setTitle(R.string.alert_confirmation_dialog_title_exit)
                    .setMessage(R.string.alert_confirmation_dialog_text_logout)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(R.string.btn_confirmation_dialog_exit, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            loadLoginActivity();
                        }
                    })
                    .setNegativeButton(R.string.btn_confirmation_dialog_cancel, null).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadLoginActivity() {
        Bundle args = new Bundle();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventCache.createEvent(LOAD_STATISTIC_USER_DATA);
    }
}
