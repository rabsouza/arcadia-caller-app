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
        if (mTxtUsername == null || Strings.isNullOrEmpty(mTxtUsername.getText().toString())) {
            final int msg_username_required = R.string.msg_username_required;
            Log.w(TAG, getContext().getString(msg_username_required));
            AndroidUtils.toast(getContext(), msg_username_required);
            return;
        }

        mTxtMail = (EditText) findViewById(R.id.txt_mail);
        if (mTxtMail == null || Strings.isNullOrEmpty(mTxtMail.getText().toString())) {
            final int msg_mail_required = R.string.msg_mail_required;
            Log.w(TAG, getContext().getString(msg_mail_required));
            AndroidUtils.toast(getContext(), msg_mail_required);
            return;
        }
        String username = mTxtUsername.getText().toString();
        String mail = mTxtMail.getText().toString();
        Log.d(TAG, MessageFormat.format("Create new user with username: {0} and mail :{1}", username, mail));

        AndroidUtils.snackbar(view, R.string.alert_success_sign_in);
    }


    public void cancel(View view) {
        Log.i(TAG, "Start activity: LoginActivity!");

        Bundle args = new Bundle();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }
}
