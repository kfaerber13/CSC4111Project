package com.happy.Reptile_Store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash_activity extends AppCompatActivity {

    Button nextbt;
    FirebaseAuth mauth  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mauth = FirebaseAuth.getInstance() ;



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUser() ;

            }
        } , 400) ;

    }

    private void checkUser() {
        FirebaseUser user = mauth.getCurrentUser();
        Intent intent;
        if(user != null){
            intent = new Intent(Splash_activity.this, Dashboard.class);
          //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK) ;

        }else {
            intent = new Intent(Splash_activity.this, Login.class);
        }
        startActivity(intent);
        finish();
    }

}