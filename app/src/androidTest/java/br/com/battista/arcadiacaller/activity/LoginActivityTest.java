package br.com.battista.arcadiacaller.activity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
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
import android.widget.Button;
import android.widget.EditText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.helper.LoginActivityHelper;
import br.com.battista.arcadiacaller.model.User;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    private EditText mTxtUsername = null;
    private Button mButtonLogin = null;
    private Button mButtonSignIn = null;

    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void checkExistsTheElementsActivity() {
        LoginActivity activity = mLoginActivityTestRule.getActivity();

        mTxtUsername = (EditText) activity.findViewById(R.id.txt_username);
        assertNotNull(mTxtUsername);

        mButtonLogin = (Button) activity.findViewById(R.id.btn_login);
        assertNotNull(mButtonLogin);

        mButtonSignIn = (Button) activity.findViewById(R.id.btn_sign_in);
        assertNotNull(mButtonSignIn);
    }

    @Test
    public void shouldShowErrorValidationWhenClickBtnLoginWithEmptyLogin() {
        LoginActivity activity = mLoginActivityTestRule.getActivity();
        String error = activity.getString(R.string.msg_username_required);

        onView(withId(R.id.btn_login)).
                perform(click());

        onView(withId(R.id.txt_username))
                .check(matches(withError(error)));

    }

    @Test
    public void shouldShowMsgErrorWhenClickBtnLoginWithInvalidLogin() {
        onView(withId(R.id.txt_username))
                .perform(typeText(DATA_USER_TEST_INVALID_USERNAME), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).
                perform(click());

        onView(withText(R.string.msg_failed_login_user))
                .check(matches(isDisplayed()));

    }

    @Test
    public void shouldShowMsgErrorWhenClickBtnLoginWithValidMail() {
        onView(withId(R.id.txt_username))
                .perform(typeText(DATA_USER_TEST_MAIL), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).
                perform(click());

        onView(withText(R.string.msg_failed_login_user))
                .check(matches(isDisplayed()));

    }

    @Test
    public void shouldShowMainActivityWhenClickBtnLoginWithValidLogin() {
        User user = LoginActivityHelper.createNewUser();

        onView(withId(R.id.txt_username))
                .perform(typeText(user.getUsername()), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).
                perform(click());

        onView(withText(R.string.title_app))
                .check(matches(isDisplayed()));

    }

    @Test
    public void shouldShowMainActivityWhenClickBtnLoginWithValidLoginAndSpace() {
        User user = LoginActivityHelper.createNewUser();

        onView(withId(R.id.txt_username))
                .perform(typeText(user.getUsername() + "  "), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).
                perform(click());

        onView(withText(R.string.title_app))
                .check(matches(isDisplayed()));

    }

}