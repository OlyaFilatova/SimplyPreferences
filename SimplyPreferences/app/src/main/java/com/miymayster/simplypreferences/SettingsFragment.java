package com.miymayster.simplypreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

/**
 * Created by Olga on 07.07.2017.
 */
// TODO (12): Create class SettingsFragment
    // TODO (13): extend from PreferenceFragmentCompat class
// TODO (61): implement SharedPreferences.OnSharedPreferenceChangeListener
public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    // TODO (14): implement onCreatePreferences method
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // TODO (22): add preferences file that we created
        addPreferencesFromResource(R.xml.pref_settings);
        // TODO (48): Get the preference screen
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        // TODO (49): Get instance of shared preferences
        SharedPreferences sharedPreferences = preferenceScreen.getSharedPreferences();
        // TODO (50): get the number of preferences
        int count = preferenceScreen.getPreferenceCount();
        // TODO (51): iterate through all of the preferences
        for(int i = 0; i < count; i ++){
            // TODO (52):
            Preference preference = preferenceScreen.getPreference(i);
            // TODO (53): if it is not a checkbox preference
            if(!(preference instanceof CheckBoxPreference)){
                // TODO (54): get value of the preference
                String value = sharedPreferences.getString(preference.getKey(), "");
                // TODO (55): call the setSummary method
                setSummary(preference, value);
            }
        }
    }


    private void setSummary(Preference preference, String value){
        // TODO (56): check that preference is an instance of ListPreference class
        if(preference instanceof ListPreference){
            // TODO (57): cast preference to ListPreference
            ListPreference listPreference = (ListPreference) preference;
            // TODO (58): get index of the value
            int valueIndex = listPreference.findIndexOfValue(value);
            // TODO (59): check that index isn't equal to -1
            if(valueIndex >= 0){
                // TODO (60): set summary equal to entry with index of the value
                listPreference.setSummary(listPreference.getEntries()[valueIndex]);
            }
        }
        // TODO (79) check that preference is an instance of EditTextPreference class
        else if(preference instanceof EditTextPreference){
            // TODO (80) cast preference to EditTextPreference
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            // TODO (81) set summary equal to value
            editTextPreference.setSummary(value);
        }
    }

    // TODO (62) implement onSharedPreferenceChanged
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // TODO (63) get instance of the preference that was changed
        Preference preference = findPreference(key);
        // TODO (64) check the preference was found
        if(null != preference){
            // TODO (65) check that this preference isn't instance of the CheckBoxPreference
            if(!(preference instanceof CheckBoxPreference)){
                // TODO (66) get value of the preference
                String value = sharedPreferences.getString(key, "");
                // TODO (67) call the setSummary method
                setSummary(preference, value);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO (68) bind listener to shared preferences
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
    @Override
    public void onDestroy() {
        // TODO (69) unbind listener from shared preferences
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }
}
