package com.example.mygrocery.views;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mygrocery.C0181R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordFragment extends Fragment {
    EditText email;
    /* access modifiers changed from: private */
    public ViewGroup emailIconContainer;
    FirebaseAuth firebaseAuth;
    TextView goBack;
    /* access modifiers changed from: private */
    public ImageView icon;
    ProgressBar iconProgressBar;
    /* access modifiers changed from: private */
    public TextView iconText;
    FrameLayout parentFramelayout;
    Button resetpassBtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0181R.C0185layout.fragment_forgot_password, container, false);
        this.email = (EditText) view.findViewById(C0181R.C0184id.forgotpassemail);
        this.resetpassBtn = (Button) view.findViewById(C0181R.C0184id.forgotpassBtn);
        this.goBack = (TextView) view.findViewById(C0181R.C0184id.goback);
        this.parentFramelayout = (FrameLayout) getActivity().findViewById(C0181R.C0184id.register_frame_layout);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.icon = (ImageView) view.findViewById(C0181R.C0184id.forgotpassicon);
        this.emailIconContainer = (ViewGroup) view.findViewById(C0181R.C0184id.forgotpassIconContainer);
        this.iconText = (TextView) view.findViewById(C0181R.C0184id.forgotpassText);
        this.iconProgressBar = (ProgressBar) view.findViewById(C0181R.C0184id.forgotpassprogressBar);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.email.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ForgotPasswordFragment.this.checkInputs();
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.resetpassBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(ForgotPasswordFragment.this.emailIconContainer);
                ForgotPasswordFragment.this.iconText.setVisibility(8);
                TransitionManager.beginDelayedTransition(ForgotPasswordFragment.this.emailIconContainer);
                ForgotPasswordFragment.this.icon.setVisibility(0);
                ForgotPasswordFragment.this.iconProgressBar.setVisibility(0);
                ForgotPasswordFragment.this.resetpassBtn.setEnabled(false);
                ForgotPasswordFragment.this.resetpassBtn.setTextColor(Color.argb(50, 0, 0, 0));
                ForgotPasswordFragment.this.firebaseAuth.sendPasswordResetEmail(ForgotPasswordFragment.this.email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) {
                            ForgotPasswordFragment.this.icon.setImageResource(C0181R.C0183drawable.ic_baseline_mark_email_read_24);
                            ForgotPasswordFragment.this.iconText.setText("Check your Email!");
                            ForgotPasswordFragment.this.iconText.setTextColor(ForgotPasswordFragment.this.getResources().getColor(C0181R.C0182color.sucsessGreen));
                            TransitionManager.beginDelayedTransition(ForgotPasswordFragment.this.emailIconContainer);
                            ForgotPasswordFragment.this.iconText.setVisibility(0);
                        } else {
                            ForgotPasswordFragment.this.iconText.setText(task.getException().getMessage());
                            ForgotPasswordFragment.this.iconText.setTextColor(ForgotPasswordFragment.this.getResources().getColor(C0181R.C0182color.failred));
                            TransitionManager.beginDelayedTransition(ForgotPasswordFragment.this.emailIconContainer);
                            ForgotPasswordFragment.this.iconText.setVisibility(0);
                        }
                        ForgotPasswordFragment.this.iconProgressBar.setVisibility(8);
                        ForgotPasswordFragment.this.resetpassBtn.setEnabled(true);
                        ForgotPasswordFragment.this.resetpassBtn.setTextColor(Color.rgb(0, 0, 0));
                    }
                });
            }
        });
        this.goBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ForgotPasswordFragment.this.setFragmet(new SignInFragment());
            }
        });
    }

    public void checkInputs() {
        if (TextUtils.isEmpty(this.email.getText())) {
            this.resetpassBtn.setEnabled(false);
            this.resetpassBtn.setTextColor(Color.argb(50, 0, 0, 0));
            return;
        }
        this.resetpassBtn.setEnabled(true);
        this.resetpassBtn.setTextColor(Color.rgb(0, 0, 0));
    }

    public void setFragmet(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(C0181R.anim.slide_from_left, C0181R.anim.slideout_from_right);
        fragmentTransaction.replace(this.parentFramelayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}
