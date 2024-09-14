package com.example.mygrocery.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mygrocery.C0181R;
import com.example.mygrocery.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInFragment extends Fragment {
    public static boolean disableCloseButton = false;
    private TextView dontHaveAccount;
    EditText email;
    FirebaseAuth firebaseAuth;
    private TextView forgot_pass;
    private FrameLayout parentFrameLayout;
    EditText pass;
    ProgressBar progressBar;
    /* access modifiers changed from: private */
    public Button signIn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0181R.C0185layout.fragment_sign_in, container, false);
        this.dontHaveAccount = (TextView) view.findViewById(C0181R.C0184id.textViewsignin);
        this.email = (EditText) view.findViewById(C0181R.C0184id.signinEmail);
        this.pass = (EditText) view.findViewById(C0181R.C0184id.signinPass);
        this.progressBar = (ProgressBar) view.findViewById(C0181R.C0184id.SignInprogressBar);
        this.signIn = (Button) view.findViewById(C0181R.C0184id.signin);
        this.parentFrameLayout = (FrameLayout) getActivity().findViewById(C0181R.C0184id.register_frame_layout);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.forgot_pass = (TextView) view.findViewById(C0181R.C0184id.forgotpass);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.dontHaveAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SignInFragment.this.setFragment(new SignUpFragment());
            }
        });
        this.forgot_pass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RegisterActivity.onResetPasswordFragment = true;
                SignInFragment.this.setFragment(new ForgotPasswordFragment());
            }
        });
        this.email.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SignInFragment.this.checkInput();
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.pass.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SignInFragment.this.checkInput();
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SignInFragment.this.checkEmailAndPasswordd();
            }
        });
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(C0181R.anim.slide_from_right, C0181R.anim.slideout_from_left);
        fragmentTransaction.replace(this.parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    public void checkInput() {
        if (TextUtils.isEmpty(this.email.getText())) {
            return;
        }
        if (TextUtils.isEmpty(this.pass.getText()) || this.pass.length() < 8) {
            this.signIn.setEnabled(false);
            this.signIn.setTextColor(Color.argb(50, 255, 0, 0));
            return;
        }
        this.signIn.setEnabled(true);
        this.signIn.setTextColor(Color.rgb(0, 0, 0));
    }

    public void checkEmailAndPasswordd() {
        if (!Patterns.EMAIL_ADDRESS.matcher(this.email.getText().toString()).matches()) {
            Toast.makeText(getActivity(), "Incorrect Email or password", 0).show();
        } else if (this.pass.length() >= 8) {
            this.progressBar.setVisibility(0);
            this.signIn.setEnabled(false);
            this.signIn.setTextColor(Color.argb(50, 0, 0, 0));
            this.firebaseAuth.signInWithEmailAndPassword(this.email.getText().toString(), this.pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        SignInFragment.this.startActivity(new Intent(SignInFragment.this.getActivity(), MainActivity.class));
                        SignInFragment.this.getActivity().finish();
                        return;
                    }
                    SignInFragment.this.progressBar.setVisibility(4);
                    SignInFragment.this.signIn.setEnabled(true);
                    SignInFragment.this.signIn.setTextColor(Color.rgb(0, 0, 0));
                    Toast.makeText(SignInFragment.this.getActivity(), task.getException().getMessage(), 0).show();
                }
            });
        } else {
            Toast.makeText(getActivity(), "Incorrect Email or password", 0).show();
        }
    }
}
