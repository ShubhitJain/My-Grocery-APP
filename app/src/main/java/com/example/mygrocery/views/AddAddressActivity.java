package com.example.mygrocery.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mygrocery.C0181R;

public class AddAddressActivity extends AppCompatActivity {
    private Button saveBtn;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0181R.C0185layout.activity_add_address);
        this.saveBtn = (Button) findViewById(C0181R.C0184id.save_btn);
        setSupportActionBar((Toolbar) findViewById(C0181R.C0184id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle((CharSequence) "Add a new Address");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddAddressActivity.this.startActivity(new Intent(AddAddressActivity.this, DeliveryActivity.class));
                AddAddressActivity.this.finish();
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
