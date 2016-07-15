package com.example.ledebour.programmatically_change_language;

import android.app.Application;

import com.example.ledebour.programmatically_change_language.helper.LanguageHelper;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LanguageHelper.onCreate(this, "pt");
    }
}
