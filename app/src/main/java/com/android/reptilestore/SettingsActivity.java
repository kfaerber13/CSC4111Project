package com.android.reptilestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.android.reptilestore.ui.settings.SecurityInfoFragment;
import com.android.reptilestore.ui.settings.SettingsFragment;
import com.example.reptilestore.R;
import com.example.reptilestore.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public AppCompatActivity mActivity;
    private final static String TAG_SECURITY_FRAGMENT = "TAG_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        mActivity = this;
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

    }

    public void goSecurityInfoFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SecurityInfoFragment(),TAG_SECURITY_FRAGMENT).addToBackStack(null)
                .commit();
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            final SecurityInfoFragment security_info_fragment = (SecurityInfoFragment) getSupportFragmentManager().findFragmentByTag(TAG_SECURITY_FRAGMENT);
            if (security_info_fragment != null && getSupportFragmentManager().getBackStackEntryCount() == 1) {
                this.setTitle(R.string.title_activity_settings);
            }
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return true;
    }
}