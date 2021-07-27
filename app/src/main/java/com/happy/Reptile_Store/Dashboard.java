package com.happy.Reptile_Store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



    }

    public void logout(View view){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        Intent intent =new Intent(Dashboard.this,Login.class);
        startActivity(intent);
        finish();
    }

    public void contact(View view){
        Intent intent =new Intent(Dashboard.this,contact_us.class);
        startActivity(intent);
    }
}