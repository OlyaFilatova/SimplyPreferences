package com.miymayster.simplypreferences;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by Olga on 07.07.2017.
 */
// TODO (12): Create class SettingsFragment
    // TODO (13): extend from PreferenceFragmentCompat class
public class SettingsFragment extends PreferenceFragmentCompat {

    // TODO (14): implement onCreatePreferences method
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // TODO (22): add preferences file that we created
        addPreferencesFromResource(R.xml.pref_settings);
    }
}
