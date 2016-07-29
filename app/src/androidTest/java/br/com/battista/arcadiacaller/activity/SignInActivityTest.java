package br.com.battista.arcadiacaller.activity;

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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static br.com.battista.arcadiacaller.helper.AndroidTestUtils.withError;
import static br.com.battista.arcadiacaller.helper.TestConstant.DATA_USER_TEST_MAIL;
import static br.com.battista.arcadiacaller.helper.TestConstant.DATA_USER_TEST_USERNAME;
import static junit.framework.Assert.assertNotNull;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SignInActivityTest {

    @Rule
    public ActivityTestRule<SignInActivity> mSignInActivityTestRule =
            new ActivityTestRule<>(SignInActivity.class);
    private EditText mTxtUsername = null;
    private EditText mTxtMail = null;
    private Button mButtonCreate = null;
    private Button mButtonCancel = null;

    @Test
    public void checkExistsTheElementsActivity() {
        SignInActivity activity = mSignInActivityTestRule.getActivity();

        mTxtUsername = (EditText) activity.findViewById(R.id.txt_username);
        assertNotNull(mTxtUsername);

        mTxtMail = (EditText) activity.findViewById(R.id.txt_mail);
        assertNotNull(mTxtMail);

        mButtonCreate = (Button) activity.findViewById(R.id.btn_create);
        assertNotNull(mButtonCreate);

        mButtonCancel = (Button) activity.findViewById(R.id.btn_cancel);
        assertNotNull(mButtonCancel);
    }

    @Test
    public void shouldShowErrorValidationWhenClickBtnCreateWithEmptyLogin() {
        SignInActivity activity = mSignInActivityTestRule.getActivity();
        String error = activity.getString(R.string.msg_username_required);

        onView(withId(R.id.btn_create)).
                perform(click());

        onView(withId(R.id.txt_username))
                .check(matches(withError(error)));

    }

    @Test
    public void shouldShowErrorValidationWhenClickBtnCreateWithEmptyMail() {
        SignInActivity activity = mSignInActivityTestRule.getActivity();
        String error = activity.getString(R.string.msg_mail_required);

        onView(withId(R.id.txt_username))
                .perform(typeText(DATA_USER_TEST_USERNAME), closeSoftKeyboard());

        onView(withId(R.id.btn_create)).
                perform(click());

        onView(withId(R.id.txt_mail))
                .check(matches(withError(error)));

    }

    @Test
    public void shouldShowMainActivityWhenClickBtnCreateWithValidLoginAndMail() {
        onView(withId(R.id.txt_username))
                .perform(typeText("user" + System.currentTimeMillis()), closeSoftKeyboard());

        onView(withId(R.id.txt_mail))
                .perform(typeText(DATA_USER_TEST_MAIL), closeSoftKeyboard());

        onView(withId(R.id.btn_create)).
                perform(click());

        onView(withText(R.string.title_app))
                .check(matches(isDisplayed()));

    }

    @Test
    public void shouldShowMsgErrorWhenClickBtnCreateAndExistUser() {
        LoginActivityHelper.createNewUser();

        onView(withId(R.id.txt_username))
                .perform(typeText(DATA_USER_TEST_USERNAME), closeSoftKeyboard());

        onView(withId(R.id.txt_mail))
                .perform(typeText(DATA_USER_TEST_MAIL), closeSoftKeyboard());

        onView(withId(R.id.btn_create)).
                perform(click());

        onView(withText(R.string.msg_failed_already_exists_user))
                .check(matches(isDisplayed()));

    }

    @Test
    public void shouldShowMsgErrorWhenClickBtnCreateWithShortUsername() {
        onView(withId(R.id.txt_username))
                .perform(typeText("abc"), closeSoftKeyboard());

        onView(withId(R.id.txt_mail))
                .perform(typeText(DATA_USER_TEST_MAIL), closeSoftKeyboard());

        onView(withId(R.id.btn_create)).
                perform(click());

        onView(withText(R.string.msg_failed_create_user))
                .check(matches(isDisplayed()));

    }

    @Test
    public void shouldShowMsgErrorWhenClickBtnCreateWithBigUsername() {
        String userName = "abcdefghijklmnopqrstuvxwyzABCDEFGHIJKLMNOPQRSTUVXWYZ_01234567890";
        onView(withId(R.id.txt_username))
                .perform(typeText(userName), closeSoftKeyboard());

        onView(withId(R.id.txt_mail))
                .perform(typeText(DATA_USER_TEST_MAIL), closeSoftKeyboard());

        onView(withId(R.id.btn_create)).
                perform(click());

        onView(withText(R.string.msg_failed_create_user))
                .check(matches(isDisplayed()));

    }

    @Test
    public void shouldShowMsgErrorWhenClickBtnCreateWithInvalidUsername() {
        String userName = "user-01@01";
        onView(withId(R.id.txt_username))
                .perform(typeText(userName), closeSoftKeyboard());

        onView(withId(R.id.txt_mail))
                .perform(typeText(DATA_USER_TEST_MAIL), closeSoftKeyboard());

        onView(withId(R.id.btn_create)).
                perform(click());

        onView(withText(R.string.msg_failed_create_user))
                .check(matches(isDisplayed()));

    }

    @Test
    public void shouldShowMsgErrorWhenClickBtnCreateWithInvalidMail() {
        onView(withId(R.id.txt_username))
                .perform(typeText("user" + System.currentTimeMillis()), closeSoftKeyboard());

        onView(withId(R.id.txt_mail))
                .perform(typeText("mail.test.com"), closeSoftKeyboard());

        onView(withId(R.id.btn_create)).
                perform(click());

        onView(withText(R.string.msg_failed_create_user))
                .check(matches(isDisplayed()));

    }
}