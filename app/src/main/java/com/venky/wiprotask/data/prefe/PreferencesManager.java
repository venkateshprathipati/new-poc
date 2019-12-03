package com.venky.wiprotask.data.prefe;

import android.content.Context;
import android.content.SharedPreferences;

import com.venky.wiprotask.di.AppContext;
import com.venky.wiprotask.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Venkatesh on 03,December,2019
 */
@Singleton
public class PreferencesManager implements PreferenceHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_LOGGED_IN_MODE";
    private static final String PREF_KEY_USER_CODE = "PREF_KEY_USER_CODE";
    private static final String PREF_KEY_USER_MOBILE = "PREF_KEY_CURRENT_MOBILE";
    private static final String PREF_KEY_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_FIRST_TIME = "PREF_KEY_FIRST_TIME";
    private static final String PREF_KEY_USER_PROFILE_PIC_URL = "PREF_KEY_USER_PROFILE_PIC_URL";
    private static final String PREF_KEY_COACH_MARK = "PREF_KEY_COACH_MARK";
    private static final String PREF_KEY_DB_NAME = "PREF_KEY_DB_NAME";

    private final SharedPreferences mPrefs;



    @Inject
    public PreferencesManager(@AppContext Context context,
                              @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
       // mAppContext = context;
    }

}
