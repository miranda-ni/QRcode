package com.example.qrcode.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {
    private static SharedPreferences sharedPreferences;
    private final static String APP_PREF_NAME = "kg.geektech.lesson1";
    private final static String USER_NAME = "username";

    public static void init(Context context){
        sharedPreferences = context.getSharedPreferences(APP_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void saveUserName(String userName) {
        sharedPreferences.edit().putString(USER_NAME, userName).apply();
    }

    public static String getUserName() {
        return sharedPreferences.getString(USER_NAME, "");
    }


}
