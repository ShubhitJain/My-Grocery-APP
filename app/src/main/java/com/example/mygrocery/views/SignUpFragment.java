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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {
    TextView alreadyHaveAccount;
    EditText email;
    private String emailPattern = "[a-zA-Z0_9._-]+@[a-z]+.[a-z]+";
    /* access modifiers changed from: private */
    public FirebaseAuth firebaseAuth;
    /* access modifiers changed from: private */
    public FirebaseFirestore firebaseFirestore;
    FrameLayout parentFramelayout;
    EditText pass;
    ProgressBar progressBar;
    /* access modifiers changed from: private */
    public Button signUp;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0181R.C0185layout.fragment_sign_up, container, false);
        this.alreadyHaveAccount = (TextView) view.findViewById(C0181R.C0184id.textView);
        this.email = (EditText) view.findViewById(C0181R.C0184id.signupEmail);
        this.pass = (EditText) view.findViewById(C0181R.C0184id.signupPass);
        this.progressBar = (ProgressBar) view.findViewById(C0181R.C0184id.SignUpProgressBar);
        this.signUp = (Button) view.findViewById(C0181R.C0184id.signup);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firebaseFirestore = FirebaseFirestore.getInstance();
        this.parentFramelayout = (FrameLayout) getActivity().findViewById(C0181R.C0184id.register_frame_layout);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SignUpFragment.this.setFragmet(new SignInFragment());
            }
        });
        this.email.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SignUpFragment.this.checkInputs();
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.pass.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SignUpFragment.this.checkInputs();
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SignUpFragment.this.checkEmailPassword();
            }
        });
    }

    public void setFragmet(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(C0181R.anim.slide_from_left, C0181R.anim.slideout_from_right);
        fragmentTransaction.replace(this.parentFramelayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    public void checkInputs() {
        if (TextUtils.isEmpty(this.email.getText())) {
            this.signUp.setEnabled(false);
            this.signUp.setTextColor(Color.argb(50, 0, 0, 0));
        } else if (TextUtils.isEmpty(this.pass.getText()) || this.pass.length() < 8) {
            this.signUp.setEnabled(false);
            this.signUp.setTextColor(Color.argb(50, 255, 0, 0));
        } else {
            this.signUp.setEnabled(true);
            this.signUp.setTextColor(Color.rgb(0, 0, 0));
        }
    }

    public void checkEmailPassword() {
        if (Patterns.EMAIL_ADDRESS.matcher(this.email.getText().toString()).matches()) {
            this.progressBar.setVisibility(0);
            this.signUp.setEnabled(false);
            this.signUp.setTextColor(Color.argb(50, 0, 0, 0));
            this.firebaseAuth.createUserWithEmailAndPassword(this.email.getText().toString(), this.pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Map<String, Object> userData = new HashMap<>();
                        userData.put("Email Id", SignUpFragment.this.email.getText().toString());
                        SignUpFragment.this.firebaseFirestore.collection("USERS").document(SignUpFragment.this.firebaseAuth.getUid()).set(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Map<String, Object> listSize = new HashMap<>();
                                    listSize.put("list_size", 0L);
                                    SignUpFragment.this.firebaseFirestore.collection("USERS").document(SignUpFragment.this.firebaseAuth.getUid()).collection("USER_DATA").document("MY_WISHLIST").set(listSize).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        public void onComplete(Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                SignUpFragment.this.startActivity(new Intent(SignUpFragment.this.getActivity(), MainActivity.class));
                                                SignUpFragment.this.getActivity().finish();
                                                return;
                                            }
                                            SignUpFragment.this.progressBar.setVisibility(4);
                                            Toast.makeText(SignUpFragment.this.getActivity(), task.getException().getMessage(), 0).show();
                                            SignUpFragment.this.signUp.setEnabled(true);
                                            SignUpFragment.this.signUp.setTextColor(Color.rgb(0, 0, 0));
                                        }
                                    });
                                    return;
                                }
                                Toast.makeText(SignUpFragment.this.getActivity(), task.getException().getMessage(), 0).show();
                            }
                        });
                        return;
                    }
                    SignUpFragment.this.progressBar.setVisibility(4);
                    Toast.makeText(SignUpFragment.this.getActivity(), task.getException().getMessage(), 0).show();
                    SignUpFragment.this.signUp.setEnabled(true);
                    SignUpFragment.this.signUp.setTextColor(Color.rgb(0, 0, 0));
                }
            });
            return;
        }
        this.email.setError("Enter a valid Email");
        this.email.requestFocus();
    }
}
