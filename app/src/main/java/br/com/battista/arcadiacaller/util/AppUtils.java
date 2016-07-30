package br.com.battista.arcadiacaller.util;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.common.base.Strings;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.activity.LoginActivity;
import br.com.battista.arcadiacaller.exception.ArcadiaCallerException;

public class AppUtils {

    private static final String TAG = AndroidUtils.class.getSimpleName();

    private AppUtils() {
    }

    public static void goToHomeIfApplicationIsNull(Context context) {
        MainApplication instance = MainApplication.instance();
        if (instance == null) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
    }

    public static void goToHomeIfUserIsNull(Application application, Context context) {
        MainApplication instance = MainApplication.instance();
        if (instance == null || instance.getUser() == null || Strings.isNullOrEmpty(instance.getToken())) {
            Log.e(TAG, "goToHomeIfUserIsNull: Bad context application! Go to login!");
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
    }

    private static Boolean checkOnlineServer(Application application) {
        if (application == null) {
            throw new ArcadiaCallerException("Application can not be null!");
        }

        return AndroidUtils.isOnline(application) && Inject.provideAppService().checkPing();
    }

}
