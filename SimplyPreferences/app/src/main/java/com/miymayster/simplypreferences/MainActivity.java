package com.miymayster.simplypreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.prefs.PreferenceChangeListener;

// TODO (30) implement SharedPreferences.OnSharedPreferenceChangeListener
public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    TextView firstTextView;
    TextView secondTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO (27) Saving reference to our dummy TextView
        firstTextView = (TextView) findViewById(R.id.first_tv);
        // TODO (44) Saving reference to our dummy TextView
        secondTextView = (TextView) findViewById(R.id.second_tv);

        setupSharedPreferences();
    }
    private void setupSharedPreferences(){
        // TODO (25) Get a reference to the default shared preferences from the PreferenceManager class
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // TODO (28) update first TextView accordingly
        updateFirstTextView(sharedPreferences);
        // TODO (45) update second TextView accordingly
        updateSecondTextView(sharedPreferences);

        // TODO (34) register change listener for shared preferences
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }
    private void updateFirstTextView(SharedPreferences sharedPreferences) {
        // TODO (26) Save value of first preference into variable
        boolean firstPreferenceValue = sharedPreferences.getBoolean(
                getString(R.string.first_preference_key),
                getResources().getBoolean(R.bool.first_preference_default_value)
        );
        // TODO (29) If first preference is checked show firstTextView, otherwise hide it
        if (firstPreferenceValue) {
            firstTextView.setVisibility(View.VISIBLE);
        } else {
            firstTextView.setVisibility(View.GONE);
        }
    }
    private void updateSecondTextView(SharedPreferences sharedPreferences){
        // TODO (46) Save value of second preference into variable
        String secondPreferenceValue = sharedPreferences.getString(getString(R.string.second_preference_key), getString(R.string.second_preference_default_value));
        secondTextView.setText(getString(R.string.second_preference_label) + " - " + secondPreferenceValue);
    }

    // TODO (7): Bind /menu/menu_item.xml to MainActivity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO (8): Open SettingsActivity on clicking action_settings menu item
        if(item.getItemId() == R.id.action_settings){
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // TODO (31) implement onSharedPreferenceChanged
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // TODO (32) check which preference was changed
        if(key.equals(getString(R.string.first_preference_key))){
            // TODO (33) update view accordingly
            updateFirstTextView(sharedPreferences);
        }else if(key.equals(getString(R.string.second_preference_key))){
            // TODO (47) update view accordingly
            updateSecondTextView(sharedPreferences);
        }
    }

    @Override
    protected void onDestroy() {
        // TODO (34) unregister change listener from shared preferences when activity is destroyed
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }
}
