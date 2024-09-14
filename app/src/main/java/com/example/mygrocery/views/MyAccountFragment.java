package com.example.mygrocery.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.mygrocery.C0181R;

public class MyAccountFragment extends Fragment {
    public static final int MANAGE_ADDRESS = 1;
    private Button ViewAllAddressBtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0181R.C0185layout.fragment_my_account, container, false);
        Button button = (Button) view.findViewById(C0181R.C0184id.view_all_address_btn);
        this.ViewAllAddressBtn = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent addAdressIntent = new Intent(MyAccountFragment.this.getContext(), MyAddresessActivity.class);
                addAdressIntent.putExtra("MODE", 1);
                MyAccountFragment.this.startActivity(addAdressIntent);
            }
        });
        return view;
    }
}
