package com.android.reptilestore.ui.settings;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.android.reptilestore.Constants;
import com.example.reptilestore.R;

import com.example.reptilestore.databinding.FragmentSettingsBinding;
import com.google.android.material.textfield.TextInputLayout;

public class SecurityInfoFragment extends PreferenceFragmentCompat {
    private Preference resetPassword;

    private SwitchPreference hideFirstName;
    private SwitchPreference notLogin;
    private TextInputLayout password;
    private TextInputLayout newPassword;
    private TextInputLayout conPassword;
    private TextInputLayout inputUrl;
    private AlertDialog resetPasswordAlert;
    private SettingsViewModel settinsViewModel;
    private FragmentSettingsBinding binding;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.security_preferences, rootKey);
        getActivity().setTitle(R.string.title_securityinfo);

        hideFirstName = (SwitchPreference)getPreferenceManager().findPreference(Constants.HIDE_FIRST_NAME);
        notLogin = (SwitchPreference)getPreferenceManager().findPreference(Constants.NOT_LOGIN);

        resetPassword = getPreferenceManager().findPreference(Constants.RESET_PASSWORD);
        resetPassword.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                showResetPasswordAlert();
                return false;
            }
        });

        SharedPreferences sh = getActivity().getSharedPreferences(Constants.REPTILE_SETTING_PREF, getContext().MODE_PRIVATE);
        Boolean removed_account = sh.getBoolean(Constants.REMOVE_ACCOUNT, true);
        if(removed_account){
            resetPassword.setVisible(false);
        }else{
            resetPassword.setVisible(true);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sh = getActivity().getSharedPreferences(Constants.REPTILE_SETTING_PREF, getContext().MODE_PRIVATE);
        Boolean removed_account = sh.getBoolean(Constants.REMOVE_ACCOUNT, true);
        if(removed_account){
            resetPassword.setVisible(false);
        }else{
            resetPassword.setVisible(true);
        }
    }

    private void showResetPasswordAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View resetPasswordView = getLayoutInflater().inflate(R.layout.alert_reset_password, null);
//        val resetPasswordView = activity?.layoutInflater?.inflate(R.layout.alert_reset_password, null)
        password = (TextInputLayout)resetPasswordView.findViewById(R.id.password) ;
        newPassword = (TextInputLayout)resetPasswordView.findViewById(R.id.newpassword);
        conPassword = (TextInputLayout)resetPasswordView.findViewById(R.id.confirmpassword);
        builder.setView(resetPasswordView);
        builder.setTitle(R.string.change_password)
                .setCancelable(false)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.change, null);
        resetPasswordAlert = builder.create();
        resetPasswordAlert.show();
        resetPasswordAlert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        resetPasswordAlert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        resetPasswordAlert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//        setupPasswordWatcher();
        resetPasswordAlert.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword(password.getEditText().getText().toString(), newPassword.getEditText().getText().toString(), conPassword.getEditText().getText().toString());
            }
        });
    }

    private void resetPassword(String oPassword,String nPassword, String cPassword){
        // CHANGEIT START
        SharedPreferences sh = getActivity().getSharedPreferences(Constants.REPTILE_SETTING_PREF, getContext().MODE_PRIVATE);
        String old_password = sh.getString(Constants.PASS, "");
        if(!TextUtils.equals(old_password, oPassword)){
            onResetPasswordResponse("Incorrect Password!");
        }else if(TextUtils.isEmpty(nPassword)){
            onResetPasswordResponse("Enter Password!");
        }else if(!TextUtils.equals(nPassword, cPassword)){
            onResetPasswordResponse("Confirm Password!");
        }else{
            onResetPasswordResponse("success");
        }
        // CHANGEIT END
//
    }

    private void onResetPasswordResponse(String message) {
        if (!TextUtils.equals(message,"success")) {
            Toast.makeText(getActivity(), message , Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), getString(R.string.success_change_password) , Toast.LENGTH_LONG).show();
            resetPasswordAlert.dismiss();
        }
    }


}