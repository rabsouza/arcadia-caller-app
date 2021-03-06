package br.com.battista.arcadiacaller.activity;

import static br.com.battista.arcadiacaller.constants.CrashlyticsConstant.ANSWERS_SIGN_UP_METHOD;
import static br.com.battista.arcadiacaller.constants.EntityConstant.DEFAULT_VERSION;
import static br.com.battista.arcadiacaller.constants.FriendConstant.URL_AVATAR;
import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_CARD_DATA;
import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_HERO_DATA;
import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_SCENERY_DATA;
import static br.com.battista.arcadiacaller.model.enuns.ActionCacheEnum.LOAD_STATISTIC_USER_DATA;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.SignUpEvent;
import com.google.common.base.Strings;

import java.text.MessageFormat;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.service.CacheManageService;
import br.com.battista.arcadiacaller.cache.EventCache;
import br.com.battista.arcadiacaller.constants.ProfileAppConstant;
import br.com.battista.arcadiacaller.exception.EntityAlreadyExistsException;
import br.com.battista.arcadiacaller.exception.ValidatorException;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.model.dto.FriendDto;
import br.com.battista.arcadiacaller.service.LoginService;
import br.com.battista.arcadiacaller.service.UserService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

public class SignInActivity extends BaseActivity {

    private static final String TAG = SignInActivity.class.getSimpleName();
    private EditText mTxtUsername;
    private EditText mTxtMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void save(View view) {
        mTxtUsername = (EditText) findViewById(R.id.txt_username);
        if (Strings.isNullOrEmpty(mTxtUsername.getText().toString())) {
            String msgErrorUsername = getContext().getString(R.string.msg_username_required);
            AndroidUtils.changeErrorEditText(mTxtUsername, msgErrorUsername, true);
            return;
        }
        AndroidUtils.changeErrorEditText(mTxtUsername);
        final String username = mTxtUsername.getText().toString().trim();

        mTxtMail = (EditText) findViewById(R.id.txt_mail);
        if (Strings.isNullOrEmpty(mTxtMail.getText().toString())) {
            String msgErrorMail = getContext().getString(R.string.msg_mail_required);
            AndroidUtils.changeErrorEditText(mTxtMail, msgErrorMail, true);
            return;
        }
        AndroidUtils.changeErrorEditText(mTxtMail);
        final String mail = mTxtMail.getText().toString().trim();

        Log.d(TAG, MessageFormat.format("Create new user with username: {0} and mail :{1}", username, mail));

        final View currentView = view;
        new ProgressApp(this, R.string.msg_action_login, false) {
            private User user;
            private String token;
            private Boolean alreadyExistseUser = Boolean.FALSE;

            @Override
            protected void onPostExecute(Boolean result) {
                if (!result && alreadyExistseUser) {
                    Log.d(TAG, "onPostExecute: Failed in create user!");
                    AndroidUtils.snackbar(currentView, R.string.msg_failed_already_exists_user);

                    Answers.getInstance().logSignUp(new SignUpEvent()
                            .putMethod(ANSWERS_SIGN_UP_METHOD)
                            .putSuccess(false));
                } else if (!result || user == null || user.getVersion() == null) {
                    Log.d(TAG, "onPostExecute: Failed in create user!");
                    AndroidUtils.snackbar(currentView, R.string.msg_failed_create_user);

                    Answers.getInstance().logSignUp(new SignUpEvent()
                            .putMethod(ANSWERS_SIGN_UP_METHOD)
                            .putSuccess(false));
                } else {
                    Log.d(TAG, "onPostExecute: Success in create user!");
                    EventCache.createEvent(LOAD_CARD_DATA, LOAD_HERO_DATA, LOAD_SCENERY_DATA, LOAD_STATISTIC_USER_DATA);

                    Answers.getInstance().logSignUp(new SignUpEvent()
                            .putMethod(ANSWERS_SIGN_UP_METHOD)
                            .putSuccess(true));

                    loadMainActivity();
                }
                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    LoginService service = Inject.provideLoginService();
                    Log.d(TAG, MessageFormat.format(
                            "doInBackground: create to user with username: {0} and mail {1}.", username, mail));

                    User userBuild = new User()
                            .username(username)
                            .mail(mail)
                            .profile(ProfileAppConstant.APP);

                    user = service.create(userBuild);
                    if (user != null && DEFAULT_VERSION.equals(user.getVersion())) {
                        token = service.login(user.getUsername());

                        MainApplication instance = MainApplication.instance();
                        instance.setToken(token);
                        loadAllFriend(user, token);
                        instance.setUser(user);
                        new CacheManageService().onActionCache(LOAD_STATISTIC_USER_DATA);
                    }
                    return true;
                } catch (EntityAlreadyExistsException e) {
                    Log.e(TAG, "EntityAlreadyExistsException: Error create new user!", e);
                    alreadyExistseUser = true;
                } catch (ValidatorException e) {
                    Log.e(TAG, "ValidatorException: Error create new user!", e);
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                }
                return false;
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

    private void loadLoginActivity() {
        Bundle args = new Bundle();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

    private void loadMainActivity() {
        Bundle args = new Bundle();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }


    public void cancel(View view) {
        Log.i(TAG, "Start activity: LoginActivity!");

        loadLoginActivity();
    }
}
