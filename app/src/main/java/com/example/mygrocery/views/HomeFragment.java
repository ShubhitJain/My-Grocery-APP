package com.example.mygrocery.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mygrocery.Adapters.CategoryAdapter;
import com.example.mygrocery.Adapters.HomePageAdapter;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.CategoryModel;
import com.example.mygrocery.p016DB.DBqueries;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    public static SwipeRefreshLayout swipeRefreshLayout;
    /* access modifiers changed from: private */
    public CategoryAdapter categoryAdapter;
    /* access modifiers changed from: private */
    public List<CategoryModel> categoryModelFakeList = new ArrayList();
    /* access modifiers changed from: private */
    public RecyclerView categoryRecyclerView;
    /* access modifiers changed from: private */
    public HomePageAdapter homePageAdapter;
    private RecyclerView recyclerViewTesting;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0181R.C0185layout.fragment_home2, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(C0181R.C0184id.refresh_layout);
        this.categoryRecyclerView = (RecyclerView) view.findViewById(C0181R.C0184id.categoryRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(0);
        this.categoryRecyclerView.setLayoutManager(layoutManager);
        this.categoryModelFakeList.add(new CategoryModel("null", ""));
        this.categoryModelFakeList.add(new CategoryModel("null", ""));
        this.categoryModelFakeList.add(new CategoryModel("null", ""));
        this.categoryModelFakeList.add(new CategoryModel("null", ""));
        this.categoryModelFakeList.add(new CategoryModel("null", ""));
        this.categoryModelFakeList.add(new CategoryModel("null", ""));
        this.categoryModelFakeList.add(new CategoryModel("null", ""));
        this.categoryModelFakeList.add(new CategoryModel("null", ""));
        this.categoryModelFakeList.add(new CategoryModel("null", ""));
        this.categoryModelFakeList.add(new CategoryModel("null", ""));
        this.categoryModelFakeList.add(new CategoryModel("null", ""));
        CategoryAdapter categoryAdapter2 = new CategoryAdapter(this.categoryModelFakeList);
        this.categoryAdapter = categoryAdapter2;
        this.categoryRecyclerView.setAdapter(categoryAdapter2);
        if (DBqueries.modelList.size() == 0) {
            DBqueries.loadCategories(this.categoryRecyclerView, getContext());
        } else {
            this.categoryAdapter.notifyDataSetChanged();
        }
        this.recyclerViewTesting = (RecyclerView) view.findViewById(C0181R.C0184id.home_page_recycler_view);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(1);
        this.recyclerViewTesting.setLayoutManager(testingLayoutManager);
        if (DBqueries.lists.size() == 0) {
            DBqueries.loadedCategoriesName.add("HOME");
            DBqueries.lists.add(new ArrayList());
            HomePageAdapter homePageAdapter2 = new HomePageAdapter(DBqueries.lists.get(0));
            this.homePageAdapter = homePageAdapter2;
            DBqueries.setFragmentData(homePageAdapter2, getContext(), 0, "HOME");
        } else {
            HomePageAdapter homePageAdapter3 = new HomePageAdapter(DBqueries.lists.get(0));
            this.homePageAdapter = homePageAdapter3;
            homePageAdapter3.notifyDataSetChanged();
        }
        this.recyclerViewTesting.setAdapter(this.homePageAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                HomeFragment.swipeRefreshLayout.setColorSchemeColors(HomeFragment.this.getContext().getResources().getColor(C0181R.C0182color.failred), HomeFragment.this.getContext().getResources().getColor(C0181R.C0182color.failred), HomeFragment.this.getContext().getResources().getColor(C0181R.C0182color.failred));
                HomeFragment.swipeRefreshLayout.setRefreshing(true);
                DBqueries.modelList.clear();
                DBqueries.lists.clear();
                DBqueries.loadedCategoriesName.clear();
                CategoryAdapter unused = HomeFragment.this.categoryAdapter = new CategoryAdapter(HomeFragment.this.categoryModelFakeList);
                HomeFragment.this.categoryRecyclerView.setAdapter(HomeFragment.this.categoryAdapter);
                DBqueries.loadCategories(HomeFragment.this.categoryRecyclerView, HomeFragment.this.getContext());
                DBqueries.loadedCategoriesName.add("HOME");
                DBqueries.lists.add(new ArrayList());
                DBqueries.setFragmentData(HomeFragment.this.homePageAdapter, HomeFragment.this.getContext(), 0, "HOME");
            }
        });
        return view;
    }
}
