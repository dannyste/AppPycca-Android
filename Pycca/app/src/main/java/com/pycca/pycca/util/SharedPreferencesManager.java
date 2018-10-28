package com.pycca.pycca.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.pycca.pycca.pojo.User;

public class SharedPreferencesManager {

    private static SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static final String USER = "USER";

    private SharedPreferencesManager() {

    }

    public static SharedPreferencesManager getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return sharedPreferencesManager;
    }

    public void setUser(User user) {
        Gson gson = new Gson();
        editor.putString(USER, gson.toJson(user));
        editor.apply();
    }

    public User getUser() {
        Gson gson = new Gson();
        return gson.fromJson(sharedPreferences.getString(USER,""), User.class);
    }

}
