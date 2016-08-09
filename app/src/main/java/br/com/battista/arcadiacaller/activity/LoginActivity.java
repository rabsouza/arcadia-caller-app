package br.com.battista.arcadiacaller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.LoginEvent;
import com.google.common.base.Strings;

import java.text.MessageFormat;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.cache.CacheManagerService;
import br.com.battista.arcadiacaller.cache.EventCache;
import br.com.battista.arcadiacaller.constants.ProfileAppConstant;
import br.com.battista.arcadiacaller.exception.AuthenticationException;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.model.enuns.SharedPreferencesKeyEnum;
import br.com.battista.arcadiacaller.service.AppService;
import br.com.battista.arcadiacaller.service.LoginService;
import br.com.battista.arcadiacaller.service.UserService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

import static br.com.battista.arcadiacaller.constants.CrashlyticsConstant.ANSWERS_LOGIN_METHOD;
import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_CARD_DATA;
import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_HERO_DATA;
import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_SCENERY_DATA;
import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_STATISTIC_USER_DATA;

public class LoginActivity extends BaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText mTxtUsername = null;
    private CheckBox chbSavedUsername = null;
    private AppService appService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appService = Inject.provideAppService();
        appService.ping();
        appService.health();

        String username = MainApplication.instance().getPreferences(SharedPreferencesKeyEnum.SAVED_USERNAME);
        if (!Strings.isNullOrEmpty(username)) {
            mTxtUsername = (EditText) findViewById(R.id.txt_username);
            mTxtUsername.setText(username);

            chbSavedUsername = (CheckBox) findViewById(R.id.chb_saved_username);
            chbSavedUsername.setChecked(Boolean.TRUE);
        }
    }

    public void login(View view) {
        mTxtUsername = (EditText) findViewById(R.id.txt_username);
        if (Strings.isNullOrEmpty(mTxtUsername.getText().toString())) {
            String msgErrorUsername = getContext().getString(R.string.msg_username_required);
            AndroidUtils.changeErrorEditText(mTxtUsername, msgErrorUsername, true);
            return;
        }
        AndroidUtils.changeErrorEditText(mTxtUsername);

        final String username = mTxtUsername.getText().toString().trim();
        Log.d(TAG, MessageFormat.format("Login user with username: {0}", username));

        chbSavedUsername = (CheckBox) findViewById(R.id.chb_saved_username);
        final MainApplication instance = MainApplication.instance();
        if (chbSavedUsername.isChecked()) {
            instance.putPreferences(SharedPreferencesKeyEnum.SAVED_USERNAME, username);
        } else {
            instance.putPreferences(SharedPreferencesKeyEnum.SAVED_USERNAME, null);
        }

        final View currentView = view;
        new ProgressApp(this, R.string.msg_action_login, false) {

            private String token;
            private User user;

            @Override
            protected void onPostExecute(Boolean result) {
                if (Strings.isNullOrEmpty(token) || user == null) {
                    Log.d(TAG, "onPostExecute: Failed in login!");
                    AndroidUtils.snackbar(currentView, R.string.msg_failed_login_user);

                    Answers.getInstance().logLogin(new LoginEvent()
                            .putMethod(ANSWERS_LOGIN_METHOD)
                            .putSuccess(false));
                } else if (ProfileAppConstant.FRIEND.equals(user.getProfile())) {
                    Log.d(TAG, "onPostExecute: Failed in login a Friend!");
                    AndroidUtils.snackbar(currentView, R.string.msg_failed_login_user);

                    Answers.getInstance().logLogin(new LoginEvent()
                            .putMethod(ANSWERS_LOGIN_METHOD)
                            .putSuccess(false));
                } else {
                    Log.d(TAG, "onPostExecute: Success in login!");
                    EventCache.createEvent(LOAD_CARD_DATA, LOAD_HERO_DATA, LOAD_SCENERY_DATA, LOAD_STATISTIC_USER_DATA);

                    Answers.getInstance().logLogin(new LoginEvent()
                            .putMethod(ANSWERS_LOGIN_METHOD)
                            .putSuccess(true));

                    loadMainActivity();
                }
                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    LoginService service = Inject.provideLoginService();
                    Log.d(TAG, MessageFormat.format("doInBackground: Login username: {}.", username));

                    token = service.login(username);
                    if (!Strings.isNullOrEmpty(token)) {
                        UserService userService = Inject.provideUserService();
                        Log.d(TAG, MessageFormat.format("doInBackground: Find user by username: {}.", username));

                        user = userService.findByUsername(token, username);
                        instance.setToken(token);
                        instance.setUser(user);

                        new CacheManagerService().onActionCache(LOAD_STATISTIC_USER_DATA);
                    }
                } catch (AuthenticationException e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }
        }.execute();
    }

    public void signIn(View view) {
        Log.i(TAG, "Start activity: SignInActivity!");

        loadSignInActivity();
    }

    private void loadSignInActivity() {
        Bundle args = new Bundle();
        Intent intent = new Intent(this, SignInActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

    private void loadMainActivity() {
        Bundle args = new Bundle();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

}
