package br.com.battista.arcadiacaller.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.fragment.HomeFragment;
import br.com.battista.arcadiacaller.fragment.dialog.AboutDialog;
import br.com.battista.arcadiacaller.util.AndroidUtils;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFloatingAction();
        loadToolbar();
        setUpToolbar(R.string.title_app);
        loadDrawer();
        loadNavigationViewHeader();
        loadFragmentInitial();
    }

    private void loadFragmentInitial() {
        replaceFragment(HomeFragment.newInstance());
    }

    private void loadNavigationViewHeader() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        super.loadNavigationViewHeader(navigationView);
    }

    private void loadFloatingAction() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "No action!!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
            changeToolbarTitle(menuItem);
            replaceFragment(HomeFragment.newInstance());
            AndroidUtils.toast(getContext(), R.string.msg_blank_fragment);

        } else if (id == R.id.nav_menu_manage) {
            Log.d(TAG, "onNavigationItemSelected: Go to menu Manage.");
            changeToolbarTitle(menuItem);
            AndroidUtils.toast(getContext(), R.string.msg_blank_fragment);

        } else if (id == R.id.nav_menu_help) {
            Log.d(TAG, "onNavigationItemSelected: Go to menu Help.");
            AboutDialog.showAbout(getSupportFragmentManager());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
