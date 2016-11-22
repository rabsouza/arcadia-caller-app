package br.com.battista.arcadiacaller.activity;

import static br.com.battista.arcadiacaller.constants.CrashlyticsConstant.ANSWERS_LOGIN_METHOD;
import static br.com.battista.arcadiacaller.constants.FriendConstant.URL_AVATAR;
import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_CARD_DATA;
import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_HERO_DATA;
import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_SCENERY_DATA;
import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_STATISTIC_USER_DATA;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.LoginEvent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
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
import br.com.battista.arcadiacaller.model.dto.FriendDto;
import br.com.battista.arcadiacaller.model.enuns.SharedPreferencesKeyEnum;
import br.com.battista.arcadiacaller.service.AppService;
import br.com.battista.arcadiacaller.service.LoginService;
import br.com.battista.arcadiacaller.service.UserService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

public class LoginActivity extends BaseActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;

    private EditText mTxtUsername = null;
    private CheckBox chbSavedUsername = null;
    private TextView title_text = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppService appService = Inject.provideAppService();
        appService.ping();
        appService.health();

        loadPreferences();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

// Build a GoogleApiClient with access to GoogleSignIn.API and the options above.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // [START customize_button]
        // Set the dimensions of the sign-in button.
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);
        // [END customize_button

        title_text = (TextView) findViewById(R.id.title_text);

    }

    private void updateUI(boolean signedIn) {
        if (!signedIn) {
            findViewById(R.id.title_text).setVisibility(View.GONE);
        } else {
            findViewById(R.id.title_text).setVisibility(View.VISIBLE);
        }
    }

    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: ERROR: " + requestCode);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    // [END onActivityResult]

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess() + ", status: " + result.getStatus().getStatusCode());
        updateUI(result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            title_text.setText("[email: " + acct.getEmail() + ", id: " + acct.getId() + ", photo: " + acct.getPhotoUrl() + "]");
        } else {
            // Signed out, show unauthenticated UI.
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        }
    }
    // [END handleSignInResult]

    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void loadPreferences() {
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
        MainApplication instance = MainApplication.instance();
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
                        MainApplication instance = MainApplication.instance();
                        instance.setToken(token);
                        instance.setUser(user);

                        loadAllFriend(user, token);
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

            private void loadAllFriend(User user, String token) {
                if (user != null && !user.getFriends().isEmpty()) {
                    UserService userService = Inject.provideUserService();

                    for (String friend : user.getFriends()) {

                        final User userFriend = userService.findByUsername(token, friend);
                        if (userFriend != null) {
                            final String urlAvatar;
                            if (ProfileAppConstant.FRIEND.equals(userFriend.getProfile())) {
                                urlAvatar = URL_AVATAR;
                            } else {
                                urlAvatar = userFriend.getUrlAvatar();
                            }
                            final FriendDto friendDto = new FriendDto().username(userFriend.getUsername()).urlAvatar(urlAvatar).profile(userFriend.getProfile());
                            user.addFriend(friendDto);
                        }
                    }
                }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }
}
