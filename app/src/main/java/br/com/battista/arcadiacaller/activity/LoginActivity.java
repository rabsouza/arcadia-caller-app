package br.com.battista.arcadiacaller.activity;

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
import br.com.battista.arcadiacaller.service.LoginService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

public class LoginActivity extends BaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText mTxtUsername = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        mTxtUsername = (EditText) findViewById(R.id.txt_username);
        if (Strings.isNullOrEmpty(mTxtUsername.getText().toString())) {
            String msgErrorUsername = getContext().getString(R.string.msg_username_required);
            AndroidUtils.changeErrorEditText(mTxtUsername, msgErrorUsername, true);
            return;
        }
        AndroidUtils.changeErrorEditText(mTxtUsername);

        final String username = mTxtUsername.getText().toString();
        Log.d(TAG, MessageFormat.format("Login user with username: {0}", username));

        final View currentView = view;
        new ProgressApp(this, R.string.msg_action_login, false) {

            private String token;

            @Override
            protected void onPostExecute(Boolean result) {
                if (Strings.isNullOrEmpty(token)) {
                    Log.d(TAG, "onPostExecute: Failed in login!");
                    AndroidUtils.snackbar(currentView, R.string.msg_failed_login_user);
                } else {
                    Log.d(TAG, "onPostExecute: Success in login!");
                    MainApplication.getInstance().setToken(token);
                    loadMainActivity();
                }
                getProgress().dismiss();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                LoginService service = Inject.provideLoginService();
                Log.d(TAG, MessageFormat.format("doInBackground: Login username: {}.", username));
                token = service.login(username);
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
