package com.happy.Reptile_Store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.happy.Reptile_Store.databinding.ActivityLoginBinding;
import com.happy.Reptile_Store.models.User;

import es.dmoral.toasty.Toasty;

public class Login extends AppCompatActivity {

    FirebaseAuth mAuth;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginBtn.setOnClickListener(v -> {

            // login
            String mail = binding.usernameLogin.getText().toString();
            String password = binding.passwordLogin.getText().toString();

            if (mail.isEmpty() || password.isEmpty()) {

                Toasty.error(getApplicationContext(), "Fill The Form Properly !!").show();
            } else {
                loginUser(mail, password);
            }


        });
    }

    public void signup(View view) {
        Intent intent = new Intent(Login.this, Signup.class);
        startActivity(intent);

    }

    private void loginUser(String mail, String password) {
        binding.progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //binding.progressBar.setVisibility(View.GONE);
                            FirebaseUser user = mAuth.getCurrentUser();
                            searchForUserData(user.getUid());

                        } else {
                            // If sign in fails, display a message to the user.
                            binding.progressBar.setVisibility(View.GONE);
                            Toasty.error(getApplicationContext(), "User Not Found", Toasty.LENGTH_LONG).show();

                        }
                    }
                });

    }

    private void searchForUserData(String uid) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user").child(uid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                binding.progressBar.setVisibility(View.GONE);

                if (snapshot.exists()) {

                    User user = snapshot.getValue(User.class);

                    if (user.getType().toLowerCase().contains("admin")) {
                          gotoMainPage();

                    } else {
                        mAuth.signOut();
                        Toasty.error(getApplicationContext(), "User is not an Admin", Toasty.LENGTH_LONG).show();

                    }

                } else {
                    mAuth.signOut();
                    Toasty.error(getApplicationContext(), "User Not Found", Toasty.LENGTH_LONG).show();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBar.setVisibility(View.GONE);
                Toasty.error(getApplicationContext(), "Error " + error.getMessage(), Toasty.LENGTH_LONG).show();

            }
        });

    }

    private void gotoMainPage() {
        Intent p = new Intent(getApplicationContext(), Dashboard.class);
        // add flag
        startActivity(p);
        finish();
    }

    public void forgetpassword(View view){
        startActivity(new Intent(Login.this, forgetpassword.class));
    }
}