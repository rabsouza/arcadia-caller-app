package br.com.battista.arcadiacaller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.CustomEvent;

import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.util.AppUtils;

import static br.com.battista.arcadiacaller.constants.CrashlyticsConstant.KEY_FRAGMENT;
import static br.com.battista.arcadiacaller.constants.CrashlyticsConstant.KEY_OPEN_FRAGMENT;

public class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String nameView = this.getClass().getSimpleName();
        Answers.getInstance().logCustom(new CustomEvent(KEY_OPEN_FRAGMENT)
                .putCustomAttribute(KEY_FRAGMENT, nameView));

        Answers.getInstance().logContentView(new ContentViewEvent()
                .putContentName(nameView)
                .putContentType(KEY_FRAGMENT));


        AppUtils.goToHomeIfUserIsNull(MainApplication.instance(), getContext());
    }

    @Override
    public void onStart() {
        super.onStart();

        AppUtils.goToHomeIfUserIsNull(MainApplication.instance(), getContext());
    }

    protected void replaceDetailFragment(Fragment fragment, int containerResID) {
        if (fragment != null) {
            Log.d(TAG, "replaceFragment: Change to detail fragment!");
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(containerResID, fragment).commit();
        }
    }
}
