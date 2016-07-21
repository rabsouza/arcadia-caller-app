package br.com.battista.arcadiacaller.fragment;

import static br.com.battista.arcadiacaller.constants.CrashlyticsConstant.KEY_FRAGMENT;
import static br.com.battista.arcadiacaller.constants.CrashlyticsConstant.KEY_OPEN_FRAGMENT;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.CustomEvent;

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String nameView = this.getClass().getSimpleName();
        Answers.getInstance().logCustom(new CustomEvent(KEY_OPEN_FRAGMENT)
                .putCustomAttribute(KEY_FRAGMENT, nameView));

        Answers.getInstance().logContentView(new ContentViewEvent()
                .putContentName(nameView)
                .putContentType(KEY_FRAGMENT));
    }
}
