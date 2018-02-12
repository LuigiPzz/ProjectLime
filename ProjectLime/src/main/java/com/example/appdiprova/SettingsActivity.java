package com.example.appdiprova;

/**
 * Created by luigi.pozzi on 12/02/2018.
 */

import android.os.Bundle;
        import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }}
