package br.com.battista.arcadiacaller.activity;

import static br.com.battista.arcadiacaller.R.id.container;
import static br.com.battista.arcadiacaller.R.id.nav_menu_campaign;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;

import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.CampaignsFragment;
import br.com.battista.arcadiacaller.fragment.CardsFragment;
import br.com.battista.arcadiacaller.fragment.HeroesFragment;
import br.com.battista.arcadiacaller.fragment.HomeFragment;
import br.com.battista.arcadiacaller.fragment.SceneriesFragment;
import br.com.battista.arcadiacaller.fragment.dialog.AboutDialog;
import br.com.battista.arcadiacaller.model.enuns.ActionEnum;
import br.com.battista.arcadiacaller.util.AndroidUtils;

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
        loadFragmentInitial(getIntent().getExtras());
        logUserCrashlytics();

    }

    private void logUserCrashlytics() {
        MainApplication instance = MainApplication.instance();
        Crashlytics.setUserIdentifier(instance.getToken());
        Crashlytics.setUserEmail(instance.getUser().getMail());
        Crashlytics.setUserName(instance.getUser().getUsername());
    }

    private void loadFragmentInitial(Bundle extras) {
        if (extras != null && extras.containsKey(BundleConstant.ACTION)) {
            ActionEnum action = ActionEnum.get(extras.get(BundleConstant.ACTION).toString());
            if (ActionEnum.START_FRAGMENT_CAMPAIGNS.equals(action)) {
                Log.i(TAG, "loadFragmentInitial: Load the CampaignsFragment!");
                changeTitleToolbar(R.string.title_campaigns);
                replaceFragment(CampaignsFragment.newInstance());
                return;
            }
        }
        Log.i(TAG, "loadFragmentInitial: Load the HomeFragment!");
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
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_menu_home) {
            Log.d(TAG, "onNavigationItemSelected: Go to menu Home.");
            changeToolbarTitleByMenu(menuItem);
            replaceFragment(HomeFragment.newInstance());

        } else if (id == nav_menu_campaign) {
            Log.d(TAG, "onNavigationItemSelected: Go to menu Campaign.");
            changeToolbarTitleByMenu(menuItem);
            replaceFragment(CampaignsFragment.newInstance());

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

        } else if (id == R.id.nav_menu_manage) {
            Log.d(TAG, "onNavigationItemSelected: Go to menu Manage.");
            changeToolbarTitleByMenu(menuItem);
            AndroidUtils.snackbar(findViewById(container), R.string.msg_blank_fragment);

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
}
