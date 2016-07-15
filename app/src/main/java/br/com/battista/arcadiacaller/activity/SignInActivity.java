package br.com.battista.arcadiacaller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.common.base.Strings;

import java.text.MessageFormat;

import br.com.battista.arcadiacaller.R;
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
        String username = mTxtUsername.getText().toString();

        mTxtMail = (EditText) findViewById(R.id.txt_mail);
        if (Strings.isNullOrEmpty(mTxtMail.getText().toString())) {
            String msgErrorMail = getContext().getString(R.string.msg_mail_required);
            AndroidUtils.changeErrorEditText(mTxtMail, msgErrorMail, true);
            return;
        }
        AndroidUtils.changeErrorEditText(mTxtMail);
        String mail = mTxtMail.getText().toString();

        Log.d(TAG, MessageFormat.format("Create new user with username: {0} and mail :{1}", username, mail));

        final View currentView = view;
        new ProgressApp(this, R.string.msg_action_login, false) {
            @Override
            protected void onPostExecute(Boolean result) {
                AndroidUtils.snackbar(currentView, R.string.alert_success_sign_in);
                loadMainActivity();
                getProgress().dismiss();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Thread.sleep(5000L);
                } catch (Exception e) {
                    Log.e(TAG_CLASSNAME, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
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
