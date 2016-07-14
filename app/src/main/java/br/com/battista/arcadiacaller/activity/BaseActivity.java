package br.com.battista.arcadiacaller.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    protected Context getContext() {
        return this;
    }

    protected Activity getActivity() {
        return this;
    }

}
