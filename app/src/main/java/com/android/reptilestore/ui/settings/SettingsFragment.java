package com.android.reptilestore.ui.settings;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.android.reptilestore.Constants;
import com.android.reptilestore.SettingsActivity;
import com.example.reptilestore.R;

public  class SettingsFragment extends PreferenceFragmentCompat {
    private Preference removeAccount;
    private Preference account_detail;
    private AlertDialog confirmRemoveAccountDialog;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        Preference change_securiyinfo = getPreferenceManager().findPreference(Constants.SECURITY_INFO);
        change_securiyinfo.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsActivity)getActivity()).goSecurityInfoFragment();
                return false;
            }
        });

        removeAccount = getPreferenceManager().findPreference(Constants.REMOVE_ACCOUNT);

        removeAccount.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                showConfirmRemoveAccount();
                return false;
            }
        });

        account_detail = (Preference) findPreference(Constants.ACCOUNT_DETAIL);
        account_detail.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent();
                intent.setClassName(getContext(), "com.android.reptilestore.ui.settings.AccountActivity");
                startActivity(intent);
                return true;
            }
        });

        SharedPreferences sh = getActivity().getSharedPreferences(Constants.REPTILE_SETTING_PREF, getContext().MODE_PRIVATE);
        Boolean removed_account = sh.getBoolean(Constants.REMOVE_ACCOUNT, true);
        if(removed_account){
            removeAccount.setVisible(false);
            account_detail.setTitle(R.string.create_account);

        }else{
            removeAccount.setVisible(true);
            account_detail.setTitle(R.string.signature_title);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        // Fetching the stored data
        // from the SharedPreference
        SharedPreferences sh = getActivity().getSharedPreferences(Constants.REPTILE_SETTING_PREF, getContext().MODE_PRIVATE);
        Boolean removed_account = sh.getBoolean(Constants.REMOVE_ACCOUNT, true);
        if(removed_account){
            removeAccount.setVisible(false);
            account_detail.setTitle(R.string.create_account);

        }else{
            removeAccount.setVisible(true);
            account_detail.setTitle(R.string.signature_title);
        }
    }

    private void showConfirmRemoveAccount(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.remove_account)
                .setMessage(R.string.confirm_remove_account)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.ok, null);
        confirmRemoveAccountDialog = builder.create();
        confirmRemoveAccountDialog.show();
        confirmRemoveAccountDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAccount();
            }
        });
    }
    private void removeAccount(){
        SharedPreferences sh = getActivity().getSharedPreferences(Constants.REPTILE_SETTING_PREF, getContext().MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh.edit();
        myEdit.putBoolean(Constants.REMOVE_ACCOUNT,true);
        myEdit.apply();
        confirmRemoveAccountDialog.dismiss();
        onResume();
    }
}