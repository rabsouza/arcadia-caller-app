package br.com.battista.arcadiacaller.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.battista.arcadiacaller.model.Campaign;


public class ScreenShareService {
    private static final String TAG = "ScreenShareService";
    private static final String IMAGES_PATH_CACHE = "images";
    private final Context context;

    public ScreenShareService(Context context) {
        this.context = context;
    }

    public void shareScrenshot(@NonNull Activity activity, @NonNull Campaign campaign) {
        String nameFile = "/ac-campaing-" + campaign.getKey() + ".png";
        Log.i(TAG, "shareScrenshot: nameFile: " + nameFile);

        Bitmap bitmap = takeScreenshot(activity);
            Log.i(TAG, "shareScrenshot: success!");
            shareImage(bitmap);
    }

    private Bitmap takeScreenshot(Activity activity) {
        Log.i(TAG, "takeScreenshot");
        View rootView = activity.getWindow().getDecorView().getRootView();
        rootView.setDrawingCacheEnabled(true);

        rootView.setDrawingCacheEnabled(true);
        rootView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap screenshot = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);

        return screenshot;
    }

    private boolean saveImageToDisk(Bitmap screenshot, String nameFile) {
        Log.i(TAG, "saveImageToDisk");
        try {
            File cachePath = new File(context.getCacheDir(), IMAGES_PATH_CACHE);
            cachePath.mkdirs();
            FileOutputStream stream = new FileOutputStream(cachePath.getPath() + nameFile);
            screenshot.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void shareImage(Bitmap bitmap){
        String pathofBmp = MediaStore.Images.Media.insertImage(context.getContentResolver(),
                bitmap,"title", "description");
        Uri bmpUri = Uri.parse(pathofBmp);

        final Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
        shareIntent.setType("image/png");

        context.startActivity(Intent.createChooser(shareIntent, "Choose an app"));
        Log.i(TAG, "shareImage: startActivity");
    }

    private void shareImage(String nameFile) {
        Log.i(TAG, "shareImage: ");
        File imagePath = new File(context.getCacheDir(), IMAGES_PATH_CACHE);
        File newFile = new File(imagePath, nameFile);
        Uri contentUri = FileProvider.getUriForFile(context, context.getPackageName(), newFile);

        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.setDataAndType(contentUri, context.getContentResolver().getType
                    (contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            context.startActivity(Intent.createChooser(shareIntent, "Choose an app"));
            Log.i(TAG, "shareImage: startActivity");
        }
    }

}
