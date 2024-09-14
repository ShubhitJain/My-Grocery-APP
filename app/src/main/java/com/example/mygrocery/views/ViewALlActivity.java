package com.example.mygrocery.views;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocery.Adapters.GridProductLayoutAdapter;
import com.example.mygrocery.Adapters.MyWishListAdapter;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.HorizontalProductScrollModel;
import com.example.mygrocery.Models.MyWishListModel;

import java.util.List;

public class ViewALlActivity extends AppCompatActivity {
    public static List<HorizontalProductScrollModel> horizontalProductScrollModelList;
    public static List<MyWishListModel> wishListModelList;
    private GridView gridView;
    private RecyclerView recyclerView;
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0181R.C0185layout.activity_view_all);
        Toolbar toolbar2 = (Toolbar) findViewById(C0181R.C0184id.toolbar);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle((CharSequence) getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.recyclerView = (RecyclerView) findViewById(C0181R.C0184id.recyclerView);
        this.gridView = (GridView) findViewById(C0181R.C0184id.grid_view);
        int layoutCode = getIntent().getIntExtra("layout_code", -1);
        if (layoutCode == 0) {
            this.recyclerView.setVisibility(0);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(1);
            this.recyclerView.setLayoutManager(layoutManager);
            MyWishListAdapter adapter = new MyWishListAdapter(wishListModelList, false);
            this.recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else if (layoutCode == 1) {
            this.gridView.setVisibility(0);
            this.gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        finish();
        return true;
    }
}
