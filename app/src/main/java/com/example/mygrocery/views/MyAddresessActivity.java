package com.example.mygrocery.views;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.mygrocery.Adapters.AddressesAdapter;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.addressesModel;

import java.util.ArrayList;
import java.util.List;

public class MyAddresessActivity extends AppCompatActivity {
    private static AddressesAdapter adapter;
    RecyclerView addressesrecyclerView;
    private Button deliverHereBtn;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0181R.C0185layout.activity_my_addresess);
        setSupportActionBar((Toolbar) findViewById(C0181R.C0184id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle((CharSequence) "My Addresess");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.addressesrecyclerView = (RecyclerView) findViewById(C0181R.C0184id.addressessRecyclerView);
        this.deliverHereBtn = (Button) findViewById(C0181R.C0184id.dilever_here_btn);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(1);
        this.addressesrecyclerView.setLayoutManager(layoutManager);
        List<addressesModel> addressesModelList = new ArrayList<>();
        addressesModelList.add(new addressesModel("John Doe", "Delhi", "299999", true));
        addressesModelList.add(new addressesModel("Shubhit Jain", "Delhi", "299999", false));
        addressesModelList.add(new addressesModel("Shubhit", "Delhi", "299999", false));
        int mode = getIntent().getIntExtra("MODE", -1);
        if (mode == 0) {
            this.deliverHereBtn.setVisibility(0);
        } else {
            this.deliverHereBtn.setVisibility(8);
        }
        AddressesAdapter addressesAdapter = new AddressesAdapter(addressesModelList, mode);
        adapter = addressesAdapter;
        this.addressesrecyclerView.setAdapter(addressesAdapter);
        ((SimpleItemAnimator) this.addressesrecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        adapter.notifyDataSetChanged();
    }

    public static void refreshItem(int dselect, int select) {
        adapter.notifyItemChanged(select);
        adapter.notifyItemChanged(dselect);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        finish();
        return true;
    }
}
