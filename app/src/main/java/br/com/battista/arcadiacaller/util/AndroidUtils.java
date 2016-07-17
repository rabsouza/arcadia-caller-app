package br.com.battista.arcadiacaller.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.battista.arcadiacaller.exception.ArcadiaCallerException;

public class AndroidUtils {

    private static final String TAG = AndroidUtils.class.getSimpleName();

    public static String getVersionName(Activity activity) {
        if (activity == null) {
            throw new ArcadiaCallerException("Activity can not be null!");
        }

        PackageManager pm = activity.getPackageManager();
        String packageName = activity.getPackageName();
        String versionName;
        try {
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            versionName = "N/A";
        }

        Log.d(TAG, String.format("getVersionName: Version app: %s!", versionName));
        return versionName;
    }

    public static void changeErrorEditText(EditText editText) {
        changeErrorEditText(editText, null, false);
    }

    public static boolean isOnline(Application application) {
        if (application == null) {
            throw new ArcadiaCallerException("Application can not be null!");
        }

        ConnectivityManager cm =
                (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void changeErrorEditText(EditText editText, String msgErro, Boolean error) {
        if (editText == null) {
            throw new ArcadiaCallerException("EditText can not be null!");
        }

        if (error) {
            Log.e(TAG, msgErro);
            editText.setError(msgErro);
            editText.requestFocus();
        } else {
            editText.setError(null);
        }
    }

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, int msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void snackbar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void snackbar(View view, int msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }


}
