package com.example.mygrocery.views;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocery.Adapters.HomePageAdapter;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.p016DB.DBqueries;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView categoryRecyclerView;
    private HomePageAdapter homePageAdapter;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0181R.C0185layout.activity_category);
        setSupportActionBar((Toolbar) findViewById(C0181R.C0184id.catecory_toolbar));
        String title = getIntent().getStringExtra("Category Name");
        getSupportActionBar().setTitle((CharSequence) title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.categoryRecyclerView = (RecyclerView) findViewById(C0181R.C0184id.categoryItemRecyclerView);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(1);
        this.categoryRecyclerView.setLayoutManager(testingLayoutManager);
        int listPosition = 0;
        for (int x = 0; x < DBqueries.loadedCategoriesName.size(); x++) {
            if (DBqueries.loadedCategoriesName.get(x).equals(title)) {
                listPosition = x;
            }
        }
        if (listPosition == 0) {
            DBqueries.loadedCategoriesName.add(title);
            DBqueries.lists.add(new ArrayList());
            HomePageAdapter homePageAdapter2 = new HomePageAdapter(DBqueries.lists.get(DBqueries.loadedCategoriesName.size() - 1));
            this.homePageAdapter = homePageAdapter2;
            DBqueries.setFragmentData(homePageAdapter2, this, DBqueries.loadedCategoriesName.size() - 1, title);
        } else {
            this.homePageAdapter = new HomePageAdapter(DBqueries.lists.get(listPosition));
        }
        this.categoryRecyclerView.setAdapter(this.homePageAdapter);
        this.homePageAdapter.notifyDataSetChanged();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0181R.C0186menu.search_icon, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == C0181R.C0184id.search_icon) {
            return true;
        }
        if (id != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        finish();
        return true;
    }
}
