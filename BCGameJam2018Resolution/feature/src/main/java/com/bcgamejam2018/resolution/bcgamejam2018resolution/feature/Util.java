package com.bcgamejam2018.resolution.bcgamejam2018resolution.feature;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Eric Kim on 2018-02-10.
 */

public class Util {
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void pushActivity(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

//    public static void playMusic(Class classs, String rawId) {
//        MediaPlayer mp  = MediaPlayer.create(classs,rawId);
//        mp.start();
//    }

    public static String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public static JSONObject parseJSONString(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch(Exception e) {
            Log.e("Util", e.getMessage());
            return null;
        }
    }
}
