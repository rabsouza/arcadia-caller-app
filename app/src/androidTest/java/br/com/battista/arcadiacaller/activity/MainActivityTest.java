package br.com.battista.arcadiacaller.activity;

import android.support.design.widget.NavigationView;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.TextView;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.helper.LoginActivityHelper;
import br.com.battista.arcadiacaller.model.User;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @BeforeClass
    public static void setup() {
        User user = LoginActivityHelper.createNewUser();
        String token = LoginActivityHelper.loginUser(user.getUsername());
        MainApplication mainApplication = MainApplication.instance();
        mainApplication.setToken(token);
        mainApplication.setUser(user);
    }

    @Test
    public void checkExistsTheElementsActivity() {
        MainActivity activity = mActivityTestRule.getActivity();

        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        assertNotNull(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        assertNotNull(drawerLayout);

        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        assertNotNull(navigationView);
    }

    @Test
    public void checkExistMenuInNavigationDrawer() {
        MainActivity activity = mActivityTestRule.getActivity();

        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        assertNotNull(navigationView);

        assertThat(navigationView.getMenu().size(), equalTo(8));

        assertNotNull(navigationView.getMenu().findItem(R.id.nav_menu_home));
        assertNotNull(navigationView.getMenu().findItem(R.id.nav_menu_campaign));
        assertNotNull(navigationView.getMenu().findItem(R.id.nav_menu_heroes));
        assertNotNull(navigationView.getMenu().findItem(R.id.nav_menu_cards));
        assertNotNull(navigationView.getMenu().findItem(R.id.nav_menu_sceneries));
        assertNotNull(navigationView.getMenu().findItem(R.id.nav_menu_manage));
        assertNotNull(navigationView.getMenu().findItem(R.id.nav_menu_about));
        assertNotNull(navigationView.getMenu().findItem(R.id.nav_menu_logout));
    }

    @Test
    public void shouldShowMyProfileWhenStartingMainActivity() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)));

        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText(R.string.title_app)));
    }

    @Test
    public void shouldShowHomeWhenClickMenuHome() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(open());

        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_menu_home));

        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText(R.string.title_home)));
    }

    @Test
    public void shouldShowCampaignWhenClickMenuCampaign() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(open());

        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_menu_campaign));

        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText(R.string.title_campaigns)));
    }

    @Test
    public void shouldShowCardsWhenClickMenuCards() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(open());

        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_menu_cards));

        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText(R.string.title_cards)));
    }

    @Test
    public void shouldShowHeroesWhenClickMenuHeroes() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(open());

        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_menu_heroes));

        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText(R.string.title_heroes)));
    }

    @Test
    public void shouldShowSceneriesWhenClickMenuSceneries() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(open());

        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_menu_sceneries));

        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText(R.string.title_sceneries)));
    }

    @Test
    public void shouldShowManageWhenClickMenuManage() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(open());

        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_menu_manage));

        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText(R.string.title_manage)));
    }

    @Test
    public void ShouldShowDialogAboutWhenClickMenuAbout() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(open());

        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_menu_about));

        onView(withText(R.string.title_about))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowLogoutWhenClickMenuLogout() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(open());

        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_menu_logout));

        onView(withId(R.id.btn_login))
                .check(matches(isDisplayed()));

        onView(withId(R.id.btn_sign_in))
                .check(matches(isDisplayed()));
    }

}