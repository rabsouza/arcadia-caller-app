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
        if (mTxtUsername == null || Strings.isNullOrEmpty(mTxtUsername.getText().toString())) {
            Log.w(TAG, getContext().getString(R.string.msg_username_required));
            AndroidUtils.toast(getContext(), R.string.msg_username_required);
            return;
        }
        String username = mTxtUsername.getText().toString();
        Log.d(TAG, MessageFormat.format("Login user with username: {0}", username));

        AndroidUtils.snackbar(view, R.string.alert_success_login);
    }

    public void signIn(View view) {
        Log.i(TAG, "Start activity: SignInActivity!");

        Bundle args = new Bundle();
        Intent intent = new Intent(this, SignInActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

}
