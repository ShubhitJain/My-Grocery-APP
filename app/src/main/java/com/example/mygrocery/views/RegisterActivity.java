package com.example.mygrocery.views;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mygrocery.C0181R;

public class RegisterActivity extends AppCompatActivity {
    public static boolean onResetPasswordFragment = false;
    public static boolean setSignUpFragment = false;
    FrameLayout frameLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0181R.C0185layout.activity_register);
        this.frameLayout = (FrameLayout) findViewById(C0181R.C0184id.register_frame_layout);
        if (setSignUpFragment) {
            setSignUpFragment = false;
            setFragment(new SignUpFragment());
            return;
        }
        setFragment(new SignInFragment());
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || !onResetPasswordFragment) {
            return super.onKeyDown(keyCode, event);
        }
        onResetPasswordFragment = false;
        setFragmet1(new SignInFragment());
        return false;
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(this.frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    public void setFragmet1(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(C0181R.anim.slide_from_left, C0181R.anim.slideout_from_right);
        fragmentTransaction.replace(this.frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}
