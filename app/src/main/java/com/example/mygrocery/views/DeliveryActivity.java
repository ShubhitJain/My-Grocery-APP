package com.example.mygrocery.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocery.Adapters.CartAdapter;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.CartItemModel;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {
    public static final int SELECT_ADDRESS = 0;
    private Button changeOrAddNewAddressBtn;
    private RecyclerView dileveryRecyclerView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0181R.C0185layout.activity_delivery);
        setSupportActionBar((Toolbar) findViewById(C0181R.C0184id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle((CharSequence) "Delivery");
        this.dileveryRecyclerView = (RecyclerView) findViewById(C0181R.C0184id.dilevery_recycler_view);
        this.changeOrAddNewAddressBtn = (Button) findViewById(C0181R.C0184id.changeOraddAddressBtn);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(1);
        this.dileveryRecyclerView.setLayoutManager(layoutManager);
        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0, C0181R.C0183drawable.almonds, "Almonds 250g", 2, "Rs.499/-", "Rs.599/-", 1, 0, 0));
        cartItemModelList.add(new CartItemModel(0, C0181R.C0183drawable.almonds, "Almonds 250g", 2, "Rs.499/-", "Rs.599/-", 1, 1, 0));
        cartItemModelList.add(new CartItemModel(0, C0181R.C0183drawable.almonds, "Almonds 250g", 2, "Rs.499/-", "Rs.599/-", 1, 2, 0));
        cartItemModelList.add(new CartItemModel(1, "Price(3 items)", "Rs.1497/-", "free", "300/-", "1497/-"));
        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        this.dileveryRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
        this.changeOrAddNewAddressBtn.setVisibility(0);
        this.changeOrAddNewAddressBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent addAdressIntent = new Intent(DeliveryActivity.this, MyAddresessActivity.class);
                addAdressIntent.putExtra("MODE", 0);
                DeliveryActivity.this.startActivity(addAdressIntent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        finish();
        return true;
    }
}
