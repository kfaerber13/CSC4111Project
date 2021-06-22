package com.example.reptilestore;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.LoginFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class Login_Form extends AppCompatActivity {
    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    Button buttonLogin;
    TextView textViewSignUp;
    ProgressBar progressBar;
    EditText edit_email,edit_password;
    TextView tv_registerlink;

     String username = "";
     String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        edit_email = (EditText)findViewById(R.id.Email);
        edit_password = (EditText)findViewById(R.id.Password);
        buttonLogin = (Button)findViewById(R.id.Login_From);
        tv_registerlink = (TextView)findViewById(R.id.Register);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                username = edit_email.getText().toString();
                password = edit_password.getText().toString();
                if(username.equals("")){
                    edit_email.setError("email is missing");
                    return;
                }
                if(password.equals("")){
                    edit_password.setError("password is missing");
                    return;
                }
                Intent intent = new Intent(Login_Form.this,MainActivity.class);
                startActivity(intent);



            }

        });
        tv_registerlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Form.this,Signup_Form.class);
                startActivity(intent);
            }
        });


    }
}