package com.happy.Reptile_Store;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.happy.Reptile_Store.databinding.ActivitySignupBinding;
import com.happy.Reptile_Store.models.User;

import es.dmoral.toasty.Toasty;

public class Signup extends AppCompatActivity {
    ProgressDialog dialog;
    FirebaseAuth mauth;
    private ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mauth = FirebaseAuth.getInstance();

        binding.regBtn.setOnClickListener(v -> {

            String mail, password, firstname, lastname, ph;

            mail = binding.regEmail.getText().toString();
            password = binding.regPassword.getText().toString();
            firstname = binding.regFirstname.getText().toString();
            lastname = binding.regLastname.getText().toString();
            ph = binding.regPhoneNo.getText().toString();

            if (mail.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || ph.isEmpty()) {
                Toasty.error(getApplicationContext(), "Fill The Form Properly !!").show();

            } else {
                // go with sign up
                User user = new User("",firstname, lastname, ph, mail, "admin" , "" , "admin address" );

                createAUser(user, password);


            }


        });

        dialog = new ProgressDialog(Signup.this);
        dialog.setMessage("Registering Account... ");
        dialog.setCancelable(false);
    }



    private void createAUser(User user, String password) {
        dialog.show();

        mauth.createUserWithEmailAndPassword(user.getMail(), password).addOnSuccessListener(authResult -> {
            // user created
            //now same on the daatabase
            dialog.setMessage("Creating Account...");
            saveOnDataBase(user);

        }).addOnFailureListener(e -> {
            Toasty.error(getApplicationContext(), "Could Not Register The User Error " + e.getMessage(), Toasty.LENGTH_LONG).show();
        });
    }

    private void saveOnDataBase(User user) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("user");
        FirebaseUser users = mauth.getCurrentUser();
        user.setId(users.getUid());

        database.child(users.getUid()).setValue(user)
                .addOnSuccessListener(aVoid -> {
                    dialog.dismiss();
                    Toasty.success(getApplicationContext(), "User Created !!!", Toasty.LENGTH_SHORT).show();
                    gotoMainPage();

                })
                .addOnFailureListener(e -> {
                    dialog.dismiss();
                    saveOnDataBase(user);
                });

    }

    private void gotoMainPage() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        Intent intent =new Intent(Signup.this,Login.class);
        startActivity(intent);
        finish();
    }



    public void back(View view) {
        Intent intent = new Intent(Signup.this, Login.class);
        startActivity(intent);
    }
}