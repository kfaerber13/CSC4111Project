package com.android.reptilestore.ui.settings;

import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.reptilestore.Constants;
import com.example.reptilestore.R;
import com.google.android.material.textfield.TextInputEditText;


public class AccountFragment extends Fragment {

    private AccountViewModel mViewModel;



    private TextInputEditText firstName = null;
    private TextInputEditText lastName = null;
    private EditText email = null;
    private EditText pass = null;
    private EditText passOld = null;
    private EditText passCon = null;
    private Button btnSave = null;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.account_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialize your view here for use view.findViewById("your view id")
        firstName = (TextInputEditText) view.findViewById(R.id.inputFirstName);
        lastName = (TextInputEditText) view.findViewById(R.id.inputLastName);
        email = (EditText) view.findViewById(R.id.editTextTextEmailAddress);
        pass = (EditText) view.findViewById(R.id.editTextTextPassword);
        passCon = (EditText) view.findViewById(R.id.editTextTextPasswordCon);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = getActivity().getSharedPreferences(Constants.REPTILE_SETTING_PREF, getContext().MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sh.edit();
                Boolean removed_account = sh.getBoolean(Constants.REMOVE_ACCOUNT, true);

                android.util.Log.v("reptile","pass="+pass+"|confirm="+passCon);
                if(removed_account){
                    if(TextUtils.isEmpty(pass.getText().toString()) || !TextUtils.equals(pass.getText().toString(), passCon.getText().toString())){
                        Toast.makeText(getContext(),"failed password.", Toast.LENGTH_LONG).show();
                        return;
                    }else{
                        myEdit.putString(Constants.PASS, pass.getText().toString());
                    }
                }
                Toast.makeText(getContext(),"Sussessfully created.", Toast.LENGTH_LONG).show();
                myEdit.putString(Constants.FIRST_NAME, firstName.getText().toString());
                myEdit.putString(Constants.LAST_NAME, lastName.getText().toString());

                myEdit.putString(Constants.EMAIL, email.getText().toString());
                myEdit.putBoolean(Constants.REMOVE_ACCOUNT, false);

                myEdit.commit();
                getActivity().finish();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

        // Fetching the stored data
        // from the SharedPreference
        SharedPreferences sh = getActivity().getSharedPreferences(Constants.REPTILE_SETTING_PREF, getContext().MODE_PRIVATE);

        Boolean removed_account = sh.getBoolean(Constants.REMOVE_ACCOUNT, true);
        if(!removed_account) {
            String preFirstName = sh.getString(Constants.FIRST_NAME, "");
            String preLastName = sh.getString(Constants.LAST_NAME, "");
            String prePass = sh.getString(Constants.PASS, "");
            String preEmail = sh.getString(Constants.EMAIL, "");

            pass.setVisibility(View.GONE);
            passCon.setVisibility(View.GONE);

            firstName.setText(preFirstName);
            lastName.setText(preLastName);
            pass.setText(prePass);
            email.setText(preEmail);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        // TODO: Use the ViewModel
    }

}
