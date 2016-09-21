package br.com.battista.arcadiacaller.activity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static br.com.battista.arcadiacaller.helper.AndroidTestUtils.withError;
import static br.com.battista.arcadiacaller.helper.TestConstant.DATA_USER_TEST_INVALID_USERNAME;
import static br.com.battista.arcadiacaller.helper.TestConstant.DATA_USER_TEST_MAIL;
import static junit.framework.Assert.assertNotNull;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.helper.LoginActivityHelper;
import br.com.battista.arcadiacaller.model.User;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityTestRule =
            new ActivityTestRule<>(LoginActivity.class);
    private EditText txtUsername = null;
    private Button btnLogin = null;
    private Button btnSignIn = null;
    private CheckBox chkRememberMe = null;

    @Before
    public void setup() {
        chkRememberMe = (CheckBox) mLoginActivityTestRule.getActivity().findViewById(R.id.chb_saved_username);
        if (chkRememberMe.isChecked()) {
            onView(withId(R.id.chb_saved_username))
                    .perform(click());
        }

        onView(withId(R.id.txt_username))
                .perform(clearText());
    }

    @Test
    public void checkExistsTheElementsActivity() {
        LoginActivity activity = mLoginActivityTestRule.getActivity();

        txtUsername = (EditText) activity.findViewById(R.id.txt_username);
        assertNotNull(txtUsername);

        btnLogin = (Button) activity.findViewById(R.id.btn_login);
        assertNotNull(btnLogin);

        btnSignIn = (Button) activity.findViewById(R.id.btn_sign_in);
        assertNotNull(btnSignIn);

        chkRememberMe = (CheckBox) activity.findViewById(R.id.chb_saved_username);
        assertNotNull(chkRememberMe);
    }

    @Test
    public void shouldShowErrorValidationWhenClickBtnLoginWithEmptyLogin() {
        LoginActivity activity = mLoginActivityTestRule.getActivity();
        String error = activity.getString(R.string.msg_username_required);

        onView(withId(R.id.txt_username))
                .perform(clearText());


        onView(withId(R.id.btn_login)).
                perform(click());

        onView(withId(R.id.txt_username))
                .check(matches(withError(error)));

    }

    @Test
    public void shouldShowMsgErrorWhenClickBtnLoginWithInvalidLogin() {
        onView(withId(R.id.txt_username))
                .perform(clearText(), typeText(DATA_USER_TEST_INVALID_USERNAME), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).
                perform(click());

        onView(withText(R.string.msg_failed_login_user))
                .check(matches(isDisplayed()));

    }

    @Test
    public void shouldShowMsgErrorWhenClickBtnLoginWithValidMail() {
        onView(withId(R.id.txt_username))
                .perform(clearText(), typeText(DATA_USER_TEST_MAIL), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).
                perform(click());

        onView(withText(R.string.msg_failed_login_user))
                .check(matches(isDisplayed()));

    }

    @Test
    public void shouldShowMainActivityWhenClickBtnLoginWithValidLogin() {
        User user = LoginActivityHelper.createNewUser();

        onView(withId(R.id.txt_username))
                .perform(clearText(), typeText(user.getUsername()), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).
                perform(click());

        onView(withText(R.string.title_app))
                .check(matches(isDisplayed()));

    }

    @Test
    public void shouldShowMainActivityWhenClickBtnLoginWithValidLoginAndSpace() {
        User user = LoginActivityHelper.createNewUser();

        onView(withId(R.id.txt_username))
                .perform(clearText(), typeText(user.getUsername() + "  "), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).
                perform(click());

        onView(withText(R.string.title_app))
                .check(matches(isDisplayed()));

    }

    @Test
    public void shouldShowMainActivityWhenClickBtnLoginWithValidLoginAndCheckRememberMe() {
        User user = LoginActivityHelper.createNewUser();

        onView(withId(R.id.txt_username))
                .perform(clearText(), typeText(user.getUsername()), closeSoftKeyboard());

        onView(withId(R.id.btn_login))
                .perform(click());

        onView(withText(R.string.title_app))
                .check(matches(isDisplayed()));

    }

    @Test
    public void shouldShowMainActivityWhenClickBtnLoginWithValidLoginAndUnCheckRememberMe() {
        User user = LoginActivityHelper.createNewUser();

        onView(withId(R.id.txt_username))
                .perform(clearText(), typeText(user.getUsername()), closeSoftKeyboard());

        onView(withId(R.id.chb_saved_username))
                .perform(click());

        onView(withId(R.id.btn_login))
                .perform(click());

        onView(withText(R.string.title_app))
                .check(matches(isDisplayed()));

    }

    @Test
    public void shouldRememberMeWhenClickBtnLoginWithValidLoginAndCheckRememberMe() {
        User user = LoginActivityHelper.createNewUser();

        onView(withId(R.id.txt_username))
                .perform(clearText(), typeText(user.getUsername()), closeSoftKeyboard());

        onView(withId(R.id.chb_saved_username))
                .perform(click());

        onView(withId(R.id.btn_login))
                .perform(click());

        onView(withText(R.string.title_app))
                .check(matches(isDisplayed()));

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(open());

        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_menu_logout));

        onView(withText(R.string.btn_confirmation_dialog_exit))
                .perform(click());

        onView(withId(R.id.txt_username))
                .check(matches(withText(user.getUsername())));


        onView(withId(R.id.chb_saved_username))
                .check(matches(isChecked()));
    }

}