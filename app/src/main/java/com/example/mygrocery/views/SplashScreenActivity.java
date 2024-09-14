package com.example.mygrocery.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mygrocery.C0181R;
import com.example.mygrocery.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0181R.C0185layout.activity_splash_screen);
        this.firebaseAuth = FirebaseAuth.getInstance();
        SystemClock.sleep(3000);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.firebaseAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
            return;
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
