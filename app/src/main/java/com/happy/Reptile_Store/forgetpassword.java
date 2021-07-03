package com.happy.Reptile_Store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatActivity {

    Button reg_btn;
    TextInputEditText reg_email;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        reg_btn = findViewById(R.id.reg_btn);

        reg_email = findViewById(R.id.reg_email);

        dialog = new ProgressDialog(forgetpassword.this);
        dialog.setMessage("Registering Account... ");
        dialog.setCancelable(false);


    }

    public void back(View view){
        startActivity(new Intent(forgetpassword.this, Login.class));
    }

    public void loginUser(View view) {
        if (reg_email.getText().toString().equals("")){
            Toast.makeText(com.happy.Reptile_Store.forgetpassword.this, "Pease write email", Toast.LENGTH_SHORT).show();
            dialog.show();
        }
        else {
            String email=reg_email.getText().toString();
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                 startActivity(new Intent(forgetpassword.this, Login.class));
                                dialog.setMessage("Creating Account...");
                                Toast.makeText(forgetpassword.this, "Reset Password is send at your email", Toast.LENGTH_SHORT).show();
                                Log.d("TAG", "Email sent.");
                            }
                        }
                    });
        }
    }




}