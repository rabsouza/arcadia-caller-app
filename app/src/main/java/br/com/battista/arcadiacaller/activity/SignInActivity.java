package br.com.battista.arcadiacaller.activity;

import static br.com.battista.arcadiacaller.constants.EntityConstant.DEFAULT_VERSION;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.common.base.Strings;

import java.text.MessageFormat;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.ProfileAppConstant;
import br.com.battista.arcadiacaller.exception.EntityAlreadyExistsException;
import br.com.battista.arcadiacaller.exception.ValidatorException;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.service.LoginService;
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
                } else if (!result || user == null || user.getVersion() == null) {
                    Log.d(TAG, "onPostExecute: Failed in create user!");
                    AndroidUtils.snackbar(currentView, R.string.msg_failed_create_user);
                } else {
                    Log.d(TAG, "onPostExecute: Success in create user!");
                    MainApplication.getInstance().setToken(token);
                    MainApplication.getInstance().setUser(user);
                    loadMainActivity();
                }
                getProgress().dismiss();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    LoginService service = Inject.provideLoginService();
                    Log.d(TAG, MessageFormat.format(
                            "doInBackground: create to user with username: {0} and mail {1}.", username, mail));

                    User userBuild = User.builder()
                            .username(username)
                            .mail(mail)
                            .profile(ProfileAppConstant.APP)
                            .build();

                    user = service.create(userBuild);
                    if (user != null && user.getVersion() == DEFAULT_VERSION) {
                        token = service.login(user.getUsername());
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
